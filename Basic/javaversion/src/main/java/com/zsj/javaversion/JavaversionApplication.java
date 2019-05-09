package com.zsj.javaversion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class JavaversionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaversionApplication.class, args);
	}

	AnnotationConfigApplicationContext  app = new AnnotationConfigApplicationContext();

}
