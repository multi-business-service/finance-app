package com.ak.synchronous.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.logstash.logback.marker.Markers;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

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
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        preProcess(requestWrapper);
        filterChain.doFilter(request, response);
    }

    private void preProcess(HttpServletRequest request) throws IOException {

        log.info(Markers.appendRaw("audit", "IN"), "empty");
        log.info(Markers.appendRaw("payload", IOUtils.toString(request.getReader())), null);
        log.info(Markers.appendRaw("audit", "IN").and(Markers.appendRaw("payload", IOUtils.toString(request.getReader()))), null);
    }
}
