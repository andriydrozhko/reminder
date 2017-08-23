package com.reminder.controller;


import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public abstract class BaseController {

    @Autowired
    protected MapperFacade mapper;

    protected URI getCreateLocation(String id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }


}
