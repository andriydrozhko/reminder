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

    @Override
    public Event findOne(String id) {
        return repository.findOne(id);
    }

    @Override
    public void remove(String id) {
        repository.delete(id);
    }

    @Override
    public Event update(Event event) {
        event.setUpdatedDate(new Date());
        return repository.save(event);
    }

}
