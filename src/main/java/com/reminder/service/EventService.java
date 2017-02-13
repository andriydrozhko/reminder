package com.reminder.service;


import com.google.gson.Gson;
import com.mongodb.*;
import com.reminder.entity.Event;
import com.reminder.util.EmailService;
import org.bson.types.ObjectId;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventService {

    private final DB db;
    private final DBCollection collection;

    public EventService(DB db) {
        this.db = db;
        this.collection = db.getCollection("events");
    }

    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            events.add(new Event((BasicDBObject) dbObject));
        }
        EmailService.sendMessage("andriydrozhko@gmail.com", "Test subject", "Test text");

        return events;
    }

    public void createEvent(String body) {
        Event event = new Gson().fromJson(body, Event.class);
        collection.insert(new BasicDBObject("title", event.getTitle())
                .append("eventDate", event.getEventDate())
                .append("createdOn", new Date()));
    }

    public Event find(String id) {
        return new Event((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    public void remove(String id) {
        collection.remove(collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

//    public Event update(String todoId, String body) {
//        Event todo = new Gson().fromJson(body, Event.class);
//        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
//        return this.find(todoId);
//    }
}
