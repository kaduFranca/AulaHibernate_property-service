package com.example.propertyservice;

import com.example.propertyservice.models.UserModel;
import com.example.propertyservice.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class PropertyServiceApplication {
	final UserRepository repository;

	public PropertyServiceApplication(UserRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(PropertyServiceApplication.class, args);


	}
}
