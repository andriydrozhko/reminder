package com.reminder.service.impl;


import com.reminder.repository.EventRepository;
import com.reminder.entity.Event;
import com.reminder.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

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

    public Event saveOrUpdate(Event event) {
        //TODO provide own custom exception or use hibernate validator on class level due to validation purpose
        assertNotNull(event);
        boolean creationFlow = null == event.getId();
        Date now = new Date();
        if (creationFlow) event.setCreatedDate(now);
        event.setUpdatedDate(now);
        return save(event);
    }

}
