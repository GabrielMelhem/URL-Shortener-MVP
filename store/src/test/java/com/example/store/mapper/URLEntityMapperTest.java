package com.example.store.mapper;

import com.example.domain.model.URL;
import com.example.store.entity.URLEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class URLEntityMapperTest {

    private URLEntityMapper urlEntityMapper;

    @BeforeEach
    void setUp() {
        urlEntityMapper = Mappers.getMapper(URLEntityMapper.class);
    }

    @Test
    void testToEntity() {
        URL url = new URL();
        url.setOriginalUrl("https://www.google.com/");
        url.setShortenedUrl("abc123");

        URLEntity urlEntity = urlEntityMapper.toEntity(url);

        assertNotNull(urlEntity);
        assertEquals(url.getOriginalUrl(), urlEntity.getOriginalUrl());
        assertEquals(url.getShortenedUrl(), urlEntity.getShortenedUrl());
    }

    @Test
    void testToDomain() {
        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl("https://www.google.com/");
        urlEntity.setShortenedUrl("abc123");

        URL url = urlEntityMapper.toDomain(urlEntity);

        assertNotNull(url);
        assertEquals(url.getOriginalUrl(), urlEntity.getOriginalUrl());
        assertEquals(url.getShortenedUrl(), urlEntity.getShortenedUrl());
    }
}
