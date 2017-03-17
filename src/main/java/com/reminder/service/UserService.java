package com.reminder.service;


import com.reminder.dto.UserDto;
import com.reminder.entity.User;

import java.util.List;

public interface UserService {
    User findOne(String id);
    User findOneByEmail(String email);
    List<User> findAll();
    User save(User user);
    User saveOrUpdate(User user);
    void delete(User user);
    void delete(String id);
}
