package com.example.store.repository;

import com.example.store.entity.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface URLRepository extends JpaRepository<URLEntity, Long> {

    Optional<URLEntity> findByShortenedUrl(String shortenedUrl);
}
