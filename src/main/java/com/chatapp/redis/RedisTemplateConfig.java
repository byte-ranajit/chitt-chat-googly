package com.chatapp.redis;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RedisTemplateConfig {

    private final RedisTemplate<String, String> redisTemplate;

    public void publish(String channel, String message){
        redisTemplate.convertAndSend(channel, message);
    }
}
