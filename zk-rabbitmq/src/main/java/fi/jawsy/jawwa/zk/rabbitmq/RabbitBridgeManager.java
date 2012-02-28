package fi.jawsy.jawwa.zk.rabbitmq;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueues;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

@RequiredArgsConstructor
public class RabbitBridgeManager {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final ConcurrentMap<String, RabbitBridge> bridges = new ConcurrentHashMap<String, RabbitBridge>();
    private final ReentrantReadWriteLock connectionLock = new ReentrantReadWriteLock();
    private final ScheduledExecutorService reconnectionExecutor = Executors.newScheduledThreadPool(1);
    private final ExecutorService connectionExecutor = Executors.newSingleThreadExecutor();
    private final ShutdownListener shutdownListener = new ShutdownListener() {
        @Override
        public void shutdownCompleted(ShutdownSignalException cause) {
            if (!cause.isInitiatedByApplication()) {
                log.debug("Connection lost", cause);
                try {
                    setConnection(null);
                } catch (Exception e) {
                    throw new RabbitBridgeException("Failed to clear connection", e);
                } finally {
                    scheduleReconnect();
                }
            }
        }
    };

    private final ConnectionFactory connectionFactory;
    private final RabbitBridgeSerializer serializer;
    private final WebApp webApp;
    private final int reconnectDelayMillis;
    private final int reconnectTimeoutMillis;

    private Connection connection;

    private void scheduleReconnect() {
        log.debug("Attempting reconnection in {} milliseconds", reconnectDelayMillis);
        reconnectionExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    ensureConnectivity();
                } catch (Exception e) {
                    log.error("Reconnection failed", e);
                    scheduleReconnect();
                }
            }
        }, reconnectDelayMillis, TimeUnit.MILLISECONDS);
    }

    private void setConnection(Connection connection) throws IOException {
        if (connection != null) {
            connection.addShutdownListener(shutdownListener);
            log.debug("Established new connection: {}", connection);
        }
        connectionLock.writeLock().lock();
        try {
            this.connection = connection;
            for (val bridge : bridges.values()) {
                if (connection == null)
                    bridge.clearConnection();
                else
                    bridge.setConnection(connection);
            }
        } finally {
            connectionLock.writeLock().unlock();
        }
    }

    void ensureConnectivity() {
        connectionLock.readLock().lock();
        try {
            if (this.connection != null)
                return;
        } finally {
            connectionLock.readLock().unlock();
        }

        try {
            setConnection(connectionExecutor.submit(new Callable<Connection>() {
                @Override
                public Connection call() throws Exception {
                    log.debug("Attempting to connect to broker");
                    return connectionFactory.newConnection();
                }
            }).get(reconnectTimeoutMillis, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            throw new RabbitBridgeException("Failed to establish connection", e);
        }
    }

    public boolean addBridge(String name) throws RabbitBridgeException {
        if (bridges.containsKey(name))
            return false;

        ensureConnectivity();

        try {
            val zkQueue = EventQueues.lookup(name, webApp, true);

            val bridge = new RabbitBridge(name, zkQueue, serializer);
            if (bridges.putIfAbsent(name, bridge) != bridge) {
                connectionLock.readLock().lock();
                try {
                    if (this.connection != null)
                        bridge.setConnection(this.connection);
                } finally {
                    connectionLock.readLock().unlock();
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RabbitBridgeException("Bridging failed", e);
        }
    }

    public void publish(String name, Event event) throws RabbitBridgeException {
        ensureConnectivity();

        try {
            val bridge = bridges.get(name);
            if (bridge == null)
                throw new RabbitBridgeException("No bridge available with name '" + name + "'");

            bridge.publish(event);
        } catch (IOException e) {
            throw new RabbitBridgeException("IO failure", e);
        }
    }

    public boolean isBridged(String name) {
        return bridges.containsKey(name);
    }

    public boolean removeBridge(String name) throws RabbitBridgeException {
        try {
            val bridge = bridges.remove(name);
            if (bridge == null)
                return false;

            bridge.close();

            if (bridges.isEmpty()) {
                closeConnection();
            }

            return true;
        } catch (IOException e) {
            throw new RabbitBridgeException(e);
        }
    }

    private void closeConnection() throws IOException {
        connectionLock.writeLock().lock();
        try {
            if (this.connection != null) {
                this.connection.close();
                this.connection = null;
            }
        } finally {
            connectionLock.writeLock().unlock();
        }
    }

    public void shutdown() {
        reconnectionExecutor.shutdownNow();
        connectionExecutor.shutdownNow();

        try {
            for (val bridge : bridges.values()) {
                bridge.close();
            }

            closeConnection();

        } catch (IOException e) {
            throw new RabbitBridgeException("Shutdown failure", e);
        }
    }

}
