package fi.jawsy.jawwa.zk.rabbitmq;

import lombok.val;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitBridgesInit implements WebAppInit, WebAppCleanup {

    @Override
    public void cleanup(WebApp wapp) throws Exception {
        val manager = (RabbitBridgeManager) wapp.removeAttribute(RabbitBridges.ATTR_NAME);
        if (manager != null)
            manager.shutdown();
    }

    @Override
    public void init(WebApp wapp) throws Exception {
        if (wapp.hasAttribute(RabbitBridges.ATTR_NAME))
            return;
        val connectionFactory = new ConnectionFactory();

        val manager = new RabbitBridgeManager(connectionFactory, wapp);
        wapp.setAttribute(RabbitBridges.ATTR_NAME, manager);
    }

}
