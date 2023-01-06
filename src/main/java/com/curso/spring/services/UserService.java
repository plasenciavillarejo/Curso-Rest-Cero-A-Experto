package com.curso.spring.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.curso.spring.model.User;
import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;

@Service
public class UserService {

	@Autowired
	private Faker faker;

	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	
	private List<User> users = new ArrayList<>();

	// Devuelve los usuarios
	public List<User> getUsers(String startWith) {
		// Utilizmaos el @RequestParam para hacer buscar por uno o por varios
		
		if (startWith != null) {
			List<User> usuariosEncontrados = new ArrayList<>();
			for (User user : users) {
				// 1º.- Buscar un nombre que contenga una letra
				/*
				 * if (user.getUsername().contains(startWith)) {
				 *  usuariosEncontrados.add(user);
				 * }
				 */
				
				// 2º.- Buscar un nnombre que empiece por un carácter en específico
				String[] separarLetras = user.getUsername().split("");
				for (int i = 0; i < separarLetras.length; i++) {
					if (separarLetras[0].equalsIgnoreCase(startWith)) {
						usuariosEncontrados.add(user);
					}
					break;
				}
			}
			log.info("Se han encontrado un total de {} usuarios, que contenga en el nombre el caracter '{}'",
					usuariosEncontrados.size(), startWith);
			return usuariosEncontrados;
		} else {
			return users;
		}
		
		// Utilizando el método stream.filer() nos facilitará la busqueda de un nombre que empiece por un carácter en específico
		/*
		if(startWith != null) {
			List<User> usuariosEncontrados =  users.stream().filter(u->
					u.getUsername().startsWith(startWith)).collect(Collectors.toList());
			log.info("Se han encontrado un total de {} usuarios, que empiecen por el caracter y '{}'", usuariosEncontrados.size(), startWith);
		return usuariosEncontrados;
		}else {
			return users;
		}
		*/
	}
		
	// Metodo para inicializar la lista antes de cargar spring. -> Método más rápido que utilizar un servlet de carga inicial dentro del web.xml
	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			users.add(new User(faker.funnyName().name(), faker.name().username(), faker.dragonBall().character()));
		}
	}
	
	public User getUserByUserName(String username) {
		return users.stream().filter(u->u.getUsername().equals(username)).findAny().orElseThrow(()
				->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Usuario %s no encontrado",username)));
	}
	
	public User createUser(User user) {
		// Si existe el usuario devuelve un error
		if(users.stream().anyMatch(u->u.getUsername().equals(user.getUsername()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s existe", user.getUsername()));
		}else {
			users.add(user);
		}
		return user;
	}
	
	public User updateUser(User user, String username) {
		
		// Busca el usuario dentro del método que contiene todos los usuarios guardados. Una vez que lo encuentra le setea los nuevos valores.
		User userUpdate = getUserByUserName(username);
		userUpdate.setNickName(user.getNickName());
		userUpdate.setPassword(user.getPassword());
		return userUpdate;
	}
	
	public void deleteUser(String user) {
		User userByUsername = getUserByUserName(user);
		users.remove(userByUsername);
	}
}
