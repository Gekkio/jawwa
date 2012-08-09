package fi.jawsy.jawwa.zk.frp;

import java.io.Serializable;

import lombok.RequiredArgsConstructor;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Deferrable;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;

import com.google.common.base.Preconditions;

import fi.jawsy.jawwa.frp.CleanupHandle;
import fi.jawsy.jawwa.frp.EventStreamBase;
import fi.jawsy.jawwa.lang.Effect;

@RequiredArgsConstructor
public class ZkEventStream<E extends Event> extends EventStreamBase<E> {

    private static final long serialVersionUID = 1093097272751986811L;

    private final Component c;
    private final String eventName;
    private final boolean deferrable;

    public ZkEventStream(Component c, String eventName) {
        this(c, eventName, false);
    }

    class Listener implements EventListener<E>, CleanupHandle, Serializable, Deferrable {
        private static final long serialVersionUID = 5492016167303631840L;

        private final Effect<? super E> f;
        private boolean registered;

        public Listener(Effect<? super E> f) {
            this.f = f;
            c.addEventListener(eventName, this);
            registered = true;
        }

        @Override
        public void cleanup() {
            Preconditions.checkArgument(registered, "listener not registered (already cleaned up?)");
            c.removeEventListener(eventName, this);
            registered = false;
        }

        @Override
        public void onEvent(E event) throws Exception {
            f.apply(event);
        }

        @Override
        public boolean isDeferrable() {
            return deferrable;
        }

    }

    @Override
    public CleanupHandle foreach(final Effect<? super E> e) {
        return new Listener(e);
    }

    public ZkEventStream<E> deferrable() {
        return new ZkEventStream<E>(c, eventName, true);
    }

}