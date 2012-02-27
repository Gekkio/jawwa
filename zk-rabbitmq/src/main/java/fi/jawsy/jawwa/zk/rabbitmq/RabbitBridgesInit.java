package fi.jawsy.jawwa.zk.rabbitmq;

import lombok.val;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitBridgesInit implements WebAppInit, WebAppCleanup {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void cleanup(WebApp wapp) throws Exception {
        val manager = (RabbitBridgeManager) wapp.removeAttribute(RabbitBridges.ATTR_NAME);
        if (manager != null) {
            manager.shutdown();
            log.info("RabbitMQ bridge shut down");
        }
    }

    @Override
    public void init(WebApp wapp) throws Exception {
        if (wapp.hasAttribute(RabbitBridges.ATTR_NAME))
            return;
        val connectionFactory = new ConnectionFactory();

        val manager = new RabbitBridgeManager(connectionFactory, getSerializer(), wapp);
        wapp.setAttribute(RabbitBridges.ATTR_NAME, manager);
        log.info("RabbitMQ bridge initialized");
    }

    protected RabbitBridgeSerializer getSerializer() {
        return new RabbitBridgeSerializer.Default();
    }

}
