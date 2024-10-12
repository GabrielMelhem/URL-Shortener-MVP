package com.example.boundary.mapper;

import com.example.boundary.dto.URLDTO;
import com.example.domain.model.URL;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

public class URLDTOMapperTest {

    private final URLDTOMapper urldtoMapper = Mappers.getMapper(URLDTOMapper.class);

    @Test
    void testToDTO() {
        URL domainUrl = new URL();
        domainUrl.setOriginalUrl("https://www.google.com");
        domainUrl.setShortenedUrl("abc123");

        URLDTO urldto = urldtoMapper.toDTO(domainUrl);

        assertNotNull(urldto);
        assertEquals(domainUrl.getOriginalUrl(), urldto.getOriginalUrl());
        assertEquals(domainUrl.getShortenedUrl(), urldto.getShortenedUrl());
    }

    @Test
    void testToDomain() {
        URLDTO urldto = new URLDTO();
        urldto.setOriginalUrl("https://www.google.com");
        urldto.setShortenedUrl("abc123");

        URL domainUrl = urldtoMapper.toDomain(urldto);

        assertNotNull(domainUrl);
        assertEquals(urldto.getOriginalUrl(), domainUrl.getOriginalUrl());
        assertEquals(urldto.getShortenedUrl(), domainUrl.getShortenedUrl());
    }
}
