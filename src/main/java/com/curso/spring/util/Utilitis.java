package com.curso.spring.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.spring.entity.User;
import com.curso.spring.repository.IUserRepository;
import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;

@Component
public class Utilitis {

	@Autowired
	private Faker faker;
	
	@Autowired
	private IUserRepository userRepository;
	
	// Carga los usuario al iniciar la aplicacion para que ya tengamos los usuarios listos para tratarlos
	@PostConstruct
	public void crearUsuarios() {
		for(int i =0; i<10; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			user.setProfile(null);
			userRepository.save(user);
		}
	}
	
}
