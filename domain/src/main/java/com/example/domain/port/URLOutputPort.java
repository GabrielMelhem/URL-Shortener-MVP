package com.example.domain.port;

import com.example.domain.model.URL;

public interface URLOutputPort {

    URL saveUrl(URL url);
    String findUrlByShortenedUrl(String shortenedUrl);

    String shortenUrl(String shortenedUrl);
}
