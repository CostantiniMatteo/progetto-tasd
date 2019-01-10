package com.tasd.applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ApplicationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationsApplication.class, args);
	}

}

