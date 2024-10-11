package com.example.boundary.controller;

import com.example.boundary.mapper.URLDTOMapper;
import com.example.domain.port.URLInputPort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/urls")
public class URLController {

    private final URLInputPort urlInputPort;
    private final URLDTOMapper urlDTOMapper;

    public URLController(URLInputPort urlInputPort, URLDTOMapper urlDTOMapper) {
        this.urlInputPort = urlInputPort;
        this.urlDTOMapper = urlDTOMapper;
    }

}
