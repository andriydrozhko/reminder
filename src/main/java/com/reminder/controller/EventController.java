package com.reminder.controller;

import com.reminder.entity.Event;
import com.reminder.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {


    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

//    private Event findOne(Request request, Response response) {
//        return eventService.findOne(request.params(":id"));
//    }
//
//    private Response update(Request request, Response response) {
////        eventService.update(request.params(":id"), request.body());
//        return response;
//    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    private Event create(@RequestBody Event event) {
        return eventService.save(event);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Event> findAll() {
        return eventService.findAll();
    }

//    private Response deleteOne(Request request, Response response) {
//        eventService.remove(request.params(":id"));
//        response.status(200);
//        return response;
//    }
}
