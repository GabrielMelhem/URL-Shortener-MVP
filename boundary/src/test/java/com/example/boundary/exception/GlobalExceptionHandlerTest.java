package com.example.boundary.exception;

import com.example.domain.exception.InvalidURLException;
import com.example.domain.exception.NotFoundException;
import com.example.domain.exception.RateLimitExceededException;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleNotFoundException() {
        NotFoundException exception = new NotFoundException("URL not found");

        ResponseEntity<Object> response = globalExceptionHandler.handleNotFoundException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("URL not found", response.getBody());
    }

    @Test
    void handleInvalidURLException() {
        InvalidURLException exception = new InvalidURLException("URL is invalid");

        ResponseEntity<Object> response = globalExceptionHandler.handleInvalidURLException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("URL is invalid", response.getBody());
    }

    @Test
    void handleRateLimitExceededException() {
        RateLimitExceededException exception = new RateLimitExceededException("Rate limit exceeded");

        ResponseEntity<Object> response = globalExceptionHandler.handleRateLimitExceededException(exception);
        assertNotNull(response);
        assertEquals(HttpStatus.TOO_MANY_REQUESTS, response.getStatusCode());
        assertEquals("Rate limit exceeded", response.getBody());

    }

    @Test
    void handleIllegalArgumentException() {

        IllegalArgumentException exception = new IllegalArgumentException("Illegal argument passed");

        ResponseEntity<Object> response = globalExceptionHandler.handleIllegalArgumentException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Illegal argument passed", response.getBody());
    }

    @Test
    void handleRequestNotPermittedException() {
        RequestNotPermitted requestNotPermitted = mock(RequestNotPermitted.class);
        when(requestNotPermitted.getMessage()).thenReturn("Request not permitted");

        ResponseEntity<Object> response = globalExceptionHandler.handleRequestNotPermittedException(requestNotPermitted);
        assertNotNull(response);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Request not permitted", response.getBody());
    }

    @Test
    void handleException(){
        Exception exception = new Exception("Generic exception occurred");

        ResponseEntity<Object> response = globalExceptionHandler.handleException(exception);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.", response.getBody());
    }

}
