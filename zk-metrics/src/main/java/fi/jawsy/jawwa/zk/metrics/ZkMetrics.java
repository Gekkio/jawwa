package fi.jawsy.jawwa.zk.metrics;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.EventInterceptor;
import org.zkoss.zk.ui.util.Monitor;
import org.zkoss.zk.ui.util.PerformanceMeter;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.yammer.metrics.core.Counter;
import com.yammer.metrics.core.Meter;
import com.yammer.metrics.core.MetricsRegistry;
import com.yammer.metrics.core.Timer;

/**
 * Measures ZK metrics using the ZK-specific measurement interfaces {@link Monitor}, {@link} PerformanceMeter, {@link}
 * EventInterceptor. Tracks requests and events using internal caches with a default expiry time of 30 minutes. The
 * expiry time is not used in normal usage, but is used only to prevent memory leaks in exceptional circumstances.
 * 
 * Subclasses can override cache configuration and metric scope.
 */
public class ZkMetrics implements Monitor, PerformanceMeter, EventInterceptor {

    private final Cache<String, Long> activeClientRequests;
    private final Cache<Event, Long> activeEventsProcessing;
    private final Cache<String, Long> activeServerRequests;

    private final Counter activeDesktops;
    private final Counter activeEvents;
    private final Counter activeSessions;
    private final Counter activeUpdates;
    private final Timer clientRequestCompletedDuration;
    private final Timer clientRequestReceivedDuration;
    private final Meter desktopsCreated;
    private final Meter desktopsDestroyed;
    private final Timer eventProcessedDuration;
    private final Meter eventsPosted;
    private final Meter eventsProcessed;
    private final Meter eventsSent;
    private final Timer serverRequestCompletedDuration;
    private final Meter sessionsCreated;
    private final Meter sessionsDestroyed;
    private final Meter updatesProcessed;
    private final Meter updatesStarted;

    /**
     * Creates a new metrics instance, and registers metrics in the specified {@link MetricsRegistry}
     * 
     * @param metricsRegistry
     *            registry where metrics should be created
     */
    public ZkMetrics(MetricsRegistry metricsRegistry) {
        activeClientRequests = createCache();
        activeEventsProcessing = createCache();
        activeServerRequests = createCache();

        activeDesktops = metricsRegistry.newCounter(ZkMetrics.class, "activeDesktops", getScope());
        activeEvents = metricsRegistry.newCounter(ZkMetrics.class, "activeEvents", getScope());
        activeSessions = metricsRegistry.newCounter(ZkMetrics.class, "activeSessions", getScope());
        activeUpdates = metricsRegistry.newCounter(ZkMetrics.class, "activeUpdates", getScope());

        clientRequestCompletedDuration = metricsRegistry.newTimer(ZkMetrics.class, "clientRequestCompletedDuration", getScope(), TimeUnit.MILLISECONDS,
                TimeUnit.SECONDS);
        clientRequestReceivedDuration = metricsRegistry.newTimer(ZkMetrics.class, "clientRequestReceivedDuration", getScope(), TimeUnit.MILLISECONDS,
                TimeUnit.SECONDS);
        desktopsCreated = metricsRegistry.newMeter(ZkMetrics.class, "desktopsCreated", "desktops", getScope(), TimeUnit.SECONDS);
        desktopsDestroyed = metricsRegistry.newMeter(ZkMetrics.class, "desktopsDestroyed", "desktops", getScope(), TimeUnit.SECONDS);
        eventProcessedDuration = metricsRegistry.newTimer(ZkMetrics.class, "eventProcessingDuration", getScope(), TimeUnit.MILLISECONDS, TimeUnit.SECONDS);
        eventsPosted = metricsRegistry.newMeter(ZkMetrics.class, "eventsPosted", "events", getScope(), TimeUnit.SECONDS);
        eventsProcessed = metricsRegistry.newMeter(ZkMetrics.class, "eventsProcessed", "events", getScope(), TimeUnit.SECONDS);
        eventsSent = metricsRegistry.newMeter(ZkMetrics.class, "eventsSent", "events", getScope(), TimeUnit.SECONDS);
        serverRequestCompletedDuration = metricsRegistry.newTimer(ZkMetrics.class, "serverRequestCompletedDuration", getScope(), TimeUnit.MILLISECONDS,
                TimeUnit.SECONDS);
        sessionsCreated = metricsRegistry.newMeter(ZkMetrics.class, "sessionsCreated", "sessions", getScope(), TimeUnit.SECONDS);
        sessionsDestroyed = metricsRegistry.newMeter(ZkMetrics.class, "sessionsDestroyed", "sessions", getScope(), TimeUnit.SECONDS);
        updatesProcessed = metricsRegistry.newMeter(ZkMetrics.class, "updatesProcessed", "updates", getScope(), TimeUnit.SECONDS);
        updatesStarted = metricsRegistry.newMeter(ZkMetrics.class, "updatesStarted", "updates", getScope(), TimeUnit.SECONDS);
    }

    @Override
    public void afterProcessEvent(Event event) {
        eventsProcessed.mark();
        activeEvents.dec();

        Long startTime = activeEventsProcessing.getIfPresent(event);
        if (startTime == null) {
            return;
        }

        activeEventsProcessing.invalidate(event);
        long length = System.currentTimeMillis() - startTime;

        eventProcessedDuration.update(length, TimeUnit.MILLISECONDS);
    }

    @Override
    public void afterUpdate(Desktop desktop) {
        updatesProcessed.mark();
        activeUpdates.dec();
    }

    @Override
    public Event beforePostEvent(Event event) {
        eventsPosted.mark();
        activeEvents.inc();
        return event;
    }

    @Override
    public Event beforeProcessEvent(Event event) {
        activeEventsProcessing.put(event, System.currentTimeMillis());
        return event;
    }

    @Override
    public Event beforeSendEvent(Event event) {
        eventsSent.mark();
        activeEvents.inc();
        return event;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public void beforeUpdate(Desktop desktop, List requests) {
        updatesStarted.mark();
        activeUpdates.inc();
    }

    @Override
    public void desktopCreated(Desktop desktop) {
        desktopsCreated.mark();
        activeDesktops.inc();
    }

    @Override
    public void desktopDestroyed(Desktop desktop) {
        desktopsDestroyed.mark();
        activeDesktops.dec();
    }

    @Override
    public void requestCompleteAtClient(String requestId, Execution exec, long time) {
        Long startTime = activeClientRequests.getIfPresent(requestId);
        if (startTime == null) {
            return;
        }

        activeClientRequests.invalidate(requestId);
        long length = time - startTime;

        clientRequestCompletedDuration.update(length, TimeUnit.MILLISECONDS);
    }

    @Override
    public void requestCompleteAtServer(String requestId, Execution exec, long time) {
        Long startTime = activeServerRequests.getIfPresent(requestId);
        if (startTime == null) {
            return;
        }

        activeServerRequests.invalidate(requestId);
        long length = time - startTime;

        serverRequestCompletedDuration.update(length, TimeUnit.MILLISECONDS);
    }

    @Override
    public void requestReceiveAtClient(String requestId, Execution exec, long time) {
        Long startTime = activeClientRequests.getIfPresent(requestId);
        if (startTime == null) {
            return;
        }

        long length = time - startTime;

        clientRequestReceivedDuration.update(length, TimeUnit.MILLISECONDS);
    }

    @Override
    public void requestStartAtClient(String requestId, Execution exec, long time) {
        activeClientRequests.put(requestId, time);
    }

    @Override
    public void requestStartAtServer(String requestId, Execution exec, long time) {
        activeServerRequests.put(requestId, time);
    }

    @Override
    public void sessionCreated(Session sess) {
        sessionsCreated.mark();
        activeSessions.inc();
    }

    @Override
    public void sessionDestroyed(Session sess) {
        sessionsDestroyed.mark();
        activeSessions.dec();
    }

    /**
     * Returns the scope used for metric instances. This scope is used in JVM-wide JMX-reporting, so multiple
     * applications need to override this or they will share the same metrics.
     * 
     * @return scope for metrics or null
     */
    protected String getScope() {
        return null;
    }

    /**
     * Creates a cache for internal request/event tracking. Subclasses overriding this method should set at least the
     * concurrency level and the expiry time.
     * 
     * @return built cache
     */
    protected <K, V> Cache<K, V> createCache() {
        return CacheBuilder.newBuilder().concurrencyLevel(32).expireAfterWrite(30, TimeUnit.MINUTES).build();
    }

}
