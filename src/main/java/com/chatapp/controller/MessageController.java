package com.chatapp.controller;

import com.chatapp.model.Message;
import com.chatapp.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public Message sendMessage(@RequestBody Message message){
        return messageService.save(message);
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getAll();
    }

    @PostMapping("/{id}/read")
    public void markAsRead(@PathVariable Long id) {
        messageService.markAsRead(id);
    }
}
