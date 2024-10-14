package com.example.store.mapper;

import com.example.domain.model.URLModel;
import com.example.store.entity.URLEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class URLModelEntityMapperTest {

    private URLEntityMapper urlEntityMapper;

    @BeforeEach
    void setUp() {
        urlEntityMapper = Mappers.getMapper(URLEntityMapper.class);
    }

    @Test
    void testToEntity() {
        URLModel urlModel = new URLModel();
        urlModel.setOriginalUrl("https://www.google.com/");
        urlModel.setShortenedUrl("abc123");

        URLEntity urlEntity = urlEntityMapper.toEntity(urlModel);

        assertNotNull(urlEntity);
        assertEquals(urlModel.getOriginalUrl(), urlEntity.getOriginalUrl());
        assertEquals(urlModel.getShortenedUrl(), urlEntity.getShortenedUrl());
    }

    @Test
    void testToDomain() {
        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl("https://www.google.com/");
        urlEntity.setShortenedUrl("abc123");

        URLModel urlModel = urlEntityMapper.toDomain(urlEntity);

        assertNotNull(urlModel);
        assertEquals(urlModel.getOriginalUrl(), urlEntity.getOriginalUrl());
        assertEquals(urlModel.getShortenedUrl(), urlEntity.getShortenedUrl());
    }
}
