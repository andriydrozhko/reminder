package com.reminder.controller;

import com.reminder.dto.UserDto;
import com.reminder.entity.User;
import com.reminder.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final MapperFacade mapper;
    private final UserService userService;

    @Autowired
    public UserController(MapperFacade mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity findAll() {
        return ResponseEntity.ok(mapper.mapAsList(userService.findAll(), UserDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity findOne(@PathVariable String id) {
        return ResponseEntity.ok(mapper.map(userService.findOne(id), UserDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.saveOrUpdate(user);
        return ResponseEntity.ok(user);
    }
}
