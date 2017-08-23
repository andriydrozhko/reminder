package com.reminder.service;

import com.reminder.entity.User;
import com.reminder.exception.RegistrationException;

import java.util.List;

public interface UserService {
    User findOne(String id);
    User findOneByEmail(String email);
    List<User> findAll();
    User save(User user);
    User create(User user) throws RegistrationException;
    User update(User user);
    void delete(User user);
    void delete(String id);
}
