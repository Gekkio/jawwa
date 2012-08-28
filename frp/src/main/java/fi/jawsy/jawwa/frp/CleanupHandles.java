package fi.jawsy.jawwa.frp;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

import lombok.val;

public final class CleanupHandles {

    private CleanupHandles() {
    }

    static class NoopCleanupHandle implements CleanupHandle, Serializable {
        private static final long serialVersionUID = -3868657478362679243L;

        @Override
        public void cleanup() {
        }

        private Object readResolve() throws ObjectStreamException {
            return NOOP;
        }

    }

    public static final CleanupHandle NOOP = new NoopCleanupHandle();

    public static CleanupHandle merge(final CleanupHandle first, final CleanupHandle second) {
        class MergedCleanupHandle implements CleanupHandle, Serializable {
            private static final long serialVersionUID = 586544697512629591L;

            @Override
            public void cleanup() {
                first.cleanup();
                second.cleanup();
            }
        }
        return new MergedCleanupHandle();
    }

    public static CleanupHandle merge(final CleanupHandle... handles) {
        class MergedCleanupHandle implements CleanupHandle, Serializable {
            private static final long serialVersionUID = 3330087040400383817L;

            @Override
            public void cleanup() {
                for (CleanupHandle handle : handles) {
                    handle.cleanup();
                }
            }
        }
        return new MergedCleanupHandle();
    }

    public static CleanupHandle atomic(final AtomicReference<CleanupHandle> handle) {
        class AtomicCleanupHandle implements CleanupHandle, Serializable {
            private static final long serialVersionUID = -1372846716767019191L;

            @Override
            public void cleanup() {
                val h = handle.get();
                if (h != null)
                    h.cleanup();
            }
        }
        return new AtomicCleanupHandle();
    }

    public static CleanupHandle merge(final AtomicReference<CleanupHandle> first, final AtomicReference<CleanupHandle> second) {
        class MergedCleanupHandle implements CleanupHandle, Serializable {
            private static final long serialVersionUID = 2437793808752492821L;

            @Override
            public void cleanup() {
                val fh = first.get();
                if (fh != null)
                    fh.cleanup();
                val sh = second.get();
                if (sh != null)
                    sh.cleanup();
            }
        }
        return new MergedCleanupHandle();
    }

}
