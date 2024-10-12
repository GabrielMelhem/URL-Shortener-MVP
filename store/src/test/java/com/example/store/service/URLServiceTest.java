package com.example.store.service;

import com.example.domain.exception.InvalidURLException;
import com.example.domain.exception.NotFoundException;
import com.example.domain.model.URL;
import com.example.store.entity.URLEntity;
import com.example.store.mapper.URLEntityMapper;
import com.example.store.repository.URLRepository;
import org.hashids.Hashids;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class URLServiceTest {

    @Mock
    private URLRepository urlRepository;

    @Mock
    private URLEntityMapper urlEntityMapper;

    @Mock
    private Hashids hashids;

    @InjectMocks
    private URLService urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUrl(){
        URL domainUrl = new URL();
        domainUrl.setOriginalUrl("https://www.google.com/");

        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl("https://www.google.com/");

        when(urlEntityMapper.toEntity(domainUrl)).thenReturn(urlEntity);
        when(urlRepository.save(urlEntity)).thenReturn(urlEntity);
        when(urlEntityMapper.toDomain(urlEntity)).thenReturn(domainUrl);

        URL result = urlService.saveUrl(domainUrl);
        assertEquals("https://www.google.com/", result.getOriginalUrl());
        verify(urlRepository).save(urlEntity);
    }

    @Test
    void testFindUrlByShortenedUrl_Success(){
        String shortenedUrl = "abc123";
        String originalUrl = "https://www.google.com/";

        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl(originalUrl);

        when(urlRepository.findByShortenedUrl(shortenedUrl)).thenReturn(Optional.of(urlEntity));

        String result = urlService.findUrlByShortenedUrl(shortenedUrl);
        assertEquals(originalUrl, result);
    }

    @Test
    void testFindUrlByShortenedUrl_NotFound(){
        String shortenedUrl = "abc123";

        when(urlRepository.findByShortenedUrl(shortenedUrl)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> urlService.findUrlByShortenedUrl(shortenedUrl));
    }

    @Test
    void testShortenUrl_Success(){

        String originalUrl = "https://www.google.com/";
        String shortenedUrl = "abc123";

        when(hashids.encode(anyLong())).thenReturn(shortenedUrl);

        URL url = new URL();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl(shortenedUrl);

        URLEntity urlEntity = new URLEntity();
        when(urlEntityMapper.toEntity(any(URL.class))).thenReturn(new URLEntity());
        when(urlRepository.save(any(URLEntity.class))).thenReturn(new URLEntity());
        when(urlEntityMapper.toDomain(urlEntity)).thenReturn(url);

        String result = urlService.shortenUrl(originalUrl);

        assertEquals(shortenedUrl, result);
    }

    @Test
    void testShortenUrl_InvalidUrl(){
        assertThrows(InvalidURLException.class, () -> urlService.shortenUrl(""));
    }
}
