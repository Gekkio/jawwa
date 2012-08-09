package fi.jawsy.jawwa.frp;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;

import lombok.val;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Predicate;

import fi.jawsy.jawwa.lang.Effect;

public abstract class EventStreamBase<E> implements EventStream<E>, Serializable {

    private static final long serialVersionUID = 4389299638075146452L;

    @Override
    public <U> EventStream<U> map(final Function<? super E, U> mapper) {
        class MappedEventStream extends EventStreamBase<U> {
            private static final long serialVersionUID = 8746853070424352228L;

            @Override
            public CleanupHandle foreach(final Effect<? super U> e) {
                class MapperEffect implements Effect<E>, Serializable {
                    private static final long serialVersionUID = -6599624715377023011L;

                    @Override
                    public void apply(E input) {
                        e.apply(mapper.apply(input));
                    }
                }
                return EventStreamBase.this.foreach(new MapperEffect());
            }
        }
        return new MappedEventStream();
    }

    @Override
    public EventStream<E> filter(final Predicate<? super E> p) {
        class FilteredEventStream extends EventStreamBase<E> {
            private static final long serialVersionUID = 2518677092062764830L;

            @Override
            public CleanupHandle foreach(final Effect<? super E> e) {
                class FilterEffect implements Effect<E>, Serializable {
                    private static final long serialVersionUID = -7284834867826632697L;

                    @Override
                    public void apply(E input) {
                        if (p.apply(input)) {
                            e.apply(input);
                        }
                    }
                }
                return EventStreamBase.this.foreach(new FilterEffect());
            }
        }
        return new FilteredEventStream();
    }

    @Override
    public <U> EventStream<U> flatMap(final Function<? super E, EventStream<U>> f) {
        class FlatMappedEventStream extends EventStreamBase<U> {
            private static final long serialVersionUID = -1662566691398092407L;

            @Override
            public CleanupHandle foreach(final Effect<? super U> e) {
                val inner = new AtomicReference<CleanupHandle>();

                class FlatMapEffect implements Effect<E>, Serializable {
                    private static final long serialVersionUID = -3177496251373686951L;

                    @Override
                    public void apply(E input) {
                        CleanupHandle old = inner.getAndSet(null);
                        if (old != null)
                            old.cleanup();
                        inner.set(f.apply(input).foreach(e));
                    }
                }

                final CleanupHandle outer = EventStreamBase.this.foreach(new FlatMapEffect());

                class FlatMapCleanup implements CleanupHandle, Serializable {
                    private static final long serialVersionUID = 7160659915770883489L;

                    @Override
                    public void cleanup() {
                        outer.cleanup();
                        CleanupHandle old = inner.getAndSet(null);
                        if (old != null)
                            old.cleanup();
                    }
                }
                return new FlatMapCleanup();
            }
        }
        return new FlatMappedEventStream();
    }

    @Override
    public EventStream<E> union(final EventStream<? extends E> es) {
        return EventStreams.union(this, es);
    }

    @Override
    public EventStream<E> distinct() {
        class DistinctEventStream extends EventStreamBase<E> {
            private static final long serialVersionUID = 7323926323136519568L;

            private final AtomicReference<E> lastValue = new AtomicReference<E>();

            @Override
            public CleanupHandle foreach(final Effect<? super E> e) {
                class DistinctEffect implements Effect<E>, Serializable {
                    private static final long serialVersionUID = 6109531897034720266L;

                    @Override
                    public void apply(E input) {
                        E previous = lastValue.getAndSet(input);
                        if (!Objects.equal(previous, input)) {
                            e.apply(input);
                        }
                    }
                }
                return EventStreamBase.this.foreach(new DistinctEffect());
            }
        }
        return new DistinctEventStream();
    }

    @Override
    public Signal<E> hold(E initial) {
        final Signal.Var<E> s = new Signal.Var<E>(initial);
        s.consume(this);
        return s;
    }

}