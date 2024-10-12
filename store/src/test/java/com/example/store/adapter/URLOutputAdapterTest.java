package com.example.store.adapter;

import com.example.domain.model.URL;
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
        URL url = new URL();
        url.setOriginalUrl("https://www.google.com/");
        url.setShortenedUrl("abc123");

        when(urlService.saveUrl(any(URL.class))).thenReturn(url);

        URL savedUrl = urlOutputAdapter.saveUrl(url);

        assertEquals(url.getOriginalUrl(), savedUrl.getOriginalUrl());
        assertEquals(url.getShortenedUrl(), savedUrl.getShortenedUrl());
        verify(urlService,times(1)).saveUrl(url);
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
