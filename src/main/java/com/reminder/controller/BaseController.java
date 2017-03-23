package com.reminder.controller;


import com.reminder.entity.User;
import com.reminder.service.UserService;
import com.reminder.util.SessionUtil;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

public abstract class BaseController {

    @Autowired
    protected MapperFacade mapper;

    @Autowired
    protected UserService userService;

    protected URI getCreateLocation(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    protected User defineLoggedUser() {
        return Optional.ofNullable(SessionUtil.getLoggedUserEmail())
                .map(userService::findOneByEmail)
                .orElseThrow(() -> new AccessDeniedException("Current user has no access"));
    }


}
