package fi.jawsy.jawwa.zk.metrics;

import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.Meter;
import com.yammer.metrics.core.Timer;

/**
 * Exposes all supported ZK metrics so that an application can report its own metrics easily. The application must NOT
 * call any write methods such as inc(), dec(), update(), or tick().
 */
public interface ZkMetrics {

    /**
     * Tracks the count of active ZK desktops.
     * 
     * @return metric
     */
    Counter getActiveDesktops();

    /**
     * Tracks the count of events being processed.
     * 
     * @return metric
     */
    Counter getActiveEvents();

    /**
     * Tracks the count of active ZK sessions.
     * 
     * @return metric
     */
    Counter getActiveSessions();

    /**
     * Tracks the count of active ZK AJAX updates.
     * 
     * @return metric
     */
    Counter getActiveUpdates();

    /**
     * Measures the combined duration of server-side request processing, network latency, and client-side processing.
     * 
     * @return metric
     */
    Timer getClientRequestCompletedDuration();

    /**
     * Measures the combined duration of server-side request processing and network latency.
     * 
     * @return metric
     */
    Timer getClientRequestReceivedDuration();

    /**
     * Counts the number of desktops that have been created.
     * 
     * @return metric
     */
    Meter getDesktopsCreated();

    /**
     * Counts the number of desktops that have been destroyed.
     * 
     * @return metric
     */
    Meter getDesktopsDestroyed();

    /**
     * Measures the duration of event processing work.
     * 
     * @return metric
     */
    Timer getEventProcessedDuration();

    /**
     * Counts the number of events that have been posted (Events.postEvent).
     * 
     * @return metric
     */
    Meter getEventsPosted();

    /**
     * Counts the number of events that have been processed.
     * 
     * @return metric
     */
    Meter getEventsProcessed();

    /**
     * Counts the number of events that have been sent (Events.sendEvent).
     * 
     * @return metric
     */
    Meter getEventsSent();

    /**
     * Measures the durations of server-side request processing.
     * 
     * @return metric
     */
    Timer getServerRequestCompletedDuration();

    /**
     * Counts the number of sessions that have been created.
     * 
     * @return metric
     */
    Meter getSessionsCreated();

    /**
     * Counts the number of sessions that have been destroyed.
     * 
     * @return metric
     */
    Meter getSessionsDestroyed();

    /**
     * Counts the number of AJAX updates that have been processed.
     * 
     * @return metric
     */
    Meter getUpdatesProcessed();

    /**
     * Counts the number of AJAX updates that have been started.
     * 
     * @return metric
     */
    Meter getUpdatesStarted();

}
