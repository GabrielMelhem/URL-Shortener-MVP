package com.example.domain.port;

import com.example.domain.model.URL;

public interface URLInputPort {

    URL createUrl(URL url);
    String retrieveOriginalUrl(String shortenedUrl);

    String shortenUrl(String originalUrl);
}
