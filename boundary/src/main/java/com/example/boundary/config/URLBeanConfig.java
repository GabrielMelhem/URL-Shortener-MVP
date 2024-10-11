package com.example.boundary.config;

import com.example.domain.factory.URLInputAdapterFactory;
import com.example.domain.port.URLInputPort;
import com.example.domain.port.URLOutputPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class URLBeanConfig {

    @Bean
    public URLInputPort urlInputPort(URLOutputPort urlOutputPort) {
        return URLInputAdapterFactory.createURLInputAdapter(urlOutputPort);
    }
}
