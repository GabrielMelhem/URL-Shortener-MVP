package com.example.domain.adapter;

import com.example.domain.model.URL;
import com.example.domain.port.URLInputPort;
import com.example.domain.port.URLOutputPort;

public class URLInputAdapter implements URLInputPort {

    private final URLOutputPort urlOutputPort;

    public URLInputAdapter(URLOutputPort urlOutputPort) {
        this.urlOutputPort = urlOutputPort;
    }


    @Override
    public URL createUrl(URL url) {
        return urlOutputPort.saveUrl(url);
    }

    @Override
    public String retrieveOriginalUrl(String shortenedUrl) {
        return urlOutputPort.findUrlByShortenedUrl(shortenedUrl);
    }

    @Override
    public String shortenUrl(String originalUrl) {
        return urlOutputPort.shortenUrl(originalUrl);
    }
}
