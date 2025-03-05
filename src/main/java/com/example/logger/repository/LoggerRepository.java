package com.example.logger.repository;

import com.example.logger.model.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoggerRepository extends JpaRepository<LoggerEntity, Long> {
}

