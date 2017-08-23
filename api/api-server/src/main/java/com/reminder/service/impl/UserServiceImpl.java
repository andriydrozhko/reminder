package com.reminder.service.impl;

import com.reminder.entity.User;
import com.reminder.exception.RegistrationException;
import com.reminder.repository.UserRepository;
import com.reminder.service.UserService;
import com.reminder.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }


    @Override
    public User findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public User findOneByEmail(String email) {
        return repository.findOneByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        Date now = new Date();
        user.setUpdatedDate(now);
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public User create(User user) throws RegistrationException {
        validateUser(user);
        return save(user);
    }

    private void validateUser(User user) throws RegistrationException {
        UserValidator.validate(user, usr -> null == repository.findOneByEmail(usr.getEmail()), "This email is already in use");
        UserValidator.validate(user, usr -> null == repository.findOneByUserName(usr.getUserName()), "This user name is already in use");
    }

}
