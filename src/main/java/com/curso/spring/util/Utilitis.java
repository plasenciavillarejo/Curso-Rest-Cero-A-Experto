package com.curso.spring.util;


import java.text.SimpleDateFormat;
import java.util.Date;

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
		for(int i =0; i<1000; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			userRepository.save(user);
		}
	}

	public Date convertirFecha(Date fechaRecibida) {
		// Pasamos el objeto a String y luego una vez convertido lo pasamos nuevamente a Date() pero ya en formato yyyy-MM-dd;
		Date date = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
		String fechaAString = formato.format(fechaRecibida);
		
		Date transformFecha =null;
		try {
			transformFecha = formato.parse(fechaAString);
		}catch (Exception e) {
			e.getMessage();
		}
		return transformFecha;
	}
	
}
