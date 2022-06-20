package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceprojectApplication.class, args);
	}

}
