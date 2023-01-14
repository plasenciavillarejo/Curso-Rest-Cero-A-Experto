package com.curso.spring.controllers;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entity.User;
import com.curso.spring.services.UserService;

@RestController
@RequestMapping(value = "/users")
//@Timed
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/* Para crear nuestras propias métricas deberemos de anotar el servicio con @Timed y el nombre de la métrica*/
	
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
	
	@PostMapping
	public ResponseEntity<User> getUserByUsernameAndPassword(@RequestBody User user) {
		return new ResponseEntity<User>(userService.getUserByUsernameAndPassword(user), HttpStatus.OK);
	}
	
	@GetMapping(value = "/pageable")
	public ResponseEntity<Page<User>> getUsersPageable(@RequestParam(value = "page", required = false, defaultValue = "0") 
	int page, @RequestParam(value = "size", required = false, defaultValue = "1000") int size){
		return new ResponseEntity<Page<User>>(userService.getUsersPageable(page,size), HttpStatus.OK);
	}

	@DeleteMapping(value = "/delete/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		userService.deleteByUsername(username);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
}
