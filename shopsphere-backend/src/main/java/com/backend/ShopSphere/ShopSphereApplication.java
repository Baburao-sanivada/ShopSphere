package com.backend.ShopSphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class ShopSphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopSphereApplication.class, args);
	}

}
