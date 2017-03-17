package com.reminder.entity;

import org.springframework.data.annotation.Id;

//TODO confirm and provide list of fields
public class User {

    @Id
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
