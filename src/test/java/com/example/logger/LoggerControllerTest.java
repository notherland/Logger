package com.example.logger;

import com.example.logger.model.LoggerEntity;
import com.example.logger.service.LoggerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoggerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoggerService loggerService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateLoggerEntry() throws Exception {
        LoggerEntity logEntry = new LoggerEntity();
        logEntry.setLoggerName("security.login.loginPage");
        logEntry.setLevel("ERROR");
        logEntry.setMessage("Успешный вход в систему пользователя \"a.pushkin\"");
        logEntry.setUrl("http://server/login");
        logEntry.setAddInfo("Произвольная информация");
        logEntry.setStackTrace("trace");

        Map<String, String> params = new HashMap<>();
        params.put("username", "a.pushkin");
        params.put("userTimeZone", "Europe/Moscow");
        logEntry.setParams(params);

        String json = objectMapper.writeValueAsString(logEntry);

        mockMvc.perform(post("/logger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        ArgumentCaptor<LoggerEntity> logEntryCaptor = ArgumentCaptor.forClass(LoggerEntity.class);
        verify(loggerService).logEvent(logEntryCaptor.capture(), Mockito.any());
    }

    @Test
    public void testCreateLoggerEntryInvalid() throws Exception {
        mockMvc.perform(post("/logger")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }
}