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
    <artifactId>jawwa-zk-metrics</artifactId>
    <release>\ |release|\ </release>
  </dependency>

Apache Ivy
__________

.. parsed-literal::

  <dependency org="fi.jawsy.jawwa" name="jawwa-zk-metrics" rev="|release|" />

SBT 0.7
_______

.. parsed-literal::

  val jawwaZkAtmosphere = "fi.jawsy.jawwa" % "jawwa-zk-metrics" % "|release|"

SBT 0.10/0.11
_____________

.. parsed-literal::

  libraryDependencies += "fi.jawsy.jawwa" % "jawwa-zk-metrics" % "|release|"

Configuring zk.xml
------------------

ZK needs to be configured to use jawwa-zk-metrics as the default server push implementation. This is done by adding the following listener section to the configuration file located at WEB-INF/zk.xml:

.. code-block:: xml

  <?xml version="1.0" encoding="UTF-8"?>
  <zk>
    <listener>
      <listener-class>fi.jawsy.jawwa.zk.metrics.DefaultZkMetrics</listener-class>
    </listener>
  </zk>

*Hint: If you are using Maven-compatible project structure, the file is usually located at src/main/webapp/WEB-INF/zk.xml*
