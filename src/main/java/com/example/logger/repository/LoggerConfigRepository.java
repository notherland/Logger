package com.example.logger.repository;

import com.example.logger.config.LoggerConfig;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerConfigRepository extends JpaRepository<LoggerConfig, String> {
}
