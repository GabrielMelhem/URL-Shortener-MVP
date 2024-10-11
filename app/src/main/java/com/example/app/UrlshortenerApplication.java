package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.example.app",
        "com.example.boundary",
        "com.example.domain",
        "com.example.store"
})
@EntityScan(basePackages ="com.example.store.entity")
@EnableJpaRepositories(basePackages ="com.example.store.repository")
@PropertySource("classpath:env.properties")
public class UrlshortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlshortenerApplication.class, args);
    }
}
