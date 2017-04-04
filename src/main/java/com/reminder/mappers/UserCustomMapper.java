package com.reminder.mappers;

import com.reminder.dto.UserDto;
import com.reminder.entity.User;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class UserCustomMapper extends CustomMapper<UserDto, User> {

    @Override
    public void mapAtoB(UserDto userDto, User user, MappingContext context) {
        user.setUserName(userDto.username);
    }

    @Override
    public void mapBtoA(User user, UserDto userDto, MappingContext context) {
        userDto.username = user.getUserName();
    }
}
