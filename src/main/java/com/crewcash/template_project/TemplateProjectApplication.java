package com.crewcash.template_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TemplateProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TemplateProjectApplication.class, args);
	}

}
