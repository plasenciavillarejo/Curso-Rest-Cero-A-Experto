package com.curso.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.model.User;
import com.curso.spring.services.UserServiceV1;

@RestController
@RequestMapping(value = "/v1/users")
public class UserControllerV1 {

	@Autowired
	private UserServiceV1 userService;

	@GetMapping
	// Método HTTP + Recurso -> Handler Methods
	// Para buscar con @RequestParam algunos usuarios que empeicen por alguna letra -> http://localhost:8080/users?startWith=b
	public ResponseEntity<List<User>> getUsers(@RequestParam(value="startWith", required = false) String startWith) {
		return new ResponseEntity<List<User>>(userService.getUsers(startWith), HttpStatus.OK);
	}

	@GetMapping(value = "/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
	}

	@PostMapping(value = "/createUser")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PutMapping(value ="/updateUser/{username}")
	public ResponseEntity<User> updateUser(@PathVariable("username") String userName, @RequestBody User user){
		return new ResponseEntity<User>(userService.updateUser(user,userName), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteUser/{username}")
	public ResponseEntity<User> deleteUser(@PathVariable("username") String userName){
		userService.deleteUser(userName);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
