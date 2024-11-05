package ru.ilya.urlshortener.service;

import org.springframework.stereotype.Service;
import ru.ilya.urlshortener.models.Url;
import ru.ilya.urlshortener.repository.UrlRepository;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final BaseService baseService;

    public UrlService(UrlRepository urlRepository, BaseService baseService) {
        this.urlRepository = urlRepository;
        this.baseService = baseService;
    }

    public String getLongUrl(String shortUrl) {
        long id = baseService.decode(shortUrl);
        Url url = urlRepository.findById(id).orElse(null);
        if (url == null) {
            return null;
        }
        return url.getLongUrl();
    }

    public String createShortUrl(String longUrl) {
        if (longUrl == null || longUrl.isEmpty()) {
            return null;
        }
        if (!longUrl.contains("https://") && !longUrl.contains("http://")) {
            longUrl = "https://" + longUrl;
        }
        Url url = new Url();
        url.setLongUrl(longUrl);
        url = urlRepository.save(url);
        return baseService.encode(url.getId());
    }

}
