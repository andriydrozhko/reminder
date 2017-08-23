package com.reminder.controller.common;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.reminder.controller.BaseWebTest;
import com.reminder.controller.common.EventController;
import com.reminder.dto.EventDto;
import com.reminder.entity.Event;
import com.reminder.exception.ExceptionHandlerController;
import com.reminder.service.EventService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static com.jayway.restassured.http.ContentType.JSON;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.basePath;
import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static com.reminder.Constants.BASE_API_V1_RESOURCE;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class EventControllerTest extends BaseWebTest {

    @InjectMocks
    private EventController controller;

    @Mock
    private EventService eventService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(
                MockMvcBuilders.standaloneSetup(controller)
                        .setControllerAdvice(new ExceptionHandlerController())
        );
        basePath = BASE_API_V1_RESOURCE;

        doReturn(mockEvent(CORRECT_ID)).when(eventService).findOne(CORRECT_ID);
        doReturn(null).when(eventService).findOne(INCORRECT_ID);

        ReflectionTestUtils.setField(controller, "mapper", mapper);

    }

    private static final String CORRECT_ID = "CORRECT_ID";
    private static final String CREATED_ID = "JustCreateId";
    private static final String INCORRECT_ID = "INCORRECT_ID";

    @Test
    public void testSuccessFindAll() {
        given()
                .contentType(JSON)
                .when()
                    .get("/events")
                .then()
                    .statusCode(HttpStatus.OK.value())
                .log()
                .body();

        verify(eventService, times(1)).findAll();
    }

    @Test
    public void testSuccessFineOne() {
        given()
                .contentType(JSON)
                .when()
                    .get("/events/" + CORRECT_ID)
                .then()
                    .statusCode(HttpStatus.OK.value())
                .log()
                .body();

        verify(eventService, times(1)).findOne(CORRECT_ID);
    }

    @Test
    public void testNoContentFineOne() {
        given()
                .contentType(JSON)
                .when()
                    .get("/events/" + INCORRECT_ID)
                .then()
                    .statusCode(HttpStatus.NO_CONTENT.value())
                .log()
                .body();

        verify(eventService, times(1)).findOne(INCORRECT_ID);
    }

    @Test
    public void testSuccessDelete() {
        given()
                .contentType(JSON)
                .when()
                    .delete("/events/" + CORRECT_ID)
                .then()
                    .statusCode(HttpStatus.OK.value())
                .log()
                .body();

        verify(eventService, times(1)).remove(CORRECT_ID);
    }

    @Test
    public void testSuccessDeleteWithIncorrectId() {
        given()
                .contentType(JSON)
                .when()
                    .delete("/events/" + INCORRECT_ID)
                .then()
                    .statusCode(HttpStatus.OK.value())
                .log()
                .body();


        verify(eventService, times(1)).remove(INCORRECT_ID);
    }

    @Test
    public void testSuccessCreate() {
        doReturn(mockEvent(CREATED_ID)).when(eventService).saveOrUpdate(any());

        given()
                .contentType(JSON)
                .body(prepareEventDto(""))
                .when()
                    .post("/events")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .header("Location", Matchers.endsWith(BASE_API_V1_RESOURCE + "/events/" + CREATED_ID))
                .log()
                .body();

        verify(eventService, times(1)).saveOrUpdate(any());
    }

    @Test
    public void testSuccessUpdate() {
        doReturn(mockEvent(CORRECT_ID)).when(eventService).saveOrUpdate(any());
        given()
                .contentType(JSON)
                    .body(prepareEventDto(CORRECT_ID))
                .when()
                    .put("/events")
                .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .header("Location", Matchers.endsWith(BASE_API_V1_RESOURCE + "/events/" + CORRECT_ID))
                .log()
                .body();

        verify(eventService, times(1)).saveOrUpdate(any());
    }

    private EventDto prepareEventDto(String id) {
        EventDto dto = new EventDto();
        dto.id = id;
        dto.createdDate = new Date();
        dto.eventDate = new Date();
        dto.title = "fake title";
        dto.updatedDate = new Date();
        return dto;
    }


    private Event mockEvent(String id) {
        Date now = new Date();
        Event event = mock(Event.class);
        doReturn(id).when(event).getId();
        doReturn(now).when(event).getCreatedDate();
        doReturn(now).when(event).getEventDate();
        doReturn(Boolean.FALSE).when(event).getProcessed();
        doReturn(now).when(event).getProcessedDate();
        doReturn(now).when(event).getUpdatedDate();
        doReturn("fake title").when(event).getTitle();
        return event;
    }

}
