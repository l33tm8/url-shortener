package ru.ilya.urlshortener.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.ilya.urlshortener.models.Url;

@Repository
public interface UrlRepository extends CrudRepository<Url, Long> {

}
