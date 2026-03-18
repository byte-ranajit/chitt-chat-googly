package com.chatapp.iservices;

import com.chatapp.model.Message;
import com.chatapp.service.MessageService;

import java.util.List;

public interface IMessageService {
    Message save(Message message);
    void markAsRead(Long messageId);
    List<Message> getAll();
    List<Message> getInbox(String user);
}
