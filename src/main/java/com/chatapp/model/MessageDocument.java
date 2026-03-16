package com.chatapp.model;

import jakarta.persistence.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Document(indexName = "messages")
public class MessageDocument {
    @Id
    private String id;
    private String sender;
    private String content;
    private LocalDateTime timeStamp;
}
