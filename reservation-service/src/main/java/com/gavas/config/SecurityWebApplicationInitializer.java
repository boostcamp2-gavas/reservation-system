package com.gavas.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletContext;

public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    @Override
    protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
        servletContext.addListener(new RequestContextListener());
    }
}

