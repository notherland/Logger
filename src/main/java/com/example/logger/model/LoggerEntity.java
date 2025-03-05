package com.example.logger.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "log_event")
public class LoggerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "logger_name")
    private String loggerName;

    @Column(name = "level")
    private String level;

    @Column(name = "message")
    private String message;

    @Column(name = "url")
    private String url;

    @Lob
    @Column(name = "stack_trace", columnDefinition = "TEXT")
    private String stackTrace;

    @Lob
    @Column(name = "add_info", columnDefinition = "TEXT")
    private String addInfo;

    @ElementCollection
    @CollectionTable(name = "log_param", joinColumns = @JoinColumn(name = "log_event_id"))
    @MapKeyColumn(name = "param_name")
    @Column(name = "param_value")
    private Map<String, String> params = new HashMap<>();
}
