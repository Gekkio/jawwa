package fi.jawsy.jawwa.frp;

import java.io.ObjectStreamException;
import java.io.Serializable;

public interface CancellationToken {

    void onCancel(Runnable callback);

    boolean isCancelled();

    boolean canBeCancelled();

    public static final CancellationToken NONE = new NoneToken();

    static class NoneToken implements CancellationToken, Serializable {
        private static final long serialVersionUID = -8016132457679326588L;

        @Override
        public void onCancel(Runnable runnable) {
        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        private Object readResolve() throws ObjectStreamException {
            return NONE;
        }

        @Override
        public boolean canBeCancelled() {
            return false;
        }

    }

}
