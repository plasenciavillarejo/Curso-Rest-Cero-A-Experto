package com.curso.spring.util;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.curso.spring.entity.Role;
import com.curso.spring.entity.User;
import com.curso.spring.entity.UserInRole;
import com.curso.spring.repository.IRoleRepository;
import com.curso.spring.repository.IUserInRoleRepository;
import com.curso.spring.repository.IUserRepository;
import com.curso.spring.services.UserInRoleRepository;
import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;


@Component
public class Utilitis {

	@Autowired
	private Faker faker;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private IUserInRoleRepository userInRoleRepository;
	
	private static int existeAdmin = 0;
	private static int existeUser = 0;
	private static int existeSupport = 0;
	
	
	private static final Logger log = LoggerFactory.getLogger(Utilitis.class);

	
	// Carga los usuario al iniciar la aplicacion para que ya tengamos los usuarios listos para tratarlos
	@PostConstruct
	public void crearUsuarios() {
		
		// Inicialmente vamos a crear unos roles por defecto en nuestra BBDD.
		Role roles[] = {new Role("ADMIN"),new Role("SUPPORT"),new Role("USER")};
		
		for(Role role: roles) {
			roleRepository.save(role);
		}
		
		// Vamos a guardar 3 usuario con diferentes roles para poder loguearnos en nuestra api
		List<UserInRole> usuariosAlmacenados = new ArrayList<>();
		for(int i =0; i<1000; i++) {
			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.dragonBall().character());
			userRepository.save(user);
			
			// Guardamos una Entidad Role y el role de forma aleatoria 
			UserInRole userInRole = new UserInRole();
			userInRole.setUser(user);
			userInRole.setRole(roles[new Random().nextInt(3)]);
			userInRoleRepository.save(userInRole);
			
			if (userInRole.getRole().getName().equalsIgnoreCase("ADMIN") && existeAdmin == 0) {
				existeAdmin = 1;
				usuariosAlmacenados.add(userInRole);
				log.info("Usuario: {}, Password: {}, Role: {}", userInRole.getUser().getUsername(),
						userInRole.getUser().getPassword(), userInRole.getRole().getName());
			} else if (userInRole.getRole().getName().equalsIgnoreCase("USER") && existeUser == 0) {
				existeUser = 1;
				usuariosAlmacenados.add(userInRole);
				log.info("Usuario: {}, Password: {}, Role: {}", userInRole.getUser().getUsername(),
						userInRole.getUser().getPassword(), userInRole.getRole().getName());
			} else if (userInRole.getRole().getName().equalsIgnoreCase("SUPPORT") && existeSupport == 0) {
				existeSupport = 1;
				log.info("Usuario: {}, Password: {}, Role: {}", userInRole.getUser().getUsername(),
						userInRole.getUser().getPassword(), userInRole.getRole().getName());
			}

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
