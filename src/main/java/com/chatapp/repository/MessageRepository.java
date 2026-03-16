package com.chatapp.repository;

import com.chatapp.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends BaseJpaRepository<Message, Long>{
    List<Message> findByReceiver(String receiver);

}
