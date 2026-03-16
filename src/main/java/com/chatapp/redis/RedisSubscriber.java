package com.chatapp.redis;

import org.jspecify.annotations.Nullable;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String msg = new String(message.getBody());
        System.out.println("Received message from Redis: " + msg);
    }
}
