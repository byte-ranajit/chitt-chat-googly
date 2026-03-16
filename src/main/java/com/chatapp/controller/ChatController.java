package com.chatapp.controller;

import com.chatapp.dto.ChatMessage;
import com.chatapp.dto.TypingEvent;
import com.chatapp.model.Message;
import com.chatapp.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ChatController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat.send")
    public void sendmessage(ChatMessage chatMessage){
        Message message = new Message();
        message.setSender(chatMessage.getSender());
        message.setReceiver(chatMessage.getReceiver());
        message.setContent(chatMessage.getContent());
        message.setStatus("SENT");
        messageService.save(message);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getReceiver(),
                "/queue/messages",
                message
        );
    }

    @MessageMapping("/chat.typing")
    public void typing(TypingEvent event){
        messagingTemplate.convertAndSendToUser(
                event.getReceiver(),
                "/queue/typing",
                event
        );
    }

}
