package com.synergisticit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AirlinesReservationApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/airlines");
		SpringApplication.run(AirlinesReservationApplication.class, args);
	}

}
