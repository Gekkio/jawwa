package fi.jawsy.jawwa.frp;

/**
 * Target for events.
 * 
 * @param <T>
 *            event type
 */
public interface EventSink<T> {

    /**
     * Publishes a new event to the sink.
     * 
     * @param event
     */
    void fire(T event);

}
