package com.notification.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotificationServicesApplication {

	public static void main(String[] args) {
		//SpringApplication.run(NotificationServicesApplication.class, args);
		SpringApplication.run((Class<?>[]) new Object[] { NotificationServicesApplication.class }, args);
	}
}
