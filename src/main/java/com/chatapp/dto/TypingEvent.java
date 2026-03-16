package com.chatapp.dto;

import lombok.Data;

@Data
public class TypingEvent {
    private String user;
    private String chatId;
    private String receiver;
    private boolean typing;
}
