package com.chatapp.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String receiver;
    private String content;
    private String status;
}
