package com.mypackage.ekart.clientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EkartClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkartClientApplication.class, args);
	}
}
