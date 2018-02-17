package com.cloud.base.schedule.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("quartz.xml")
public class CloudScheduleCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudScheduleCenterApplication.class, args);
	}
}
