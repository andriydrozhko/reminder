package com.reminder.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TODO implement spring security
//TODO add swagger configuration os smthg like that
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.reminder")
@EnableMongoRepositories("com.reminder.repository")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}