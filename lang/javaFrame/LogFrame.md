# 概述

Java日志框架经历了多次演变，目前主流方案是使用SLF4J作为日志门面，配合具体实现（如Logback或Log4j2）使用。

# Log4j

Apache Log4j是最早的Java日志框架，已停止维护，建议迁移到Log4j2。

# Log4j2

Log4j2是Log4j的重写版本，提供了异步日志、插件化架构等特性，性能优于前代。

# Logback

Logback是SLF4J的原生实现，由Log4j作者开发，是Spring Boot的默认日志框架。

# SLF4J

SLF4J（Simple Logging Facade for Java）是一个日志门面，提供统一的日志API，允许在部署时绑定具体的日志实现。
