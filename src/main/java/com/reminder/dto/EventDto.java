package com.reminder.dto;

import java.util.Date;

public class EventDto {

    public String id;
    public String title;
    public Date eventDate;
    public Date createdDate;
    public Date updatedDate;
    public Boolean processed = Boolean.FALSE;
    public Date processedDate;

}
