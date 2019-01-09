package com.tasd.jobcenters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class JobCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobCenterApplication.class, args);
	}

}

