package fi.jawsy.jawwa.zk.rabbitmq;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import lombok.val;

import org.zkoss.zk.ui.event.Event;

public interface RabbitBridgeSerializer {

    byte[] serialize(Event event);

    Event deserialize(byte[] bytes);

    public static class Default implements RabbitBridgeSerializer {

        private static final int DEFAULT_BUFFER_SIZE = 1024 * 2;

        @Override
        public byte[] serialize(Event event) {
            try {
                val byteOut = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
                val out = new ObjectOutputStream(byteOut);

                out.writeObject(event);

                return byteOut.toByteArray();
            } catch (Exception e) {
                throw new RabbitBridgeException("Serialization failure", e);
            }
        }

        @Override
        public Event deserialize(byte[] bytes) {
            val byteIn = new ByteArrayInputStream(bytes);
            try {
                val in = new ObjectInputStream(byteIn);

                return (Event) in.readObject();
            } catch (Exception e) {
                throw new RabbitBridgeException("Deserialization failure", e);
            }
        }

    }

}
