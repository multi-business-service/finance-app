package com.ak.common.core.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class SynchronousInterceptor extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("filter started");
        preProcess(request);
        filterChain.doFilter(request, response);
        System.out.println("filter ends");
    }

    private void preProcess(HttpServletRequest request) throws IOException {
        log.info(Markers.appendRaw("audit", "IN").and(Markers.appendRaw("payload", IOUtils.toString(request.getReader()))), null);
    }
}
