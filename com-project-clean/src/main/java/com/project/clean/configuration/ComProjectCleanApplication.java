package com.project.clean.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@ComponentScan("com.project.clean")
public class ComProjectCleanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComProjectCleanApplication.class, args);
	}

}
