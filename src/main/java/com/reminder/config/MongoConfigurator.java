//package com.reminder.config;
//
//import com.mongodb.DB;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoClientOptions;
//import com.mongodb.ServerAddress;
//import com.mongodb.WriteConcern;
//import spark.utils.StringUtils;
//
//import java.net.UnknownHostException;
//
//class MongoConfigurator {
//
//    private static DB db;
//
//    private Integer port = 3000;
//    private String host = "localhost";
//    private String dbName = "eventsapp";
//    private String username = "";
//    private String password = "";
//    private boolean localMode = true;
//
//    private static final int MAX_CONNECTION_PER_HOST = 20;
//
//    {
//        //TODO update me
//        //TODO need to provide environment modes
//        if (false) {
//            host = System.getenv("OPENSHIFT_MONGODB_DB_HOST");
//            port = Integer.parseInt(System.getenv("OPENSHIFT_MONGODB_DB_PORT"));
//            dbName = System.getenv("OPENSHIFT_APP_NAME");
//            username = System.getenv("OPENSHIFT_MONGODB_DB_USERNAME");
//            password = System.getenv("OPENSHIFT_MONGODB_DB_PASSWORD");
//            localMode = StringUtils.isEmpty(System.getenv("OPENSHIFT_MONGODB_DB_HOST"));
//        }
//
//    }
//
//    static DB getDb() {
//        return db;
//    }
//
//    void configure() throws UnknownHostException {
//        defineDB();
//        authenticate();
//    }
//
//    private void authenticate() {
//        if (!localMode && !db.authenticate(username, password.toCharArray())) {
//            throw new RuntimeException("Not able to authenticate with MongoDB");
//        }
//    }
//
//    //TODO should depends on mode (local, build, production, etc.)
//    private void defineDB() throws UnknownHostException {
//        MongoClientOptions mongoClientOptions = MongoClientOptions.builder()
//                .connectionsPerHost(MAX_CONNECTION_PER_HOST)
//                .build();
//
//        MongoClient mongoClient = localMode
//                ? new MongoClient(host)
//                : new MongoClient(new ServerAddress(host, port), mongoClientOptions);
//
//        mongoClient.setWriteConcern(WriteConcern.SAFE);
//        db = mongoClient.getDB(dbName);
//    }
//
//}
