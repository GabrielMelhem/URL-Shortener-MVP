package com.example.domain.adapter;

import com.example.domain.model.URL;
import com.example.domain.port.URLOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class URLInputAdapterTest {

    private URLOutputPort urlOutputPort;
    private URLInputAdapter urlInputAdapter;

    @BeforeEach
    public void setUp() {
        urlOutputPort = mock(URLOutputPort.class);
        urlInputAdapter = new URLInputAdapter(urlOutputPort);
    }

    @Test
    void testCreateUrl() {
        URL urlToCreate = new URL();
        when(urlOutputPort.saveUrl(urlToCreate)).thenReturn(urlToCreate);

        URL createdURL = urlInputAdapter.createUrl(urlToCreate);


        assertEquals(urlToCreate, createdURL);
        verify(urlOutputPort).saveUrl(urlToCreate);
    }

    @Test
    void testRetrieveOriginalUrl(){

        String shortenedUrl = "abcd1234";
        String originalUrl = "https://www.google.com/";

        when(urlOutputPort.findUrlByShortenedUrl(shortenedUrl)).thenReturn(originalUrl);

        String retrievedUrl = urlInputAdapter.retrieveOriginalUrl(shortenedUrl);

        assertEquals(originalUrl, retrievedUrl);
        verify(urlOutputPort).findUrlByShortenedUrl(shortenedUrl);
    }

    @Test
    void testShortenUrl(){
        String originalUrl = "https://www.google.com/";
        String shortenedUrl = "abcd1234";

        when(urlOutputPort.shortenUrl(originalUrl)).thenReturn(shortenedUrl);

        String result = urlInputAdapter.shortenUrl(originalUrl);

        assertEquals(shortenedUrl, result);
        verify(urlOutputPort).shortenUrl(originalUrl);
    }
}
