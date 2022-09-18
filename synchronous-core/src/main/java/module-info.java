open module synchronous.core {
    requires spring.context;
    requires org.slf4j;
    requires spring.beans;
    requires org.apache.tomcat.embed.core;
    requires spring.web;
    requires spring.core;
    requires logstash.logback.encoder;
    requires lombok;
    requires org.apache.commons.io;
    exports com.ak.synchronous.interceptor;
}