package fi.jawsy.jawwa.zk.metrics;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppCleanup;

import com.yammer.metrics.Metrics;

/**
 * Provides a default implementation of ZK metrics.
 * 
 * Metrics will be exported to JMX with names starting with <strong>fi.jawsy.jawwa.zk.metrics:type=ZkMetrics</strong>.
 * Also manages the lifecycle of the default static {@link MetricsRegistry}, so it is shut down properly with the
 * application.
 */
public class DefaultZkMetrics extends ZkMetrics implements WebAppCleanup {

    public DefaultZkMetrics() {
        super(Metrics.defaultRegistry());
    }

    @Override
    public void cleanup(WebApp wapp) throws Exception {
        Metrics.shutdown();
    }

}
