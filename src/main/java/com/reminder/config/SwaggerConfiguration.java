package com.reminder.config;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static com.reminder.Constants.AUTHORIZATION_HEADER;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;


@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.reminder.controller"})
public class SwaggerConfiguration {

    private static final String REMINDER_GROUP_NAME = "Reminder";
    private static final String AUTH_GROUP_NAME = "Auth";

    @Bean
    public Docket api() {
        return new Docket(SWAGGER_2)
                .groupName(REMINDER_GROUP_NAME)
                .globalOperationParameters(defineGlobalParameters())
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.reminder.controller.common"))
                .paths(any())
                .build();
    }


    @Bean
    public Docket publicApi() {
        return new Docket(SWAGGER_2)
                .groupName(AUTH_GROUP_NAME)
                .genericModelSubstitutes(ResponseEntity.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.reminder.controller.auth"))
                .paths(any())
                .build();
    }

    private ArrayList<Parameter> defineGlobalParameters() {
        return Lists.newArrayList(new ParameterBuilder()
                .name(AUTHORIZATION_HEADER)
                .parameterType("header")
                .modelRef(new ModelRef("string"))
                .description("Application secret")
                .required(true)
                .build());
    }


}
