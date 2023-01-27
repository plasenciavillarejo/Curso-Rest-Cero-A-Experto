package com.curso.spring.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.curso.spring.entity.Role;
import com.curso.spring.entity.User;
import com.curso.spring.services.RoleService;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	
	@GetMapping
	@Cacheable("listaRoles")
	public ResponseEntity<List<Role>> getRoles() {
		
		// Clase que nos facilita spring para saber que usuario autenticado ha solicitado el endpoint
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Name: {}", authentication.getName());
		log.info("Principal: {}", authentication.getPrincipal());
		log.info("Credentials: {}", authentication.getCredentials());
		log.info("Roles: {}", authentication.getAuthorities().toString());
		
		
		return new ResponseEntity<List<Role>>(roleService.getRoles(), HttpStatus.OK);
	}

	// Devuelve todos los usuarios con un ROL determinado
	@GetMapping(value = "/{roleName}/users")
	public ResponseEntity<List<User>> getUserByRole(@PathVariable("roleName") String roleName) {
		return new ResponseEntity<List<User>>(roleService.getUsersByRole(roleName), HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/createRole")
	public ResponseEntity<Role> createRoles(@RequestBody Role role) {
		roleService.createRole(role);
		return new ResponseEntity<Role>(HttpStatus.OK);
	}

	@PutMapping(value = "/updateRole/{roleId}")
	public ResponseEntity<Role> createRoles(@PathVariable("roleId") Integer roleId,@RequestBody Role role) {
		roleService.updateRole(roleId,role);
		return new ResponseEntity<Role>(roleService.updateRole(roleId, role),HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/deleteId/{roleId}")
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer roleId){
		roleService.deleteRol(roleId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	
}
