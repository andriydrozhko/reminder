package com.reminder.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(value = "User data model")
public class UserDto {

    @ApiModelProperty(value = "ID")
    public String id;

    @Email
    @NotBlank
    @ApiModelProperty(value = "Email")
    public String email;

    @NotBlank
    @ApiModelProperty(value = "Name/Login")
    public String userName;

}
