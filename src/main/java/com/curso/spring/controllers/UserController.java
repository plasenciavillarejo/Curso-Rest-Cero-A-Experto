package com.curso.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entity.User;
import com.curso.spring.services.UserService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer id) {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	
}
