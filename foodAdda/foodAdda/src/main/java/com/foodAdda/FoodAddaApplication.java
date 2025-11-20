package com.foodAdda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FoodAddaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodAddaApplication.class, args);

		System.out.println("Server Started......");
	}

}
