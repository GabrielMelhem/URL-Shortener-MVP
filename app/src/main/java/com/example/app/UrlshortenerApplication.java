package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication(scanBasePackages = {
        "com.example.app",
        "com.example.boundary",
        "com.example.domain",
        "com.example.store"
})
@EntityScan(basePackages ="com.example.store.entity")
@EnableJpaRepositories(basePackages ="com.example.store.repository")
public class UrlshortenerApplication {

    private static final Logger logger = LoggerFactory.getLogger(UrlshortenerApplication.class);

    public static void main(String[] args) {
        logger.info("Starting UrlshortenerApplication logger ");
        SpringApplication.run(UrlshortenerApplication.class, args);
        logger.info("UrlshortenerApplication started successfully logger");
    }
}
