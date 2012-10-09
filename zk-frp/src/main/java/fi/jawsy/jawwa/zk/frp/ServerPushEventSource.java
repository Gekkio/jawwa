package fi.jawsy.jawwa.zk.frp;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.val;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.DesktopUnavailableException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;

import fi.jawsy.jawwa.frp.CancellationToken;
import fi.jawsy.jawwa.frp.EventSink;
import fi.jawsy.jawwa.frp.EventStream;
import fi.jawsy.jawwa.frp.EventStreamBase;
import fi.jawsy.jawwa.lang.Effect;

public class ServerPushEventSource<E> extends EventStreamBase<E> implements EventSink<E> {

    private static final long serialVersionUID = -9056310527499308343L;

    private final Desktop desktop;

    private final EventListener<ServerPushEvent<E>> listener;

    private volatile ImmutableSet<Effect<? super E>> effects = ImmutableSet.of();
    private final AtomicBoolean hasEffects = new AtomicBoolean();

    static class ServerPushEvent<E> extends Event {
        private static final long serialVersionUID = 3159112399616454726L;

        public final E data;

        public ServerPushEvent(E data) {
            super("onServerPush", null, null);
            this.data = data;
        }
    }

    public ServerPushEventSource() {
        this(Executions.getCurrent().getDesktop());
    }

    public ServerPushEventSource(Desktop desktop) {
        this.desktop = desktop;
        class Listener implements EventListener<ServerPushEvent<E>>, Serializable {
            private static final long serialVersionUID = 1297814172054452846L;

            @Override
            public void onEvent(ServerPushEvent<E> event) throws Exception {
                for (val effect : effects) {
                    effect.apply(event.data);
                }
            }
        }
        this.listener = new Listener();
    }

    @Override
    public EventStream<E> foreach(final Effect<? super E> e, CancellationToken token) {
        Preconditions.checkArgument(desktop.equals(Executions.getCurrent().getDesktop()), "desktop doesn't match in foreach");
        addListener(e);
        desktop.enableServerPush(true);

        token.onCancel(new Runnable() {
            @Override
            public void run() {
                clearListeners();
            }
        });

        if (token.isCancelled()) {
            removeListener(e);
        }

        return this;
    }

    private void removeListener(Effect<? super E> e) {
        synchronized (this) {
            val b = ImmutableSet.<Effect<? super E>> builder();

            for (val effect : effects) {
                if (!effect.equals(e))
                    b.add(effect);
            }

            effects = b.build();
            if (effects.isEmpty())
                hasEffects.set(false);
        }
    }

    private void addListener(Effect<? super E> e) {
        synchronized (this) {
            effects = ImmutableSet.<Effect<? super E>> builder().addAll(effects).add(e).build();
            hasEffects.set(true);
        }
    }

    private void clearListeners() {
        synchronized (this) {
            effects = ImmutableSet.of();
            hasEffects.set(false);
        }
    }

    @Override
    public void fire(E event) {
        if (hasEffects.get()) {
            try {
                Executions.schedule(desktop, listener, new ServerPushEvent<E>(event));
            } catch (DesktopUnavailableException e) {
                clearListeners();
            }
        }
    }

}
