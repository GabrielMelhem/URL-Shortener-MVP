package com.example.boundary.dto;

import com.example.domain.model.URL;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class URLDTOTest {

    @Test
    void testSettersAndGetters() {
        URLDTO urldto = new URLDTO();
        String originalUrl = "https://www.google.com";
        String shortenedUrl = "abc123";

        urldto.setOriginalUrl(originalUrl);
        urldto.setShortenedUrl(shortenedUrl);

        assertEquals(originalUrl, urldto.getOriginalUrl());
        assertEquals(shortenedUrl, urldto.getShortenedUrl());

    }

}
