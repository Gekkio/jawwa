package fi.jawsy.jawwa.frp;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Supplier;

import fi.jawsy.jawwa.lang.Effect;

public abstract class SignalBase<T> implements Signal<T>, Serializable, Supplier<T> {

    private static final long serialVersionUID = -8545700156708474986L;

    @Override
    public Supplier<T> asSupplier() {
        return this;
    }

    @Override
    public T get() {
        return now();
    }

    @Override
    public EventStream<T> nowAndChange() {
        return EventStreams.instant(this.asSupplier()).union(change());
    }

    @Override
    public <U> Signal<U> map(final Function<? super T, U> f) {
        class MappedSignal extends SignalBase<U> {
            private static final long serialVersionUID = -3228645957665057772L;

            @Override
            public U now() {
                return f.apply(SignalBase.this.now());
            }

            @Override
            public EventStream<U> change() {
                return SignalBase.this.change().map(f);
            }
        }
        return new MappedSignal();
    }

    @Override
    public <U> Signal<U> map(Supplier<U> s) {
        return map(Functions.forSupplier(s));
    }

    @Override
    public <U> Signal<U> map(U constant) {
        return map(Functions.constant(constant));
    }

    @Override
    public <U> Signal<U> map(Signal<U> s) {
        return map(s.asSupplier());
    }

    @Override
    public <U> Signal<U> flatMap(final Function<? super T, Signal<U>> f) {
        class FlatMappedSignal extends SignalBase<U> {
            private static final long serialVersionUID = -6722902423420227469L;

            @Override
            public U now() {
                return f.apply(SignalBase.this.now()).now();
            }

            @Override
            public EventStream<U> change() {
                class FlatMapSignalEventStream extends EventStreamBase<U> {
                    private static final long serialVersionUID = 7806489755846636883L;

                    @Override
                    public EventStream<U> foreach(final Effect<? super U> e, CancellationToken token) {
                        final AtomicReference<CancellationTokenSource> oldToken = new AtomicReference<CancellationTokenSource>();

                        class FlatMappedSignalEffect implements Effect<T> {
                            @Override
                            public void apply(T input) {
                                CancellationTokenSource newToken = new CancellationTokenSource();
                                f.apply(input).change().foreach(e, newToken);

                                CancellationTokenSource old = oldToken.getAndSet(newToken);
                                if (old != null)
                                    old.cancel();
                            }
                        }

                        SignalBase.this.change().foreach(new FlatMappedSignalEffect(), token);
                        return this;
                    }
                }
                return new FlatMapSignalEventStream();
            }

        }
        return new FlatMappedSignal();
    }

}
