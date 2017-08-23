package com.reminder.controller.auth;

import com.reminder.controller.BaseController;
import com.reminder.dto.SignUpUserDto;
import com.reminder.entity.User;
import com.reminder.exception.RegistrationException;
import com.reminder.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.reminder.Constants.BASE_API_V1_RESOURCE;

@RestController
@RequestMapping(BASE_API_V1_RESOURCE + "/sign-up")
public class AuthController extends BaseController {

    private UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @ApiOperation("Sign up user")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> signUp(@RequestBody @Valid SignUpUserDto dto) throws RegistrationException {
        service.create(mapper.map(dto, User.class));
        return ResponseEntity.ok().build();
    }

}
