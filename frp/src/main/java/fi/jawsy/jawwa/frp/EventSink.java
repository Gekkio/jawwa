package fi.jawsy.jawwa.frp;

public interface EventSink<T> {

    void fire(T event);

}
