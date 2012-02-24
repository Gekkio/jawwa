package fi.jawsy.jawwa.zk.rabbitmq;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueues;

import com.google.common.collect.Maps;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@RequiredArgsConstructor
public class RabbitBridgeManager {

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<String, RabbitBridge> bridges = Maps.newHashMap();
    private final ConnectionFactory connectionFactory;
    private final WebApp webApp;

    private Connection connection;

    void ensureConnectivity() throws IOException {
        if (connection == null) {
            connection = connectionFactory.newConnection();
        }
        if (connection == null) {
            throw new RabbitBridgeException("No AMQP connection");
        }
    }

    public boolean addBridge(String name) throws RabbitBridgeException {
        try {
            lock.writeLock().lock();
            if (bridges.containsKey(name))
                return false;

            ensureConnectivity();

            val zkQueue = EventQueues.lookup(name, webApp, true);

            val bridge = new RabbitBridge(name, zkQueue);
            bridges.put(name, bridge);

            return true;
        } catch (IOException e) {
            throw new RabbitBridgeException("Bridging failed", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void publish(String name, Event event) throws RabbitBridgeException {
        try {
            lock.readLock().lock();

            ensureConnectivity();

            val bridge = bridges.get(name);
            if (bridge == null)
                throw new RabbitBridgeException("No bridge available with name '" + name + "'");

            bridge.publish(event);
        } catch (IOException e) {
            throw new RabbitBridgeException("IO failure", e);
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean isBridged(String name) {
        try {
            lock.readLock().lock();
            return bridges.containsKey(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    public boolean removeBridge(String name) throws RabbitBridgeException {
        try {
            lock.writeLock().lock();
            val bridge = bridges.remove(name);
            if (bridge == null)
                return false;

            bridge.close();

            if (bridges.isEmpty() && connection != null) {
                connection.close();
                connection = null;
            }

            return true;
        } catch (IOException e) {
            throw new RabbitBridgeException(e);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void shutdown() {
        try {
            lock.writeLock().lock();

            for (val bridge : bridges.values()) {
                bridge.close();
            }

            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (IOException e) {
            throw new RabbitBridgeException("Shutdown failure", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

}
