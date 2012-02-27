package fi.jawsy.jawwa.zk.rabbitmq;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.RequiredArgsConstructor;
import lombok.val;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventQueue;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

@RequiredArgsConstructor
public class RabbitBridge {

    private final String exchangeName;
    private final String routingKey;
    private final EventQueue zkQueue;
    private final RabbitBridgeSerializer serializer;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private Channel channel;

    public RabbitBridge(String exchangeName, EventQueue zkQueue, RabbitBridgeSerializer serializer) {
        this.exchangeName = exchangeName;
        this.routingKey = exchangeName + ".message";
        this.zkQueue = zkQueue;
        this.serializer = serializer;
    }

    public void clearConnection() {
        try {
            lock.writeLock().lock();
            channel = null;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void setConnection(Connection connection) throws IOException {
        try {
            lock.writeLock().lock();

            channel = connection.createChannel();

            channel.exchangeDeclare(exchangeName, "fanout", true);
            val queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchangeName, routingKey);
            channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body) throws IOException {
                    zkQueue.publish(serializer.deserialize(body));
                }
            });
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void publish(Event event) throws IOException {
        try {
            lock.readLock().lock();
            if (channel == null)
                throw new RabbitBridgeException("No AMQP channel");
            channel.basicPublish(exchangeName, routingKey, null, serializer.serialize(event));
        } finally {
            lock.readLock().unlock();
        }
    }

    public void close() {
        try {
            lock.writeLock().lock();
            if (channel != null)
                channel.close();
            channel = null;
        } catch (IOException e) {
            throw new RabbitBridgeException("Failed to close channel", e);
        } finally {
            lock.writeLock().unlock();
        }
    }

}
