package com.example.store.service;

import com.example.domain.exception.InvalidURLException;
import com.example.domain.exception.NotFoundException;
import com.example.domain.model.URL;
import com.example.store.entity.URLEntity;
import com.example.store.mapper.URLEntityMapper;
import com.example.store.repository.URLRepository;
import jakarta.transaction.Transactional;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;


@Service
@Transactional
public class URLService {

    private final URLRepository urlRepository;
    private final URLEntityMapper urlEntityMapper;
    private final Hashids hashids;

    public URLService(URLRepository urlRepository, URLEntityMapper urlEntityMapper, Hashids hashids) {
        this.urlRepository = urlRepository;
        this.urlEntityMapper = urlEntityMapper;
        this.hashids = hashids;
    }

    public URL saveUrl(URL url) {
        URLEntity urlEntity = urlEntityMapper.toEntity(url);
        URLEntity savedUrlEntity = urlRepository.save(urlEntity);
        return urlEntityMapper.toDomain(savedUrlEntity);
    }


    public String findUrlByShortenedUrl(String shortenedUrl) {
        return urlRepository.findByShortenedUrl(shortenedUrl)
                .map(URLEntity::getOriginalUrl)
                .orElseThrow(() -> new NotFoundException("Shortened URL not found"));
    }


    public String shortenUrl(String originalUrl) {
        if(originalUrl == null || originalUrl.isEmpty()) {
            throw new InvalidURLException("Invalid URL provided: URL cannot be null or empty");
        }

        try {
            new java.net.URL(originalUrl);
        } catch (MalformedURLException e) {
            throw new InvalidURLException("Invalid URL provided: Malformed URL");
        }


        long currentTime = System.currentTimeMillis();
        String shortenedUrl = hashids.encode(currentTime);

        URL url = new URL();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl(shortenedUrl);

        saveUrl(url);

        return shortenedUrl;
    }
}
