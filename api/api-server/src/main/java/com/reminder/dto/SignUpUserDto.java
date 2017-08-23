package com.reminder.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;

@ApiModel("Represent model for user Sign UP")
public class SignUpUserDto {

    @NotBlank
    @ApiModelProperty("User's main login/name")
    public String login;

    @Email
    @NotBlank
    @ApiModelProperty("User's email")
    public String email;

    @NotBlank
    @ApiModelProperty("User's password")
    public String password;

    @NotBlank
    @ApiModelProperty("User's password confirmation")
    public String confirmPassword;

    //Temporary solution
    @AssertTrue(message = "Wrong password confirmation")
    public boolean isConfirmationValid() {
        return null != password && password.equals(confirmPassword);
    }

}
