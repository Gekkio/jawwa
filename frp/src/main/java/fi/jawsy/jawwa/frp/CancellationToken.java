package fi.jawsy.jawwa.frp;

import java.io.ObjectStreamException;
import java.io.Serializable;

/**
 * Represents a token that tracks cancellation state and collects callback functions that should be executed when the
 * token is cancelled.
 */
public interface CancellationToken {

    /**
     * Registers a cancellation callback. Callbacks are guaranteed to be executed exactly once when the token is
     * cancelled.
     * 
     * @param callback
     *            non-null callback
     */
    void onCancel(Runnable callback);

    /**
     * Checks if the token has already been cancelled.
     * 
     * @return boolean
     */
    boolean isCancelled();

    /**
     * Checks if the token can be cancelled. This is a property of the token subclass, and doesn't change if the token
     * is cancelled or not.
     * 
     * @return boolean
     */
    boolean canBeCancelled();

    /**
     * A cancellation token that can never be cancelled.
     */
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
