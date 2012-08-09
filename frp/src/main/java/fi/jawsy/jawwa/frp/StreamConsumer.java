package fi.jawsy.jawwa.frp;

public interface StreamConsumer<T> {

    CleanupHandle consume(EventStream<? extends T> es);

}
