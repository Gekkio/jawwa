Jawwa
=====

Maven repository
----------------

All modules can be found in the Jawsy releases repository at http://oss.jawsy.fi/maven2/releases, so it should be included in the configuration of your dependency management tool.

Apache Maven 2.0/3.0
____________________

.. code-block:: xml

  <repository>
    <id>jawsy-releases</id>
    <url>http://oss.jawsy.fi/maven2/releases/</url>
  </repository>

Apache Ivy
__________

.. code-block:: xml

  <ibiblio name="jawsy-releases" m2compatible="true" root="http://oss.jawsy.fi/maven2/releases"/>

SBT 0.7
_______

.. code-block:: scala

  val jawsyMavenReleases = "jawsy-releases" at "http://oss.jawsy.fi/maven2/releases"

SBT 0.10/0.11
_____________

.. code-block:: scala

  resolvers += "jawsy-releases" at "http://oss.jawsy.fi/maven2/releases"

Available modules
-----------------

.. toctree::
  :maxdepth: 1

  lang/index

.. toctree::
  :maxdepth: 2

  zk/index

Miscellaneous
-------------

.. toctree::
  :maxdepth: 1
  
  misc/release-notes
