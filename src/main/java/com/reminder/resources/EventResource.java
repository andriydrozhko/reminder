package com.reminder.resources;

import com.mongodb.DB;
import com.reminder.entity.Event;
import com.reminder.service.EventService;
import com.reminder.util.JsonTransformer;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;

import java.util.List;

import static spark.Spark.delete;
import static spark.Spark.post;
import static spark.Spark.get;
import static spark.Spark.put;

@ResourceHandler
public class EventResource extends BaseResource {

    private final EventService eventService;

    public EventResource(DB db) {
        this.eventService = new EventService(db);
    }

    private static final String BASE_ENDPOINT = "/events";

    protected String getBaseEndpoint() {
        return BASE_ENDPOINT;
    }

    public void declareCreate() {
        post(prepareEndPointUrl(), JSON_CONTENT_VALUE, this::create, new JsonTransformer());
    }

    public void declareFindAll() {
        get(prepareEndPointUrl(), JSON_CONTENT_VALUE, this::findAll, new JsonTransformer());
    }

    public void declareFindOne() {
        get(prepareEndPointUrl("/:id"), JSON_CONTENT_VALUE, this::findOne, new JsonTransformer());
    }

    public void declareUpdate() {
        put(prepareEndPointUrl("/:id"),  JSON_CONTENT_VALUE, this::update,  new JsonTransformer());
    }

    public void declareDelete() {
        delete(prepareEndPointUrl("/:id"),  JSON_CONTENT_VALUE, this::deleteOne,  new JsonTransformer());
    }

    private Event findOne(Request request, Response response) {
        return eventService.findOne(request.params(":id"));
    }

    private Response update(Request request, Response response) {
//        eventService.update(request.params(":id"), request.body());
        return response;
    }

    private Response create(Request request, Response response) {
        eventService.createEvent(request.body());
        response.status(HttpStatus.CREATED_201);
        return response;
    }

    private List<Event> findAll(Request request, Response response) {
        return eventService.findAll();
    }

    private Response deleteOne(Request request, Response response) {
        eventService.remove(request.params(":id"));
        response.status(200);
        return response;
    }

}
