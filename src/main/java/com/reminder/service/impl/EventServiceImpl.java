package com.reminder.service.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reminder.repository.EventRepository;
import com.reminder.entity.Event;
import com.reminder.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;
    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Event save(Event event) {
        Date now = new Date();
        event.setCreatedOn(now);
        event.setUpdatedDate(now);
        return repository.save(event);
    }

    public Event findOne(String id) {
        return repository.findOne(id);
    }

    public void remove(String id) {
        repository.delete(id);
    }

    public void update(String eventId, String body) {
        //todo i not look on logick can work incorrect
        Event event = gson.fromJson(body, Event.class);
        event.setUpdatedDate(new Date());
        repository.save(event);
    }

}
