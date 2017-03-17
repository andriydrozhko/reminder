package com.reminder.repository;


import com.reminder.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User findOneByEmail(String email);
}
