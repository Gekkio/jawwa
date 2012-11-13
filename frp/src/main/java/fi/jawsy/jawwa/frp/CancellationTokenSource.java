package fi.jawsy.jawwa.frp;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * A mutable cancellation token that can be cancelled manually.
 * 
 * This class is thread-safe.
 */
public class CancellationTokenSource implements CancellationToken, Serializable {

    private static final Logger log = LoggerFactory.getLogger(CancellationTokenSource.class);

    private static final long serialVersionUID = 7711864116500771217L;

    private final AtomicBoolean cancelled = new AtomicBoolean();

    private final List<Runnable> callbacks = Lists.newArrayList();

    @Override
    public void onCancel(Runnable callback) {
        Preconditions.checkNotNull(callback, "callback cannot be null");
        if (!cancelled.get()) {
            synchronized (this) {
                if (!cancelled.get())
                    callbacks.add(callback);
            }
        }
    }

    @Override
    public boolean isCancelled() {
        return cancelled.get();
    }

    /**
     * Cancels the token. This method is idempotent, so multiple invocations are safe and callbacks are guaranteed to
     * execute only once.
     */
    public void cancel() {
        if (cancelled.compareAndSet(false, true)) {
            synchronized (this) {
                for (Iterator<Runnable> i = callbacks.iterator(); i.hasNext();) {
                    Runnable callback = i.next();
                    i.remove();
                    try {
                        callback.run();
                    } catch (RuntimeException e) {
                        log.error("Failed to execute callback", e);
                    }
                }
                callbacks.clear();
            }
        }
    }

    @Override
    public boolean canBeCancelled() {
        return true;
    }

}