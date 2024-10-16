package com.example.store.service;

import com.example.domain.exception.InvalidURLException;
import com.example.domain.exception.NotFoundException;
import com.example.domain.model.URLModel;
import com.example.store.entity.URLEntity;
import com.example.store.mapper.URLEntityMapper;
import com.example.store.repository.URLRepository;
import com.github.benmanes.caffeine.cache.Cache;
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

    @Mock
    private Cache<String,String> urlCache;

    @InjectMocks
    private URLService urlService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUrl(){
        URLModel domainUrlModel = new URLModel();
        domainUrlModel.setOriginalUrl("https://www.google.com/");

        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl("https://www.google.com/");

        when(urlEntityMapper.toEntity(domainUrlModel)).thenReturn(urlEntity);
        when(urlRepository.save(urlEntity)).thenReturn(urlEntity);
        when(urlEntityMapper.toDomain(urlEntity)).thenReturn(domainUrlModel);

        URLModel result = urlService.saveUrl(domainUrlModel);
        assertEquals("https://www.google.com/", result.getOriginalUrl());
        verify(urlRepository).save(urlEntity);
    }

    @Test
    void testFindUrlByShortenedUrl_Success_CacheHit(){
        String shortenedUrl = "abc123";
        String originalUrl = "https://www.google.com/";

        // Simulate cache hit
        when(urlCache.getIfPresent(shortenedUrl)).thenReturn(originalUrl);

        String result = urlService.findUrlByShortenedUrl(shortenedUrl);
        assertEquals(originalUrl, result);
        verify(urlRepository,never()).findByShortenedUrl(shortenedUrl);

    }

    @Test
    void testFindUrlByShortenedUrl_Success_CacheMiss(){
        String shortenedUrl = "abc123";
        String originalUrl = "https://www.google.com/";

        URLEntity urlEntity = new URLEntity();
        urlEntity.setOriginalUrl(originalUrl);

        // Simulate cache miss
        when(urlCache.getIfPresent(shortenedUrl)).thenReturn(null);
        when(urlRepository.findByShortenedUrl(shortenedUrl)).thenReturn(Optional.of(urlEntity));

        String result = urlService.findUrlByShortenedUrl(shortenedUrl);
        assertEquals(originalUrl, result);
        verify(urlCache).put(shortenedUrl, originalUrl);
    }

    @Test
    void testFindUrlByShortenedUrl_NotFound(){
        String shortenedUrl = "abc123";

        when(urlCache.getIfPresent(shortenedUrl)).thenReturn(null);
        when(urlRepository.findByShortenedUrl(shortenedUrl)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> urlService.findUrlByShortenedUrl(shortenedUrl));
    }

    @Test
    void testShortenUrl_Success(){

        String originalUrl = "https://www.google.com/";
        String shortenedUrl = "abc123";

        when(hashids.encode(anyLong())).thenReturn(shortenedUrl);

        URLModel urlModel = new URLModel();
        urlModel.setOriginalUrl(originalUrl);
        urlModel.setShortenedUrl(shortenedUrl);

        URLEntity urlEntity = new URLEntity();
        when(urlEntityMapper.toEntity(any(URLModel.class))).thenReturn(new URLEntity());
        when(urlRepository.save(any(URLEntity.class))).thenReturn(new URLEntity());
        when(urlEntityMapper.toDomain(urlEntity)).thenReturn(urlModel);

        String result = urlService.shortenUrl(originalUrl);

        assertEquals(shortenedUrl, result);
        verify(urlCache).put(shortenedUrl, originalUrl);
    }

    @Test
    void testShortenUrl_InvalidUrl(){
        assertThrows(InvalidURLException.class, () -> urlService.shortenUrl(""));
    }
}
