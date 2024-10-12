package com.example.domain.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class URLTest {

    @Test
    void testSettersAndGetters() {

        URL url = new URL();
        url.setOriginalUrl("https://www.google.com/");
        url.setShortenedUrl("abc123");

        assertEquals("https://www.google.com/", url.getOriginalUrl());
        assertEquals("abc123", url.getShortenedUrl());
    }
}
