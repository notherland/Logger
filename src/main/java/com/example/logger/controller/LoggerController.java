package com.example.logger.controller;

import com.example.logger.model.LoggerEntity;
import com.example.logger.service.LoggerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logger")
public class LoggerController {

    @Autowired
    private LoggerService loggerService;

    @PostMapping
    public ResponseEntity<Void> logEvent(@RequestBody LoggerEntity logEntry, HttpServletRequest request) {
        try {
            loggerService.logEvent(logEntry, request);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
