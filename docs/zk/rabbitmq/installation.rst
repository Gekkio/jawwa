Installation
============

The module is available in the Jawsy releases repository, so first make sure you have included it in your configuration.

Adding the dependency
---------------------

Apache Maven 2.0/3.0
____________________

.. parsed-literal::

  <dependency>
    <groupId>fi.jawsy.jawwa</groupId>
    <artifactId>jawwa-zk-rabbitmq</artifactId>
    <release>\ |release|\ </release>
  </dependency>

Apache Ivy
__________

.. parsed-literal::

  <dependency org="fi.jawsy.jawwa" name="jawwa-zk-rabbitmq" rev="|release|" />

SBT 0.7
_______

.. parsed-literal::

  val jawwaZkAtmosphere = "fi.jawsy.jawwa" % "jawwa-zk-rabbitmq" % "|release|"

SBT 0.10/0.11
_____________

.. parsed-literal::

  libraryDependencies += "fi.jawsy.jawwa" % "jawwa-zk-rabbitmq" % "|release|"

Configuring zk.xml
------------------

ZK needs to be configured to use jawwa-zk-rabbitmq as the default server push implementation. This is done by adding the following section to the configuration file located at WEB-INF/zk.xml:

.. code-block:: xml

  <listener>
    <listener-class>fi.jawsy.jawwa.zk.rabbitmq.RabbitBridgesInit</listener-class>
  </listener>

Here's a complete example:

.. code-block:: xml

  <?xml version="1.0" encoding="UTF-8"?>
  <zk>
    <listener>
      <listener-class>fi.jawsy.jawwa.zk.rabbitmq.RabbitBridgesInit</listener-class>
    </listener>
  </zk>

*Hint: If you are using Maven-compatible project structure, the file is usually located at src/main/webapp/WEB-INF/zk.xml*

RabbitMQ installation
---------------------

A working `RabbitMQ <http://www.rabbitmq.com/>`_ installation is required. By default, the module default settings assume a default RabbitMQ installation on the local machine and no extra configuration is needed.
