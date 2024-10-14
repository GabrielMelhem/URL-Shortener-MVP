package com.example.boundary.mapper;

import com.example.boundary.dto.URLDTO;
import com.example.domain.model.URLModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class URLDTOMapperTest {

    private final URLDTOMapper urldtoMapper = Mappers.getMapper(URLDTOMapper.class);

    @Test
    void testToDTO() {
        URLModel domainUrlModel = new URLModel();
        domainUrlModel.setOriginalUrl("https://www.google.com");
        domainUrlModel.setShortenedUrl("abc123");

        URLDTO urldto = urldtoMapper.toDTO(domainUrlModel);

        assertNotNull(urldto);
        assertEquals(domainUrlModel.getOriginalUrl(), urldto.getOriginalUrl());
        assertEquals(domainUrlModel.getShortenedUrl(), urldto.getShortenedUrl());
    }

    @Test
    void testToDomain() {
        URLDTO urldto = new URLDTO();
        urldto.setOriginalUrl("https://www.google.com");
        urldto.setShortenedUrl("abc123");

        URLModel domainUrlModel = urldtoMapper.toDomain(urldto);

        assertNotNull(domainUrlModel);
        assertEquals(urldto.getOriginalUrl(), domainUrlModel.getOriginalUrl());
        assertEquals(urldto.getShortenedUrl(), domainUrlModel.getShortenedUrl());
    }
}
