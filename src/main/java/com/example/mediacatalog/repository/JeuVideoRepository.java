package com.example.mediacatalog.repository;

import com.example.mediacatalog.model.JeuVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JeuVideoRepository extends JpaRepository<JeuVideo, Long> {
}
