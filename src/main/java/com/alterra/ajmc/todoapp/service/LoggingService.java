package com.alterra.ajmc.todoapp.service;

import java.util.HashMap;

public interface LoggingService {
    void createLog(HashMap<String, Object> data, String type);
}
