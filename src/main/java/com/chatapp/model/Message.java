package com.chatapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private String sender;
    private String receiver;
    private String content;
    private String status;
    private LocalDateTime timeStamp;
}
