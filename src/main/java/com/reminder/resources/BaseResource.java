package com.reminder.resources;

abstract class BaseResource {

    private static final String API_CONTEXT = "/api/v1";
    static final String JSON_CONTENT_VALUE = "application/json";

    BaseResource() {
        init();
    }

    private void init() {
        declareCreate();
        declareFindAll();
        declareFindOne();
        declareUpdate();
        declareDelete();
    }

    public abstract void declareCreate();
    public abstract void declareFindAll();
    public abstract void declareFindOne();
    public abstract void declareUpdate();
    public abstract void declareDelete();

    protected abstract String getBaseEndpoint();

    String prepareEndPointUrl(String endPoint) {
        return API_CONTEXT + getBaseEndpoint() + endPoint;
    }

    String prepareEndPointUrl() {
        return prepareEndPointUrl("");
    }

}
