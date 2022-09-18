open module exception.handler {

    requires spring.context;
    requires spring.beans;
    requires lombok;
    requires spring.web;
    requires java.xml.bind;
    requires java.sql;
    requires spring.webmvc;
    requires org.hibernate.orm.core;
    requires spring.tx;
    requires com.fasterxml.jackson.annotation;
    requires org.apache.tomcat.embed.core;
    exports com.ak.exception.controller;
    exports com.ak.exception.handler;
    exports com.ak.exception.response;
    exports com.ak.exception.exception;
    exports com.ak.exception.constrants;
}