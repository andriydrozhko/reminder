package com.reminder.resources;

import com.reminder.service.EventService;
import com.reminder.util.JsonTransformer;
import spark.Spark;

import static spark.Spark.delete;
import static spark.Spark.get;

public class EventResource extends BaseResource{

    private final EventService eventService;

    public EventResource(EventService eventService) {
        this.eventService = eventService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        Spark.post(API_CONTEXT + "/events", "application/json", (request, response) -> {
            eventService.createEvent(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/events/:id", "application/json", (request, response)

                -> eventService.find(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "/events", "application/json", (request, response)

                -> eventService.findAll(), new JsonTransformer());

        delete(API_CONTEXT + "/events/:id",  "application/json", (request, response) -> {
            eventService.remove(request.params(":id"));
            response.status(200);
            return response;
        },  new JsonTransformer());

//        put(API_CONTEXT + "/todos/:id", "application/json", (request, response)
//
//                -> eventService.update(request.params(":id"), request.body()), new JsonTransformer());
    }

}
