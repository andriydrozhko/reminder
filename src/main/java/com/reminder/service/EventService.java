package com.reminder.service;

import com.reminder.entity.Event;

import java.util.List;

public interface EventService {
     List<Event> findAll();
     Event save(Event event);
}
