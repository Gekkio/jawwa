Usage
=====

The RabbitMQ module is based on the concept of *bridging* the messaging broker with a ZK-provided application-scoped event queue.

First you must add a bridge:

.. code-block:: java

  RabbitBridges.addBridge("your_queue")

After this you can publish cluster-wide events:

.. code-block:: java

  RabbitBridges.publish("your_queue", new Event("onSomethingHappened", null, null));

A bridge ensures that messages pushed to the RabbitMQ server will automatically be published in the ZK event queue with the same name.
In order to actually listen to events, use the standard ZK event queue mechanism:

.. code-block:: java

  EventQueue eq = EventQueues.lookup("your_queue", EventQueues.APPLICATION, true);
  eq.subscribe(new EventListener() {
    public void onEvent(Event event) {
      // Do something with event
    }
  });
