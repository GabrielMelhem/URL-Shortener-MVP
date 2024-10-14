package com.example.boundary.controller;

import com.example.boundary.dto.URLDTO;
import com.example.boundary.mapper.URLDTOMapper;
import com.example.domain.model.URLModel;
import com.example.domain.port.URLInputPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class URLController {

    private final URLInputPort urlInputPort;
    private final URLDTOMapper urlDTOMapper;

    public URLController(URLInputPort urlInputPort, URLDTOMapper urlDTOMapper) {
        this.urlInputPort = urlInputPort;
        this.urlDTOMapper = urlDTOMapper;
    }

    @PostMapping("/shorten")
    public ResponseEntity<URLDTO> shortenUrl(@RequestBody URLDTO urlDTO) {

        URLModel domainURLModel = urlDTOMapper.toDomain(urlDTO);
        String createdIdentifier= urlInputPort.shortenUrl(domainURLModel.getOriginalUrl());

        URLDTO responseDTO = new URLDTO();
        responseDTO.setOriginalUrl(domainURLModel.getOriginalUrl());
        responseDTO.setShortenedUrl(createdIdentifier);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/resolve/{shortenedUrl}")
    public ResponseEntity<URLDTO> resolveUrl(@PathVariable String shortenedUrl) {
        String originalUrl = urlInputPort.retrieveOriginalUrl(shortenedUrl);

        URLDTO urldto = new URLDTO();
        urldto.setOriginalUrl(originalUrl);
        urldto.setShortenedUrl(shortenedUrl);

        return ResponseEntity.ok(urldto);
    }
}
