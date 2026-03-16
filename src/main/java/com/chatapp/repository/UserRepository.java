package com.chatapp.repository;

import com.chatapp.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseJpaRepository<User, Long> {
    public User findByUserName(String userName);
    public boolean existsByUserName(String userName);
}
