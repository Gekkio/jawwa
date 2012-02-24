package fi.jawsy.jawwa.zk.rabbitmq;

import lombok.val;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.WebApps;

public final class RabbitBridges {

    private RabbitBridges() {
    }

    static final String ATTR_NAME = RabbitBridges.class.getName();

    public static RabbitBridgeManager getManager(WebApp webApp) {
        val manager = (RabbitBridgeManager) webApp.getAttribute(ATTR_NAME);
        if (manager == null)
            throw new RabbitBridgeException("No bridge manager in web app");
        return manager;
    }

    public static boolean addBridge(String name) {
        val webApp = WebApps.getCurrent();
        if (webApp == null)
            throw new RabbitBridgeException("No current web app");
        return getManager(webApp).addBridge(name);
    }

    public static boolean isBridged(String name) {
        val webApp = WebApps.getCurrent();
        if (webApp == null)
            throw new RabbitBridgeException("No current web app");
        return getManager(webApp).isBridged(name);
    }

    public static boolean removeBridge(String name) {
        val webApp = WebApps.getCurrent();
        if (webApp == null)
            throw new RabbitBridgeException("No current web app");
        return getManager(webApp).removeBridge(name);
    }

}
