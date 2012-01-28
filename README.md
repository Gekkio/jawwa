jawwa
=================

## Introduction

Jawwa is a multi-module Java library project containing many useful things for Java developers.

## Modules

### Javadoc

Aggregated Javadoc for all modules can be found here:

[http://oss.jawsy.fi/api/jawwa/0.1.0/](http://oss.jawsy.fi/api/jawwa/0.1.0/)

### Maven repository

All modules are deployed in this Maven repository:

    <repository>
      <id>jawsy-releases</id>
      <url>http://oss.jawsy.fi/maven2/releases/</url>
    </repository>

### jawwa-lang

General purpose Java utilities.

#### Maven dependency

    <dependency>
      <groupId>fi.jawsy.jawwa</groupId>
      <artifactId>jawwa-lang</artifactId>
      <version>0.1.0</version>
    </dependency>

### jawwa-zk-atmosphere

Asynchronous ZK server push implementation based on [Atmosphere](https://github.com/Atmosphere/atmosphere)

#### Configuration

zk.xml:

    <device-config>
      <device-type>ajax</device-type>
      <server-push-class>fi.jawsy.jawwa.zk.atmosphere.AtmosphereServerPush</server-push-class>
    </device-config>

web.xml:

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
      <!-- Remove async-supported if you are not using Servlet 3.0 -->
      <async-supported>true</async-supported>
    </servlet>

#### Maven dependency

    <dependency>
      <groupId>fi.jawsy.jawwa</groupId>
      <artifactId>jawwa-zk-atmosphere</artifactId>
      <version>0.1.0</version>
    </dependency>

### jawwa-zk-gritter

Growl-like notifications (uses [Gritter for jQuery](https://github.com/jboesch/Gritter))

#### Maven dependency

    <dependency>
      <groupId>fi.jawsy.jawwa</groupId>
      <artifactId>jawwa-zk-gritter</artifactId>
      <version>0.1.0</version>
    </dependency>

### jawwa-zk-cleditor

Rich-text editor component based on [CLEditor](http://premiumsoftware.net/cleditor/).
Uses [a custom version of CLEditor](https://github.com/Gekkio/cleditor) that makes the editor usable in dynamic websites.

#### Maven dependency

    <dependency>
      <groupId>fi.jawsy.jawwa</groupId>
      <artifactId>jawwa-zk-cleditor</artifactId>
      <version>0.1.0</version>
    </dependency>
