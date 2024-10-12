package com.example.boundary.controller;

import com.example.boundary.dto.URLDTO;
import com.example.boundary.mapper.URLDTOMapper;
import com.example.domain.model.URL;
import com.example.domain.port.URLInputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class URLControllerTest {

    @Mock
    private URLInputPort urlInputPort;

    @Mock
    private URLDTOMapper urlDTOMapper;

    @InjectMocks
    private URLController urlController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testShortenUrl(){
        URLDTO urlDTO = new URLDTO();
        urlDTO.setOriginalUrl("https://www.google.com");

        URL domainURL = new URL();
        domainURL.setOriginalUrl("https://www.google.com");
        String createdIdentifieer ="abc123";

        when(urlDTOMapper.toDomain(urlDTO)).thenReturn(domainURL);
        when(urlInputPort.shortenUrl(domainURL.getOriginalUrl())).thenReturn(createdIdentifieer);

        ResponseEntity<URLDTO> response = urlController.shortenUrl(urlDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(createdIdentifieer, response.getBody().getShortenedUrl());
        assertEquals(urlDTO.getOriginalUrl(), response.getBody().getOriginalUrl());

    }

    @Test
    void testResolveUrl(){
        String shortenedUrl = "abc123";
        String originalUrl = "https://www.google.com";

        when(urlInputPort.retrieveOriginalUrl(shortenedUrl)).thenReturn(originalUrl);

        ResponseEntity<URLDTO> response = urlController.resolveUrl(shortenedUrl);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(originalUrl, response.getBody().getOriginalUrl());
        assertEquals(shortenedUrl, response.getBody().getShortenedUrl());
    }
}
