package com.example.store.adapter;

import com.example.domain.model.URL;
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
    public URL saveUrl(URL url) {
        return urlService.saveUrl(url);
    }

    @Override
    public String findUrlByShortenedUrl(String shortenedUrl) {
        return urlService.findUrlByShortenedUrl(shortenedUrl);
    }

    @Override
    public String shortenUrl(String shortenedUrl) {
        return urlService.shortenUrl(shortenedUrl);
    }
}
