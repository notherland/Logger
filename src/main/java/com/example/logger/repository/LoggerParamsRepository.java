package com.example.logger.repository;

import com.example.logger.model.LogParam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerParamsRepository extends JpaRepository<LogParam, Long> {
}
