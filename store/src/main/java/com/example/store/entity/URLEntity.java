package com.example.store.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "urls",indexes = {
        @Index(name = "idx_shortenedUrl", columnList = "shortenedUrl")
})
@Getter
@Setter
public class URLEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2048)
    private String originalUrl;

    @Column(nullable = false)
    private String shortenedUrl;



}
