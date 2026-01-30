package com.example.mediacatalog.repository;

import com.example.mediacatalog.model.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CDRepository extends JpaRepository<CD, Long> {
}
