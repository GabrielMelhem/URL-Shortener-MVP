package com.example.boundary.controller;

import com.example.boundary.dto.URLDTO;
import com.example.boundary.mapper.URLDTOMapper;
import com.example.domain.model.URLModel;
import com.example.domain.port.URLInputPort;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class URLController {

    private static final Logger logger = LoggerFactory.getLogger(URLController.class);

    private final URLInputPort urlInputPort;
    private final URLDTOMapper urlDTOMapper;



    public URLController(URLInputPort urlInputPort, URLDTOMapper urlDTOMapper) {
        this.urlInputPort = urlInputPort;
        this.urlDTOMapper = urlDTOMapper;
    }

    @RateLimiter(name = "urlRateLimiter")
    @PostMapping("/shorten")
    public ResponseEntity<URLDTO> shortenUrl(@RequestBody URLDTO urlDTO) {
        logger.info("Received request  to shorten URL: {}", urlDTO.getOriginalUrl());

        URLModel domainURLModel = urlDTOMapper.toDomain(urlDTO);
        String createdIdentifier= urlInputPort.shortenUrl(domainURLModel.getOriginalUrl());

        URLDTO responseDTO = new URLDTO();
        responseDTO.setOriginalUrl(domainURLModel.getOriginalUrl());
        responseDTO.setShortenedUrl(createdIdentifier);

        return ResponseEntity.ok(responseDTO);
    }

    @RateLimiter(name = "urlRateLimiter")
    @GetMapping("/resolve/{shortenedUrl}")
    public ResponseEntity<URLDTO> resolveUrl(@PathVariable String shortenedUrl) {
        logger.info("Resolving URL with shortenedUrl: {}", shortenedUrl);

        String originalUrl = urlInputPort.retrieveOriginalUrl(shortenedUrl);

        URLDTO urldto = new URLDTO();
        urldto.setOriginalUrl(originalUrl);
        urldto.setShortenedUrl(shortenedUrl);

        return ResponseEntity.ok(urldto);
    }
}
