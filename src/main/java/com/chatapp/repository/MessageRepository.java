package com.chatapp.repository;

import com.chatapp.model.Message;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends BaseJpaRepository<Message, Long>{
}
