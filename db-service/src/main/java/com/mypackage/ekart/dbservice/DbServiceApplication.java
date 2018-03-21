package com.mypackage.ekart.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@EnableJpaRepositories(basePackages="com.mypackage.ekart.dbservice.repository")
@SpringBootApplication
public class DbServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbServiceApplication.class, args);
	}
	
 /*@Bean
 public RestTemplate restTemplate() {
     RestTemplate restTemplate = new RestTemplate();
     ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(15000);
     ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setReadTimeout(15000);

     return restTemplate;
 }*/
}
