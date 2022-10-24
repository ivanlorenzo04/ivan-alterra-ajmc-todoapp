package com.alterra.ajmc.todoapp.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.HashMap;

@Data
@NoArgsConstructor
@Document("logging")
public class Logging {

    @Id
    private String id;

    private HashMap<String, Object> data;

    private String type;
}
