package com.alterra.ajmc.todoapp.service.impl;

import com.alterra.ajmc.todoapp.model.Logging;
import com.alterra.ajmc.todoapp.repository.LoggingRepository;
import com.alterra.ajmc.todoapp.service.LoggingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@Slf4j
public class LoggingServiceImpl implements LoggingService {

    @Autowired
    private LoggingRepository loggingRepository;

    @Override
    public void createLog(HashMap<String, Object> data, String type) {
        try {
            Logging log = new Logging();
            log.setId(UUID.randomUUID().toString());
            log.setData(data);
            log.setType(type);
            loggingRepository.save(log);
        } catch (Exception e) {
            log.error("error logging mongo {}", e.getMessage());
        }
    }
}
