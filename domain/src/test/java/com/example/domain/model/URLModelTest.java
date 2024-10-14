package com.example.domain.model;

import com.example.domain.model.URLModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class URLModelTest {

    @Test
    void testSettersAndGetters() {

        URLModel urlModel = new URLModel();
        urlModel.setOriginalUrl("https://www.google.com/");
        urlModel.setShortenedUrl("abc123");

        assertEquals("https://www.google.com/", urlModel.getOriginalUrl());
        assertEquals("abc123", urlModel.getShortenedUrl());
    }
}
