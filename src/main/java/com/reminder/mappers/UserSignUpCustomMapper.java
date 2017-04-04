package com.reminder.mappers;

import com.google.common.collect.Sets;
import com.reminder.dto.SignUpUserDto;
import com.reminder.entity.User;
import com.reminder.enums.UserRole;
import com.reminder.util.CipherUtil;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;

@Component
public class UserSignUpCustomMapper extends CustomMapper<SignUpUserDto, User> {

    @Override
    public void mapAtoB(SignUpUserDto userDto, User user, MappingContext context) {
        Date now = new Date();
        user.setPassword(CipherUtil.encrypt(userDto.password));
        user.setUpdatedDate(now);
        user.setCreatedDate(now);
        user.setEmail(StringUtils.trimAllWhitespace(userDto.email));
        user.setUserName(StringUtils.trimAllWhitespace(userDto.login));
        user.setRoles(Sets.newHashSet(UserRole.USER));
    }

}
