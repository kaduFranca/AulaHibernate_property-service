package com.example.propertyservice;

import models.UserModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;


@SpringBootApplication
public class PropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyServiceApplication.class, args);

		UserModel pessoa1 = new UserModel(null,"Kadu",465767677,"kadugabrielmdf@gmail.com", LocalDate.of(2002, 11, 10));


		System.out.println(pessoa1);
	}
}
