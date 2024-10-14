package com.example.domain.factory;

import com.example.domain.adapter.URLInputAdapter;
import com.example.domain.model.URLModel;
import com.example.domain.port.URLOutputPort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class URLModelInputAdapterFactoryTest {

    @Test
    void testCreateURLInputAdapter() {

        URLOutputPort mockOutputPort = new URLOutputPort() {
            @Override
            public URLModel saveUrl(URLModel url) {
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

        URLModel urlModel = new URLModel();
        urlModel.setOriginalUrl("https://www.google.com/");
        urlModel.setShortenedUrl("abc123");

        assertEquals(urlModel, urlInputAdapter.createUrl(urlModel),"The created URL should be the same as the one provided");

    }
}
