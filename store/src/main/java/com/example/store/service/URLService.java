package com.example.store.service;

import com.example.domain.model.URL;
import com.example.store.entity.URLEntity;
import com.example.store.mapper.URLEntityMapper;
import com.example.store.repository.URLRepository;
import jakarta.transaction.Transactional;
import org.hashids.Hashids;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class URLService {

    private final URLRepository urlRepository;
    private final URLEntityMapper urlEntityMapper;

    public URLService(URLRepository urlRepository, URLEntityMapper urlEntityMapper) {
        this.urlRepository = urlRepository;
        this.urlEntityMapper = urlEntityMapper;
    }

    public URL saveUrl(URL url) {
        URLEntity urlEntity = urlEntityMapper.toEntity(url);
        URLEntity savedUrlEntity = urlRepository.save(urlEntity);
        return urlEntityMapper.toDomain(savedUrlEntity);
    }


    public String findUrlByShortenedUrl(String shortenedUrl) {
        return urlRepository.findByShortenedUrl(shortenedUrl)
                .map(URLEntity::getOriginalUrl)
                .orElse(null);
    }


    public String shortenUrl(String originalUrl) {

        Hashids hashids = new Hashids("unique_salt", 6);

        long currentTime = System.currentTimeMillis();
        String shortenedUrl = hashids.encode(currentTime);

        URL url = new URL();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl(shortenedUrl);
        url.setCreatedAt(LocalDateTime.now());

        saveUrl(url);

        return shortenedUrl;
    }
}
