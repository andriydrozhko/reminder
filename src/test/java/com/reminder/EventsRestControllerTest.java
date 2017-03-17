package com.reminder;


import com.reminder.entity.Event;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;
import java.util.List;

public class EventsRestControllerTest {

    public static final String EVENT_REST_URI = "http://localhost:8080/api/v1/events";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void getAllEvents(){
        System.out.println("Testing all events link.");
        RestTemplate restTemplate = new RestTemplate();
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
