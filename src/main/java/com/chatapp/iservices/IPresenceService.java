package com.chatapp.iservices;

public interface IPresenceService {
    void userOnline(String userId);
    void userOffline(String userId);
}
