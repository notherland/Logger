package com.example.logger.config;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "log_configs")
public class LoggerConfig {

    @Id
    private String loggerName;

    private String level;
}
