package com.reminder.service;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.reminder.persistence.EventRepository;
import com.reminder.entity.Event;

import java.util.Date;
import java.util.List;

public class EventService {

    private EventRepository repository;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();

    public EventService(DB db) {
        this.repository = new EventRepository(db);
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public void createEvent(String body) {
        Event event = gson.fromJson(body, Event.class);
        Date now = new Date();
        event.setCreatedOn(now);
        event.setUpdatedDate(now);
        repository.create(event);
    }

    public Event findOne(String id) {
        return repository.findOne(id);
    }

    public void remove(String id) {
        repository.remove(id);
    }

    public void update(String eventId, String body) {
        Event event = gson.fromJson(body, Event.class);
        event.setUpdatedDate(new Date());
        repository.update(eventId, event);
    }

}
