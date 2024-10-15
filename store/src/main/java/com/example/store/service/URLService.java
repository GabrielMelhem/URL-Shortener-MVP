package com.example.store.service;

import com.example.domain.exception.InvalidURLException;
import com.example.domain.exception.NotFoundException;
import com.example.domain.model.URLModel;
import com.example.store.entity.URLEntity;
import com.example.store.mapper.URLEntityMapper;
import com.example.store.repository.URLRepository;
import com.github.benmanes.caffeine.cache.Cache;
import jakarta.transaction.Transactional;
import org.hashids.Hashids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;


@Service
@Transactional
public class URLService {

    private static final Logger logger = LoggerFactory.getLogger(URLService.class);

    private final URLRepository urlRepository;
    private final URLEntityMapper urlEntityMapper;
    private final Hashids hashids;
    private final Cache<String,String> urlCache;



    public URLService(URLRepository urlRepository, URLEntityMapper urlEntityMapper, Hashids hashids, Cache<String, String> urlCache) {
        this.urlRepository = urlRepository;
        this.urlEntityMapper = urlEntityMapper;
        this.hashids = hashids;
        this.urlCache = urlCache;
    }

    public URLModel saveUrl(URLModel urlModel) {
        logger.info("Saving URL: {}", urlModel.getOriginalUrl());

        URLEntity urlEntity = urlEntityMapper.toEntity(urlModel);
        URLEntity savedUrlEntity = urlRepository.save(urlEntity);

        return urlEntityMapper.toDomain(savedUrlEntity);
    }


    public String findUrlByShortenedUrl(String shortenedUrl) {
        logger.info("Finding original URL for shortenedUrl: {}", shortenedUrl);

        // Check the cache first
        String originalUrl = urlCache.getIfPresent(shortenedUrl);
        if (originalUrl != null) {
            return originalUrl;
        }

        // If not in cache, retrieve from database
        URLEntity urlEntity = urlRepository.findByShortenedUrl(shortenedUrl)
                .orElseThrow(() -> new NotFoundException("Shortened URL not found"));

        originalUrl = urlEntity.getOriginalUrl();
        // Cache the Original Url
        urlCache.put(shortenedUrl, originalUrl);
        return originalUrl;
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

        URLModel urlModel = new URLModel();
        urlModel.setOriginalUrl(originalUrl);
        urlModel.setShortenedUrl(shortenedUrl);

        // Save the URL to the database
        saveUrl(urlModel);

        // Cache the shortenedUrl and its originalUrl
        urlCache.put(shortenedUrl, originalUrl);

        logger.info("Successfully shortened URL {} to {}", originalUrl, shortenedUrl);
        return shortenedUrl;
    }
}
