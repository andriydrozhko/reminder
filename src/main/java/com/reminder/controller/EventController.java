package com.reminder.controller;

import com.reminder.dto.EventDto;
import com.reminder.entity.Event;
import com.reminder.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Optional;

//TODO move base resource 'api/v1' to Constants
@RestController
@PreAuthorize("hasAuthority('API_USER')")
@RequestMapping("api/v1/events")
public class EventController extends BaseController {

    @Autowired
    private EventService eventService;

    //TODO provide filter with pageable parameter, to avoid performance issues
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<EventDto>> findAll() {
        return ResponseEntity.ok(mapper.mapAsList(eventService.findAll(), EventDto.class));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<EventDto> findOne(@PathVariable String id) {
        return Optional.ofNullable(mapper.map(eventService.findOne(id), EventDto.class))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        eventService.remove(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<Void> create(@RequestBody EventDto body) {
        Event event = eventService.saveOrUpdate(mapper.map(body, Event.class));
        return ResponseEntity.created(getCreateLocation(event.getId())).build();
    }

}
