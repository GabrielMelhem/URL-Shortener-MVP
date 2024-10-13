package com.example.store.config;

import org.hashids.Hashids;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HashBeanConfig {

    @Bean
    public Hashids hashids() {

        String salt= "unique_salt";
        return new Hashids(salt,6);
    }
}
