package com.reminder.controller;


import com.reminder.entity.Event;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

//TODO rest controllers are testing in totally different way
public class EventsRestControllerTest {

    //TODO our API should define it's domain by itself
    public static final String EVENT_REST_URI = "http://localhost:8080/api/v1/events";

    //TODO comments means bad code
    //TODO test should represent himself by method names
    /* GET */
    @SuppressWarnings("unchecked")
    private static void getAllEvents(){
        //TODO sys-outs -- bad practice, if you wanted to add some info in logs use Logger
        System.out.println("Testing all events link.");
        RestTemplate restTemplate = new RestTemplate();

        //TODO here we should use mockito
        List<LinkedHashMap<String, Object>> eventsMap = restTemplate.getForObject(EVENT_REST_URI + "/", List.class);

        if(eventsMap != null){
            for(LinkedHashMap<String, Object> map : eventsMap){

            }
        }else{
            System.out.println("No events exist.");
        }
    }

    /* GET */
    private static void getEvent(){
        System.out.println("Testing get Event.");
        RestTemplate restTemplate = new RestTemplate();
        Event event = restTemplate.getForObject(EVENT_REST_URI + "/1", Event.class);
        System.out.println(event);
    }


}
