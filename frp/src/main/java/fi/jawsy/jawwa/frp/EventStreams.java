package fi.jawsy.jawwa.frp;

import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import fi.jawsy.jawwa.lang.Effect;

/**
 * Utilities for EventStream objects.
 */
public final class EventStreams {

    private EventStreams() {
    }

    @SuppressWarnings("rawtypes")
    static class EmptyEventStream extends EventStreamBase {
        private static final long serialVersionUID = 5638633110197759582L;

        @Override
        public EventStream collect(Predicate p, Function f) {
            return this;
        }

        @Override
        public EventStream foreach(Effect e) {
            return this;
        }

        @Override
        public EventStream foreach(Effect e, CancellationToken token) {
            return this;
        }

        @Override
        public EventStream map(Function f) {
            return this;
        }

        @Override
        public EventStream map(Object constant) {
            return this;
        }

        @Override
        public EventStream map(Supplier s) {
            return this;
        }

        @Override
        public EventStream flatMap(Function f) {
            return this;
        }

        @Override
        public EventStream filter(Predicate p) {
            return this;
        }

        @Override
        public EventStream union(EventStream es) {
            return this;
        }

        @Override
        public EventStream distinct() {
            return this;
        }

        @Override
        public EventStream drop(int amount) {
            return this;
        }

        @Override
        public EventStream dropUntil(EventStream es) {
            return this;
        }

        @Override
        public EventStream take(int amount) {
            return this;
        }

        @Override
        public EventStream takeUntil(EventStream es) {
            return this;
        }

        @Override
        public EventStream takeWhile(Predicate p) {
            return this;
        }

        @Override
        public EventStream synchronize() {
            return this;
        }

        @Override
        public EventStream asynchronous(Executor executor) {
            return this;
        }

        @Override
        public EventStream pipeTo(EventSink sink) {
            return this;
        }

        @Override
        public EventStream pipeTo(EventSink sink, CancellationToken token) {
            return this;
        }

        @SuppressWarnings("unchecked")
        @Override
        public Signal hold(Object initial) {
            return new Signal.Val(initial);
        }

        @SuppressWarnings("unchecked")
        @Override
        public Signal hold(Object initial, CancellationToken token) {
            return new Signal.Val(initial);
        }

    }

    @SuppressWarnings("rawtypes")
    private static final EventStream EMPTY = new EmptyEventStream();

    /**
     * Returns an event stream that is always empty.
     * 
     * @return event stream
     */
    @SuppressWarnings("unchecked")
    public static <T> EventStream<T> empty() {
        return EMPTY;
    }

    /**
     * Returns an event stream that is always empty.
     * 
     * @param clazz
     *            event type (for convenience only)
     * @return event stream
     */
    public static <T> EventStream<T> empty(Class<T> clazz) {
        return empty();
    }

    /**
     * Returns an event stream that immediately publishes the given value when a subscription is added.
     * 
     * @param value
     *            event value
     * @return event stream
     */
    public static <T> EventStream<T> instant(final T value) {
        class InstantEventStream extends EventStreamBase<T> {
            private static final long serialVersionUID = 6465414757355523929L;

            @Override
            public EventStream<T> foreach(Effect<? super T> e, CancellationToken token) {
                if (!token.isCancelled())
                    e.apply(value);
                return this;
            }

            @Override
            public EventStream<T> distinct() {
                return this;
            }

        }
        return new InstantEventStream();
    }

    /**
     * Returns an event stream that immediately publishes the current value of the given atomic reference when a
     * subscription is added.
     * 
     * @param value
     *            non-null atomic reference to event value
     * @return event stream
     */
    public static <T> EventStream<T> instant(final AtomicReference<T> value) {
        Preconditions.checkNotNull(value, "value reference cannot be null");
        class InstantEventStream extends EventStreamBase<T> {
            private static final long serialVersionUID = -8810804644628923419L;

            @Override
            public EventStream<T> foreach(Effect<? super T> e, CancellationToken token) {
                if (!token.isCancelled())
                    e.apply(value.get());
                return this;
            }

            @Override
            public EventStream<T> distinct() {
                return this;
            }

        }
        return new InstantEventStream();
    }

    /**
     * Returns an event stream that immediately publishes the value returned by the given supplier when a subscription
     * is added.
     * 
     * @param value
     *            non-null value supplier
     * @return event stream
     */
    public static <T> EventStream<T> instant(final Supplier<T> value) {
        Preconditions.checkNotNull(value, "supplier cannot be null");
        class InstantEventStream extends EventStreamBase<T> {
            private static final long serialVersionUID = -1095226181964772088L;

            @Override
            public EventStream<T> foreach(Effect<? super T> e, CancellationToken token) {
                if (!token.isCancelled())
                    e.apply(value.get());
                return this;
            }

            @Override
            public EventStream<T> distinct() {
                return this;
            }

        }
        return new InstantEventStream();
    }

    /**
     * Returns a union of the given event streams. The resulting event stream will receive events from all given event
     * streams.
     * 
     * This method can be used instead of EventStream.union to return an event stream whose event type is a superclass
     * of event streams given as parameters.
     * 
     * @param first
     *            non-null first event stream
     * @param second
     *            non-null second event stream
     * @return union of event streams
     */
    public static <T> EventStream<T> union(final EventStream<? extends T> first, final EventStream<? extends T> second) {
        Preconditions.checkNotNull(first, "first event stream cannot be null");
        Preconditions.checkNotNull(second, "second event stream cannot be null");
        class UnionEventStream extends EventStreamBase<T> {
            private static final long serialVersionUID = -3906439455738789209L;

            @Override
            public EventStream<T> foreach(Effect<? super T> e, CancellationToken token) {
                first.foreach(e, token);
                second.foreach(e, token);
                return this;
            }
        }
        return new UnionEventStream();
    }

    /**
     * Returns a union of the given event streams. The resulting event stream will receive events from all given event
     * streams.
     * 
     * @param streams
     *            unchecked event streams (non-null)
     * @return union of event streams
     */
    @SuppressWarnings("rawtypes")
    public static <T> EventStream<T> unionUnchecked(final EventStream... streams) {
        for (EventStream stream : streams) {
            Preconditions.checkNotNull(stream, "stream cannot be null");
        }
        class UnionEventStream extends EventStreamBase<T> {
            private static final long serialVersionUID = 6444352894945226672L;

            @SuppressWarnings("unchecked")
            @Override
            public EventStream<T> foreach(Effect<? super T> e, CancellationToken token) {
                for (EventStream stream : streams) {
                    stream.foreach(e, token);
                }
                return this;
            }
        }
        return new UnionEventStream();
    }

}