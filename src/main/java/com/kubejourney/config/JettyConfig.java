package com.kubejourney.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class JettyConfig {

    @Value("${server.port}")
    private String serverPort;


    @Bean
    public ServletWebServerFactory servletContainer() {
        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
        int port = Integer.parseInt(serverPort);
        factory.setPort(port);
        return factory;
    }
}

