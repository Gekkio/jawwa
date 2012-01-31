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
    <artifactId>jawwa-lang</artifactId>
    <release>\ |release|\ </release>
  </dependency>

Apache Ivy
__________

.. parsed-literal::

  <dependency org="fi.jawsy.jawwa" name="jawwa-lang" rev="|release|" />

SBT 0.7
_______

.. parsed-literal::

  val jawwaLang = "fi.jawsy.jawwa" % "jawwa-lang" % "|release|"

SBT 0.10/0.11
_____________

.. parsed-literal::

  libraryDependencies += "fi.jawsy.jawwa" % "jawwa-lang" % "|release|"
