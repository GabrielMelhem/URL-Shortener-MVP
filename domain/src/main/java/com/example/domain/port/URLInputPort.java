package com.example.domain.port;

import com.example.domain.model.URLModel;

public interface URLInputPort {

    URLModel createUrl(URLModel urlModel);
    String retrieveOriginalUrl(String shortenedUrl);

    String shortenUrl(String originalUrl);
}
