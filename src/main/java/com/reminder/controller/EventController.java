package com.reminder.controller;

import com.reminder.dto.EventDto;
import com.reminder.entity.Event;
import com.reminder.service.EventService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//TODO move base resource 'api/v1' to Constants
@RestController
@RequestMapping("api/v1/events")
public class EventController {

    @Autowired
    private EventService eventService;

    //TODO move in BaseController
    @Autowired
    private MapperFacade mapper;

    //TODO provide filter with pageable parameter, to avoid performance issues
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(mapper.mapAsList(eventService.findAll(), EventDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@PathVariable String id) {
        return ResponseEntity.ok(eventService.findOne(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        eventService.remove(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> create(@RequestBody EventDto dto) {
        eventService.saveOrUpdate(mapper.map(dto, Event.class));
        return ResponseEntity.ok(dto);
    }

}
