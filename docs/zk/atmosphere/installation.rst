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
    <artifactId>jawwa-zk-atmosphere</artifactId>
    <release>\ |release|\ </release>
  </dependency>

Apache Ivy
__________

.. parsed-literal::

  <dependency org="fi.jawsy.jawwa" name="jawwa-zk-atmosphere" rev="|release|" />

SBT 0.7
_______

.. parsed-literal::

  val jawwaZkAtmosphere = "fi.jawsy.jawwa" % "jawwa-zk-atmosphere" % "|release|"

SBT 0.10/0.11
_____________

.. parsed-literal::

  libraryDependencies += "fi.jawsy.jawwa" % "jawwa-zk-atmosphere" % "|release|"

Configuring web.xml
-------------------

In order for the server push to work, a servlet needs to be configured in WEB-INF/web.xml:

.. code-block:: xml

  <servlet>
    <servlet-name>AtmosphereServlet</servlet-name>
    <servlet-class>org.atmosphere.cpr.AtmosphereServlet</servlet-class>
    <init-param>
      <param-name>org.atmosphere.cpr.AtmosphereHandler</param-name>
      <param-value>fi.jawsy.jawwa.zk.atmosphere.ZkAtmosphereHandler</param-value>
    </init-param>
    <init-param>
      <param-name>org.atmosphere.cpr.AtmosphereHandler.contextRoot</param-name>
      <param-value>/zkau/comet</param-value>
    </init-param>
    <load-on-startup>0</load-on-startup>
    <!-- Uncomment async-supported if you are using Servlet 3.0 API -->
    <!-- <async-supported>true</async-supported> -->
  </servlet>
  <servlet-mapping>
    <servlet-name>AtmosphereServlet</servlet-name>
    <url-pattern>/zkau/comet</url-pattern>
  </servlet-mapping>

If you are using a Servlet 3.0 -compatible container (e.g. Tomcat 7+, Jetty 8+), you should uncomment the async-supported section.

Here's a complete example (Servlet 3.0):

.. code-block:: xml

  <?xml version="1.0" encoding="UTF-8"?>
  <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee"
    xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" version="3.0">

    <!-- ... Other servlets and listeners ... -->

    <servlet>
      <servlet-name>AtmosphereServlet</servlet-name>
      <servlet-class>org.atmosphere.cpr.AtmosphereServlet</servlet-class>
      <init-param>
        <param-name>org.atmosphere.cpr.AtmosphereHandler</param-name>
        <param-value>fi.jawsy.jawwa.zk.atmosphere.ZkAtmosphereHandler</param-value>
      </init-param>
      <init-param>
        <param-name>org.atmosphere.cpr.AtmosphereHandler.contextRoot</param-name>
        <param-value>/zkau/comet</param-value>
      </init-param>
      <load-on-startup>0</load-on-startup>
      <async-supported>true</async-supported>
    </servlet>
    <servlet-mapping>
      <servlet-name>AtmosphereServlet</servlet-name>
      <url-pattern>/zkau/comet</url-pattern>
    </servlet-mapping>
  </web-app>

*Hint: If you are using Maven-compatible project structure, the file is usually located at src/main/webapp/WEB-INF/web.xml*

Configuring zk.xml
------------------

ZK needs to be configured to use jawwa-zk-atmosphere as the default server push implementation. This is done by adding the following section to the configuration file located at WEB-INF/zk.xml:

.. code-block:: xml

  <device-config>
    <device-type>ajax</device-type>
    <server-push-class>fi.jawsy.jawwa.zk.atmosphere.AtmosphereServerPush</server-push-class>
  </device-config>

Here's a complete example:

.. code-block:: xml

  <?xml version="1.0" encoding="UTF-8"?>
  <zk>
    <device-config>
      <device-type>ajax</device-type>
      <server-push-class>fi.jawsy.jawwa.zk.atmosphere.AtmosphereServerPush</server-push-class>
    </device-config>
  </zk>

*Hint: If you are using Maven-compatible project structure, the file is usually located at src/main/webapp/WEB-INF/zk.xml*
