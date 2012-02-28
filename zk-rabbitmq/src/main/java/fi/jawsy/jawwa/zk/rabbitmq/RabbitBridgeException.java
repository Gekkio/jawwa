package fi.jawsy.jawwa.zk.rabbitmq;

public class RabbitBridgeException extends RuntimeException {

    private static final long serialVersionUID = 1088573029518176894L;

    public RabbitBridgeException() {
    }

    public RabbitBridgeException(String message) {
        super(message);
    }

    public RabbitBridgeException(String message, Throwable cause) {
        super(message, cause);
    }

    public RabbitBridgeException(Throwable cause) {
        super(cause);
    }

}
