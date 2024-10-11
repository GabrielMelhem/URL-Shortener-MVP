package com.example.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class URL {

    private String originalUrl;
    private String shortenedUrl;
    private LocalDateTime createdAt;
}
