package com.reminder.service;

import com.reminder.entity.Event;

import java.util.List;

//TODO let's avoid service interfaces
public interface EventService {
     List<Event> findAll();
     Event save(Event event);
     Event saveOrUpdate(Event event);
     Event findOne(String id);
     void remove(String id);
}
