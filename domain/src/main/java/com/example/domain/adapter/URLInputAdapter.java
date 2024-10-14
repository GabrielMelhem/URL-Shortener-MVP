package com.example.domain.adapter;

import com.example.domain.model.URLModel;
import com.example.domain.port.URLInputPort;
import com.example.domain.port.URLOutputPort;

public class URLInputAdapter implements URLInputPort {

    private final URLOutputPort urlOutputPort;

    public URLInputAdapter(URLOutputPort urlOutputPort) {
        this.urlOutputPort = urlOutputPort;
    }


    @Override
    public URLModel createUrl(URLModel urlModel) {
        return urlOutputPort.saveUrl(urlModel);
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
