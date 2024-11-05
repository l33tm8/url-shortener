package ru.ilya.urlshortener.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.ilya.urlshortener.dto.UrlDTO;
import ru.ilya.urlshortener.service.UrlService;

@Controller
public class UrlController {

    private final UrlService urlService;

    @Value("${application.host}")
    private String hostname;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public void redirectToUrl(@PathVariable String shortUrl, HttpServletResponse response) {
        String longUrl = urlService.getLongUrl(shortUrl);
        if (longUrl == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            response.setHeader("Location", longUrl);
            response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        }

    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> createShortUrl(@RequestBody UrlDTO longUrlDTO) {
        if (longUrlDTO == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(hostname + urlService.createShortUrl(longUrlDTO.getLongUrl()));
    }
}
