package com.reminder.controller.auth;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.reminder.controller.BaseWebTest;
import com.reminder.exception.ExceptionHandlerController;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.basePath;
import static com.reminder.Constants.BASE_API_V1_RESOURCE;

public class AuthControllerTest extends BaseWebTest {

    @InjectMocks
    private AuthController controller;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(
                MockMvcBuilders.standaloneSetup(controller)
                        .setControllerAdvice(new ExceptionHandlerController())
        );
        basePath = BASE_API_V1_RESOURCE;


        ReflectionTestUtils.setField(controller, "mapper", mapper);

    }

    //TODO implement me

}
