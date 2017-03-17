package com.reminder.controller;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
public class UserController extends BaseController{

    @Autowired
    private MapperFacade mapper;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getUsers() {
//        return ResponseEntity.ok(mapper.mapAsList(eventService.findAll(), ApiUserDto.class));
        return ResponseEntity.ok("");
    }
}
