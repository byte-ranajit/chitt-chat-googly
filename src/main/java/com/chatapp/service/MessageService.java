package com.chatapp.service;

import com.chatapp.model.Message;
import com.chatapp.redis.RedisPublisher;
import com.chatapp.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageService {

    private final MessageRepository repository;
    private final RedisPublisher redisPublisher;

    public Message save(Message message){
        message.setStatus("SENT");
        Message saved =  repository.save(message);
        redisPublisher.publish("chat", message.getContent());
        return saved;
    }

    public void markAsRead(Long messageId){

        Message message = repository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        message.setStatus("READ");
        repository.save(message);

    }

    public List<Message> getAll(){
        return repository.findAll();
    }
}