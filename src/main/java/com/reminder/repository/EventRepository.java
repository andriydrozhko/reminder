package com.reminder.repository;

import com.reminder.entity.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<Event, String> {

//    private final DBCollection collection;
//
//    public EventRepository(DB db) {
//        this.collection = db.getCollection("events");
//    }
//
//    public List<Event> findAll() {
//        List<Event> events = new ArrayList<>();
//        DBCursor dbObjects = collection.find();
//        while (dbObjects.hasNext()) {
//            DBObject dbObject = dbObjects.next();
//            events.add(new Event((BasicDBObject) dbObject));
//        }
//        return events;
//    }
//
//    public Event findOne(String id) {
//        BasicDBObject basicDBObject = new BasicDBObject("_id", new ObjectId(id));
//        return new Event((BasicDBObject) collection.findOne(basicDBObject));
//    }
//
//    public void update(String eventId, Event event) {
//        collection.update(
//                new BasicDBObject("_id", new ObjectId(eventId)),
//                new BasicDBObject("$set", prepareEventDbObject(event))
//        );
//    }
//
//    public void remove(String id) {
//        BasicDBObject query = new BasicDBObject();
//        query.append("_id", new ObjectId(id));
//        collection.remove(query);
//    }
//
//    public void create(Event event) {
//        collection.insert(prepareEventDbObject(event));
//    }
//
//    private BasicDBObject prepareEventDbObject(Event event) {
//        return new BasicDBObject()
//                .append("title", event.getTitle())
//                .append("processedDate", event.getProcessedDate())
//                .append("processed", event.getProcessed())
//                .append("createdOn", event.getCreatedOn())
//                .append("updatedDate", event.getUpdatedDate())
//                .append("eventDate", event.getEventDate());
//    }

}