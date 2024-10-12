package com.example.domain.factory;

import com.example.domain.adapter.URLInputAdapter;
import com.example.domain.model.URL;
import com.example.domain.port.URLOutputPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class URLInputAdapterFactoryTest {

    @Test
    void testCreateURLInputAdapter() {

        URLOutputPort mockOutputPort = new URLOutputPort() {
            @Override
            public URL saveUrl(URL url) {
                return url;
            }

            @Override
            public String findUrlByShortenedUrl(String shortenedUrl) {
                return "mockOriginalUrl";
            }

            @Override
            public String shortenUrl(String shortenedUrl) {
                return "mockShortenedUrl";
            }
        };

        URLInputAdapter urlInputAdapter = URLInputAdapterFactory.createURLInputAdapter(mockOutputPort);

        assertNotNull(urlInputAdapter, "The adapter should not be null");

        URL url = new URL();
        url.setOriginalUrl("https://www.google.com/");
        url.setShortenedUrl("abc123");

        assertEquals(url, urlInputAdapter.createUrl(url),"The created URL should be the same as the one provided");

    }
}
