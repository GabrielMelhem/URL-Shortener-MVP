package com.example.store.adapter;

import com.example.domain.model.URLModel;
import com.example.domain.port.URLOutputPort;
import com.example.store.service.URLService;
import org.springframework.stereotype.Component;

@Component
public class URLOutputAdapter implements URLOutputPort {

    private final URLService urlService;

    public URLOutputAdapter(URLService urlService) {
        this.urlService = urlService;
    }


    @Override
    public URLModel saveUrl(URLModel urlModel) {
        return urlService.saveUrl(urlModel);
    }

    @Override
    public String findUrlByShortenedUrl(String shortenedUrl) {
        return urlService.findUrlByShortenedUrl(shortenedUrl);
    }

    @Override
    public String shortenUrl(String originalUrl) {
        return urlService.shortenUrl(originalUrl);
    }
}
