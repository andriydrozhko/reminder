package com.reminder.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Event data model")
public class EventDto {

    @ApiModelProperty(value = "ID")
    public String id;

    @ApiModelProperty(value = "Title")
    public String title;

    @ApiModelProperty(value = "Event Date")
    public Date eventDate;

    @ApiModelProperty(value = "Created On")
    public Date createdDate;

    @ApiModelProperty(value = "Updated On")
    public Date updatedDate;

    @ApiModelProperty(value = "Processed status")
    public Boolean processed = Boolean.FALSE;

    @ApiModelProperty(value = "Processed Date")
    public Date processedDate;

}
