package fi.jawsy.jawwa.frp;

import java.util.concurrent.Executor;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import fi.jawsy.jawwa.lang.Effect;

public interface EventStream<T> {

    EventStream<T> foreach(Effect<? super T> e);

    EventStream<T> foreach(Effect<? super T> e, CancellationToken token);

    <U> EventStream<U> map(Function<? super T, U> f);

    <U> EventStream<U> map(U constant);

    <U> EventStream<U> map(Supplier<U> s);

    <U> EventStream<U> map(Signal<U> s);

    <U> EventStream<U> flatMap(Function<? super T, EventStream<U>> f);

    EventStream<T> filter(Predicate<? super T> p);

    EventStream<T> union(EventStream<? extends T> es);

    EventStream<T> distinct();

    EventStream<T> drop(int amount);

    EventStream<T> dropUntil(EventStream<?> es);

    EventStream<T> take(int amount);

    EventStream<T> takeUntil(EventStream<?> es);

    EventStream<T> takeWhile(Predicate<? super T> p);

    EventStream<T> synchronize();

    EventStream<T> asynchronous(Executor executor);

    EventStream<T> pipeTo(EventSink<? super T> sink);

    EventStream<T> pipeTo(EventSink<? super T> sink, CancellationToken token);

    Signal<T> hold(T initial);

    Signal<T> hold(T initial, CancellationToken token);

}