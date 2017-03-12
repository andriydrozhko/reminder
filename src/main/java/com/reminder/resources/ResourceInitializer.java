package com.reminder.resources;

import com.mongodb.*;
import org.reflections.Reflections;
import spark.utils.CollectionUtils;

import java.net.UnknownHostException;
import java.util.Set;
import java.util.logging.Logger;

public class ResourceInitializer {

    private final static Logger LOGGER = Logger.getLogger(ResourceInitializer.class.getName());

    private DB db;

    public void initialize() throws UnknownHostException {
        defineMongo();

        Set<Class<?>> annotatedClasses = new Reflections("com.reminder.resources")
                .getTypesAnnotatedWith(ResourceHandler.class);

        if (!CollectionUtils.isEmpty(annotatedClasses)) {
            annotatedClasses.forEach(this::initResource);
        }

    }

    private void initResource(Class<?> annotated) {
        try {
            Class<?> clazz = Class.forName(annotated.getName());
            clazz.getConstructor(DB.class).newInstance(db);
        } catch (Exception e) {
            //TODO handle exception
        }
    }

    //TODO looks ugly
    private void defineMongo() throws UnknownHostException {
        String host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
        if (host == null) {
            MongoClient mongoClient = new MongoClient("localhost");
            this.db = mongoClient.getDB("eventsapp");
            return;
        }
        int port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
        String dbname = System.getenv("OPENSHIFT_APP_NAME");
        String username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(20).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(host, port), mongoClientOptions);
        mongoClient.setWriteConcern(WriteConcern.SAFE);
        DB db = mongoClient.getDB(dbname);

        if (db.authenticate(username, password.toCharArray())) {
            this.db = db;
        } else {
            throw new RuntimeException("Not able to authenticate with MongoDB");
        }
    }

}
