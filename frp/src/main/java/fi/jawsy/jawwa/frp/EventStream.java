package fi.jawsy.jawwa.frp;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

import fi.jawsy.jawwa.lang.Effect;

public interface EventStream<T> {

    CleanupHandle foreach(Effect<? super T> e);

    <U> EventStream<U> map(Function<? super T, U> f);

    <U> EventStream<U> flatMap(Function<? super T, EventStream<U>> f);

    EventStream<T> filter(Predicate<? super T> p);

    EventStream<T> union(EventStream<? extends T> es);

    EventStream<T> distinct();

    EventStream<T> drop(int amount);

    EventStream<T> dropUntil(EventStream<?> es);

    EventStream<T> take(int amount);

    EventStream<T> takeUntil(EventStream<?> es);

    EventStream<T> synchronize();

    Signal<T> hold(T initial);

}