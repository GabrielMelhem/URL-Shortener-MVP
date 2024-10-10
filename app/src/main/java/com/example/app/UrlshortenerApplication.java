package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication(scanBasePackages = {
        "com.example.app",
        "com.example.boundary",
        "com.example.domain",
        "com.example.store"
})
@PropertySource("classpath:env.properties")
public class UrlshortenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrlshortenerApplication.class, args);
    }
}
