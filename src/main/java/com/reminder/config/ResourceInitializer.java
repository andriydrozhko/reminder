//package com.reminder.config;
//
//import com.mongodb.DB;
//import com.reminder.controller.ResourceHandler;
//import org.reflections.Reflections;
//import spark.utils.CollectionUtils;
//
//import java.net.UnknownHostException;
//import java.util.Set;
//import java.util.logging.Logger;
//
//class ResourceInitializer {
//
//    private final static Logger LOGGER = Logger.getLogger(ResourceInitializer.class.getName());
//
//    void initialize() throws UnknownHostException {
//        Set<Class<?>> annotatedClasses = new Reflections("com.reminder.controller")
//                .getTypesAnnotatedWith(ResourceHandler.class);
//
//        if (!CollectionUtils.isEmpty(annotatedClasses)) {
//            annotatedClasses.forEach(this::initResource);
//        }
//
//    }
//
//    private void initResource(Class<?> annotated) {
//        try {
//            Class<?> clazz = Class.forName(annotated.getName());
//            clazz.getConstructor(DB.class).newInstance(MongoConfigurator.getDb());
//        } catch (Exception e) {
//            //TODO handle exception
//        }
//    }
//
//}
