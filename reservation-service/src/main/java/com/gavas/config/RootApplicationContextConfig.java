package com.gavas.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = {
        "com.gavas.dao",
        "com.gavas.service",
        "com.gavas.domain",
        "com.gavas.interceptor"
})

@Import({DbConfig.class})
public class RootApplicationContextConfig {

}
