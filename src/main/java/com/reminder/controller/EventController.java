package com.reminder.controller;

import com.reminder.entity.Event;
import com.reminder.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
public class EventController {


    private EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    private Event findOne(@PathVariable String id) {
        return eventService.findOne(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    private void delete(@PathVariable String id) {
        eventService.remove(id);
    }

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    private Event create(@RequestBody Event event) {
        return eventService.save(event);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Event> findAll() {
        return eventService.findAll();
    }
}
