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
		System.out.println("테스트");
		System.out.println("테스트");
		System.out.println("테스트");
		System.out.println("테스트");
//		System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
	}

}
