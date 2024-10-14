package com.example.domain.port;

import com.example.domain.model.URLModel;

public interface URLOutputPort {

    URLModel saveUrl(URLModel urlModel);
    String findUrlByShortenedUrl(String shortenedUrl);

    String shortenUrl(String shortenedUrl);
}
