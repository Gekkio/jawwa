package fi.jawsy.jawwa.frp;

import java.util.concurrent.Executor;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

import fi.jawsy.jawwa.lang.Effect;

/**
 * Represents a stream of event objects of a specific type.
 * 
 * All default implementations of event streams are thread-safe.
 * 
 * @param <T>
 *            event type
 */
public interface EventStream<T> {

    /**
     * Returns a new event stream that filters event values based on the given predicate, and applies the given function
     * to all values that pass the predicate.
     * 
     * @param p
     *            non-null predicate
     * @param f
     *            non-null function
     * @return event stream
     */
    <U> EventStream<U> collect(Predicate<? super T> p, Function<? super T, U> f);

    /**
     * Runs the given side effect whenever an event is published. Returns this event stream.
     * 
     * <b>Creates a subscription, so this method has a side effect.</b> The subscription is never removed, so memory
     * leaks may occur depending on the type of the event stream.
     * 
     * @param e
     *            non-null side effect
     * @return event stream
     */
    EventStream<T> foreach(Effect<? super T> e);

    /**
     * Runs the given side effect whenever an event is published. Returns this event stream.
     * 
     * <b>Creates a subscription, so this method has a side effect.</b> The subscription is removed when the given token
     * is cancelled.
     * 
     * @param e
     *            non-null side effect
     * @param token
     *            non-null cancellation token
     * @return event stream
     */
    EventStream<T> foreach(Effect<? super T> e, CancellationToken token);

    /**
     * Returns a new event stream that applies the given function to all event values.
     * 
     * @param f
     *            non-null mapper function
     * @return event stream
     */
    <U> EventStream<U> map(Function<? super T, U> f);

    /**
     * Returns a new event stream that publishes the given constant instead of the original event values. In practice
     * this is useful if you don't care about the event values, and want to replace them with something more useful.
     * 
     * @param constant
     *            constant value
     * @return event stream
     */
    <U> EventStream<U> map(U constant);

    /**
     * Returns a new event stream that publishes values returned by the given supplier instead of the original event
     * values. In practice this is useful if you don't care about the event values, and want to replace them with
     * something more useful.
     * 
     * @param s
     *            non-null event value supplier
     * @return event stream
     */
    <U> EventStream<U> map(Supplier<U> s);

    /**
     * Returns a new event stream that publishes the current value of the signal instead of the original event values.
     * In practice this is useful if you don't care about the event values, and want to replace them with something more
     * useful.
     * 
     * @param s
     *            non-null signal
     * @return event stream
     */
    <U> EventStream<U> map(Signal<U> s);

    /**
     * Returns a new event stream that routes events based on the given function. The returned event stream will always
     * be connected to the event stream returned by the last invocation of the given function.
     * 
     * @param f
     *            non-null function
     * @return event stream
     */
    <U> EventStream<U> flatMap(Function<? super T, EventStream<U>> f);

    /**
     * Returns a new event stream that only publishes events that pass the given predicate.
     * 
     * @param p
     *            non-null predicate
     * @return event stream
     */
    EventStream<T> filter(Predicate<? super T> p);

    /**
     * Returns a union of the given event streams. The resulting event stream will receive events from both event
     * streams.
     * 
     * @param es
     *            non-null other event stream
     * @return event stream
     */
    EventStream<T> union(EventStream<? extends T> es);

    /**
     * Returns a new event stream that filters out sequential events that are equal.
     * 
     * @return event stream
     */
    EventStream<T> distinct();

    /**
     * Returns a new event stream that ignores the specific amount of events before publishing them to subscribers.
     * 
     * @param amount
     *            positive amount of events to drop
     * @return event stream
     */
    EventStream<T> drop(int amount);

    /**
     * Returns a new event stream that ignores all events until an event has been published in the event stream given as
     * a parameter.
     * 
     * @param es
     *            non-null event stream used as the trigger
     * @return event stream
     */
    EventStream<T> dropUntil(EventStream<?> es);

    /**
     * Returns a new event stream that publishes only the specific amount of events to subscribers.
     * 
     * @param amount
     *            positive amount of events to take
     * @return event stream
     */
    EventStream<T> take(int amount);

    /**
     * Returns a new event stream that publishes events to subscribers until an event is published to the event stream
     * given as a parameter.
     * 
     * @param es
     *            non-null event stream used as the trigger
     * @return event stream
     */
    EventStream<T> takeUntil(EventStream<?> es);

    /**
     * Returns a new event stream that publishes events to subscribers until the given predicate returns false. Once the
     * predicate has returned false once, the event stream will be closed forever.
     * 
     * @param p
     *            non-null predicate
     * @return event stream
     */
    EventStream<T> takeWhile(Predicate<? super T> p);

    /**
     * Returns a new event stream that is internally synchronized, so that events are guaranteed to be published
     * sequentially. In practice this means that a new event can be published only after all the processing of the
     * previous event is done.
     * 
     * The default implementation uses an internal lock object and does not use the event stream itself as a monitor.
     * 
     * @return event stream
     */
    EventStream<T> synchronize();

    /**
     * Returns a new event stream that runs subscriber methods with the given executor.
     * 
     * @param executor
     *            non-null executor
     * @return event stream
     */
    EventStream<T> asynchronous(Executor executor);

    /**
     * Pipes all arriving events to the given event sink and return this event stream.
     * 
     * <b>Creates a subscription, so this method has a side effect.</b> The subscription is never removed, so memory
     * leaks may occur depending on the type of the event stream.
     * 
     * @param sink
     *            non-null target sink
     * @return event stream
     */
    EventStream<T> pipeTo(EventSink<? super T> sink);

    /**
     * Pipes all arriving events to the given event sink and return this event stream.
     * 
     * <b>Creates a subscription, so this method has a side effect.</b> The subscription is removed when the given token
     * is cancelled.
     * 
     * @param sink
     *            non-null target sink
     * @param token
     *            non-null cancellation token
     * @return event stream
     */
    EventStream<T> pipeTo(EventSink<? super T> sink, CancellationToken token);

    /**
     * Returns a signal that will contain the given initial value and track the value based on arriving events.
     * 
     * <b>Creates a subscription, so this method has a side effect.</b> The subscription is never removed, so memory
     * leaks may occur depending on the type of the event stream.
     * 
     * @param initial
     *            initial value
     * @return signal
     */
    Signal<T> hold(T initial);

    /**
     * Returns a signal that will contain the given initial value and track the value based on arriving events.
     * 
     * <b>Creates a subscription, so this method has a side effect.</b> The subscription is removed when the given token
     * is cancelled.
     * 
     * @param initial
     *            initial value
     * @param token
     *            non-null cancellation token
     * @return signal
     */
    Signal<T> hold(T initial, CancellationToken token);

}