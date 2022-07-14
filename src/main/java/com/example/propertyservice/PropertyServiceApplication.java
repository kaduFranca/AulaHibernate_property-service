package com.example.propertyservice;

import entities.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;


@SpringBootApplication
public class PropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PropertyServiceApplication.class, args);

		User pessoa1 = new User(null,"Kadu",465767677,"kadugabrielmdf@gmail.com","2002-11-10");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("projetinho-fellas");
		EntityManager em = emf.createEntityManager();

		System.out.println(pessoa1);
	}
}
