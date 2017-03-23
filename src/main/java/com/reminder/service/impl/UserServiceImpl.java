package com.reminder.service.impl;

import com.google.common.collect.Sets;
import com.reminder.entity.User;
import com.reminder.enums.UserRole;
import com.reminder.repository.UserRepository;
import com.reminder.service.UserService;
import com.reminder.util.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User findOne(String id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findOneByEmail(String email) {
        return StringUtils.isEmpty(email) ? null : userRepository.findOneByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveOrUpdate(User user) {
        boolean creationFlow = null == user.getId();
        Date now = new Date();
        if (creationFlow) user.setCreatedDate(now);
        user.setUpdatedDate(now);
        user.setPassword(CipherUtil.encrypt(user.getPassword()));
        user.setRoles(Sets.newHashSet(UserRole.USER));
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }
}
