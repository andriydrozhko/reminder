package com.reminder.controller;

import com.reminder.dto.UserDto;
import com.reminder.entity.User;
import com.reminder.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.reminder.Constants.BASE_API_V1_RESOURCE;


//TODO should contains 2 data transfer objects (signUpUserDto, baseUserDto) - security purpose
//TODO or create separate AuthController
@Api(description = "User API")
@RestController
@RequestMapping(BASE_API_V1_RESOURCE + "/users")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(mapper.mapAsList(userService.findAll(), UserDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findOne(@PathVariable String id) {
        return ResponseEntity.ok(mapper.map(userService.findOne(id), UserDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<UserDto> create(@RequestBody @Valid UserDto body) {
        User user = userService.saveOrUpdate(mapper.map(body, User.class));
        return ResponseEntity.created(getCreateLocation(user.getId())).build();
    }
}
