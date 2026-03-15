package com.chatapp.service;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PresenceService {
    private final RedisTemplate<String, String> redisTemplate;

    public void userOnline(String userId){
        redisTemplate.opsForValue().set("online: "+userId,"true");
    }

    public void userOffline(String userId){
        redisTemplate.delete("online: "+userId);
    }
}
