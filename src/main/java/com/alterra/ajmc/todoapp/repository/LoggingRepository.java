package com.alterra.ajmc.todoapp.repository;

import com.alterra.ajmc.todoapp.model.Logging;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoggingRepository extends MongoRepository<Logging, String> {
}
