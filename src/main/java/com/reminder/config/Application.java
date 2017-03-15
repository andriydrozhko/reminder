package com.reminder.config;

import java.util.Optional;

import static spark.Spark.ipAddress;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {

    //TODO update me
    private static final String IP_ADDRESS = Optional.ofNullable(System.getenv("OPENSHIFT_DIY_IP")).orElse("localhost");
    private static final int PORT = Optional.ofNullable(System.getenv("OPENSHIFT_DIY_IP")).map(Integer::valueOf).orElse(8765);

    public static void main(String[] args) throws Exception {
        ipAddress(IP_ADDRESS);
        port(PORT);
        staticFileLocation("/public");
        new MongoConfigurator().configure();
        new ResourceInitializer().initialize();
    }

}
