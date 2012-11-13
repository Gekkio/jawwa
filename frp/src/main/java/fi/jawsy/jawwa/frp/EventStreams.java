package fi.jawsy.jawwa.frp;

import java.io.ObjectStreamException;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;

import fi.jawsy.jawwa.lang.Effect;

/**
 * Utilities for EventStream objects.
 */
public final class EventStreams {

    private EventStreams() {
    }

    @SuppressWarnings("rawtypes")
    private static final EventStream EMPTY = new EventStreamBase() {
        private static final long serialVersionUID = 9029795963118549722L;

        private Object readResolve() throws ObjectStreamException {
            return EMPTY;
        }

        @Override
        public EventStream foreach(Effect e, CancellationToken token) {
            return this;
        }
    };

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
        }
        return new InstantEventStream();
    }

    /**
     * Returns an event stream that immediately publishes the current value of the given signal when a subscription is
     * added.
     * 
     * @param value
     *            non-null signal
     * @return event stream
     */
    public static <T> EventStream<T> instant(final Signal<T> value) {
        Preconditions.checkNotNull(value, "signal cannot be null");
        class InstantEventStream extends EventStreamBase<T> {
            private static final long serialVersionUID = 1713836073822797296L;

            @Override
            public EventStream<T> foreach(Effect<? super T> e, CancellationToken token) {
                if (!token.isCancelled())
                    e.apply(value.now());
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