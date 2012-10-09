package fi.jawsy.jawwa.frp;

import java.io.ObjectStreamException;
import java.util.concurrent.atomic.AtomicReference;

import fi.jawsy.jawwa.lang.Effect;

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

    @SuppressWarnings("unchecked")
    public static <T> EventStream<T> empty() {
        return EMPTY;
    }

    public static <T> EventStream<T> empty(Class<T> clazz) {
        return empty();
    }

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

    public static <T> EventStream<T> instant(final AtomicReference<T> value) {
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

    public static <T> EventStream<T> union(final EventStream<? extends T> first, final EventStream<? extends T> second) {
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

    @SuppressWarnings("rawtypes")
    public static <T> EventStream<T> unionUnchecked(final EventStream... streams) {
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