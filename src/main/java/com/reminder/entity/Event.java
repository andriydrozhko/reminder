package com.reminder.entity;


import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class Event {
    private String id;
    private String title;
    private Date eventDate;
    private Date createdOn;

    public Event(BasicDBObject dbObject) {
        this.id = ((ObjectId) dbObject.get("_id")).toString();
        this.title = dbObject.getString("title");
        this.eventDate = dbObject.getDate("eventDate");
        this.createdOn = dbObject.getDate("createdOn");
    }

    public String getTitle() {
        return title;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Date getCreateOn() {
        return createdOn;
    }
}
