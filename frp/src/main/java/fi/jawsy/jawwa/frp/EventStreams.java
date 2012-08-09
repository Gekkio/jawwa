package fi.jawsy.jawwa.frp;

import java.io.ObjectStreamException;

import fi.jawsy.jawwa.lang.Effect;

public final class EventStreams {

    private EventStreams() {
    }

    @SuppressWarnings("rawtypes")
    private static final EventStream EMPTY = new EventStreamBase() {
        private static final long serialVersionUID = 9029795963118549722L;

        @Override
        public CleanupHandle foreach(Effect e) {
            return CleanupHandles.NOOP;
        }

        private Object readResolve() throws ObjectStreamException {
            return EMPTY;
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> EventStream<T> empty() {
        return EMPTY;
    }

    public static <T> EventStream<T> empty(Class<T> clazz) {
        return empty();
    }

    public static <T> EventStream<T> union(final EventStream<? extends T> first, final EventStream<? extends T> second) {
        class UnionEventStream extends EventStreamBase<T> {
            private static final long serialVersionUID = -3906439455738789209L;

            @Override
            public CleanupHandle foreach(Effect<? super T> e) {
                final CleanupHandle esc1 = first.foreach(e);
                final CleanupHandle esc2 = second.foreach(e);
                return CleanupHandles.merge(esc1, esc2);
            }
        }
        return new UnionEventStream();
    }

}