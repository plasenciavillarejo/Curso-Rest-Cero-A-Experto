package com.curso.spring.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.curso.spring.entity.User;
import com.curso.spring.repository.IUserRepository;

@Service
public class UserService {

	@Autowired
	private IUserRepository userRepository;

	private static final Logger log = LoggerFactory.getLogger(UserService.class);

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		User buscarUsuario = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("El usuario con el id: $d no se encuentra en la BBDD.", id)));
		return buscarUsuario;
	}

	public User getUserByUsername(String username) {
		User buscarUsuario = userRepository.findByUsername(username).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User $s not found.", username)));
		return buscarUsuario;
	}

}
