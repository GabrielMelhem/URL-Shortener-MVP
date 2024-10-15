package com.example.store.adapter;

import com.example.domain.model.URLModel;
import com.example.store.service.URLService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class URLOutputAdapterTest {

    private URLService urlService;
    private URLOutputAdapter urlOutputAdapter;

    @BeforeEach
    void setUp() {
        urlService = mock(URLService.class);
        urlOutputAdapter = new URLOutputAdapter(urlService);

    }

    @Test
    void testSaveUrl(){
        URLModel urlModel = new URLModel();
        urlModel.setOriginalUrl("https://www.google.com/");
        urlModel.setShortenedUrl("abc123");

        when(urlService.saveUrl(any(URLModel.class))).thenReturn(urlModel);

        URLModel savedUrlModel = urlOutputAdapter.saveUrl(urlModel);

        assertEquals(urlModel.getOriginalUrl(), savedUrlModel.getOriginalUrl());
        assertEquals(urlModel.getShortenedUrl(), savedUrlModel.getShortenedUrl());
        verify(urlService,times(1)).saveUrl(urlModel);
    }

    @Test
    void testFindUrlByShortenedUrl(){

        String shortenedUrl = "abc123";
        String originalUrl = "https://www.google.com/";

        when(urlService.findUrlByShortenedUrl(shortenedUrl)).thenReturn(originalUrl);

        String result = urlOutputAdapter.findUrlByShortenedUrl(shortenedUrl);

        assertEquals(originalUrl, result);
        verify(urlService,times(1)).findUrlByShortenedUrl(shortenedUrl);

    }
    @Test
    void testShortenUrl(){
        String originalUrl = "https://www.google.com/";
        String shortenedUrl = "abc123";

        when(urlService.shortenUrl(originalUrl)).thenReturn(shortenedUrl);

        String result = urlOutputAdapter.shortenUrl(originalUrl);

        assertEquals(shortenedUrl, result);
        verify(urlService,times(1)).shortenUrl(originalUrl);
    }
}
