package com.reminder.entity;


import com.mongodb.BasicDBObject;

import java.util.Date;

public class Event {

    private String id;
    private String title;
    private Date eventDate;
    private Date createdOn;
    private Date updatedDate;
    private Boolean processed = false;
    private Date processedDate;

    public Event(BasicDBObject dbObject) {
        this.id = dbObject.get("_id").toString();
        this.title = dbObject.getString("title");
        this.eventDate = dbObject.getDate("eventDate");
        this.updatedDate = dbObject.getDate("updatedDate");
        this.createdOn = dbObject.getDate("createdOn");
        this.processed = dbObject.getBoolean("processed");
        this.createdOn = dbObject.getDate("processedDate");
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public Date getProcessedDate() {
        return processedDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public void setProcessedDate(Date processedDate) {
        this.processedDate = processedDate;
    }

}
