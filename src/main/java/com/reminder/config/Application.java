package com.reminder.config;

import com.reminder.resources.ResourceInitializer;

import static spark.Spark.ipAddress;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class Application {

    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null
            ? System.getenv("OPENSHIFT_DIY_IP")
            : "localhost";

    private static final int PORT = System.getenv("OPENSHIFT_DIY_IP") != null
            ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_IP"))
            : 8765;

    public static void main(String[] args) throws Exception {
        ipAddress(IP_ADDRESS);
        port(PORT);
        staticFileLocation("/public");

        new ResourceInitializer().initialize();
    }

}
