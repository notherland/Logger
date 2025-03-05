package com.example.logger.service;

import com.example.logger.config.LoggerConfig;
import com.example.logger.model.LogParam;
import com.example.logger.model.LoggerEntity;
import com.example.logger.repository.LoggerConfigRepository;
import com.example.logger.repository.LoggerParamsRepository;
import com.example.logger.repository.LoggerRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LoggerService {

    @Autowired
    private LoggerRepository loggerRepository;

    @Autowired
    private LoggerConfigRepository loggerConfigRepository;

    @Autowired
    private LoggerParamsRepository logParamsRepository;

    public void logEvent(LoggerEntity logEntry, HttpServletRequest request) {
        Optional<LoggerConfig> configOpt = loggerConfigRepository.findById(logEntry.getLoggerName());
        if (configOpt.isEmpty() || isCritical(logEntry.getLevel(), configOpt.get().getLevel())) {
            shouldLog(logEntry, request);
        }
    }

    private void shouldLog(LoggerEntity logEntry, HttpServletRequest request) {
        logEntry.setIpAddress(request.getRemoteAddr());
        List<LogParam> paramList = new ArrayList<>();

        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            for (String value : entry.getValue()) {
                LogParam logParam = new LogParam();
                logParam.setLoggerEntity(logEntry);
                logParam.setParamName(entry.getKey());
                logParam.setParamValue(value);
                paramList.add(logParam);
            }
        }

        loggerRepository.save(logEntry);
        logParamsRepository.saveAll(paramList);
    }

    private boolean isCritical(String level, String configLevel) {
        String[] levels = {"TRACE", "DEBUG", "INFO", "WARNING", "ERROR"};
        int inputLevelIndex = indexOf(levels, level);
        int configLevelIndex = indexOf(levels, configLevel);
        return inputLevelIndex >= configLevelIndex;
    }

    private int indexOf(String[] levels, String level) {
        for (int i = 0; i < levels.length; i++) {
            if (levels[i].equals(level)) {
                return i;
            }
        }
        return -1;
    }
}