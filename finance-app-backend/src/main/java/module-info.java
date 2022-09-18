open module finance.app.backend {
    requires spring.context;
    requires lombok;
    requires spring.web;
    requires spring.beans;
    requires modelmapper;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires spring.tx;
    requires spring.data.jpa;
    requires com.sun.istack.runtime;
    requires com.fasterxml.jackson.annotation;
    requires spring.core;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires java.sql;
    requires spring.aop;
    requires synchronous.core;
    requires exception.handler;
}
