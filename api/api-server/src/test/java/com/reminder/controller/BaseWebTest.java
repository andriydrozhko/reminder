package com.reminder.controller;

import com.reminder.config.MapperConfiguration;
import ma.glasnost.orika.MapperFacade;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MockServletContext.class, MapperConfiguration.class })
@WebAppConfiguration
public abstract class BaseWebTest {

    @Autowired
    protected MapperFacade mapper;

}