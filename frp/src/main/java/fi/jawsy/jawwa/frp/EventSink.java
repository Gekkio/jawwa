package fi.jawsy.jawwa.frp;

public interface EventSink<T> {

    void fire(T event);

    CleanupHandle pipeFrom(EventStream<? extends T> es);

}
