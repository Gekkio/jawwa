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
    <artifactId>jawwa-zk-cleditor</artifactId>
    <release>\ |release|\ </release>
  </dependency>

Apache Ivy
__________

.. parsed-literal::

  <dependency org="fi.jawsy.jawwa" name="jawwa-zk-cleditor" rev="|release|" />

SBT 0.7
_______

.. parsed-literal::

  val jawwaZkClEditor = "fi.jawsy.jawwa" % "jawwa-zk-cleditor" % "|release|"

SBT 0.10/0.11
_____________

.. parsed-literal::

  libraryDependencies += "fi.jawsy.jawwa" % "jawwa-zk-cleditor" % "|release|"
