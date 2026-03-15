package com.chatapp.controller;

import com.chatapp.dto.ChatMessage;
import com.chatapp.model.Message;
import com.chatapp.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;

    @MessageMapping("/chat.send")
    @SendTo("/topic/messages")
    public Message sendmessage(ChatMessage chatMessage){
        Message message = new Message();
        message.setSender(chatMessage.getSender());
        message.setReceiver(chatMessage.getReceiver());
        message.setContent(chatMessage.getContent());
        message.setStatus("SENT");
        messageService.save(message);
        return message;
    }

}
