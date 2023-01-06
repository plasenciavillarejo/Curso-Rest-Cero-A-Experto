package com.curso.spring.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.curso.spring.entity.Role;
import com.curso.spring.repository.IRoleRepository;

import jakarta.transaction.Transactional;

@Service
public class RoleService {

	@Autowired
	private IRoleRepository roleRepository;

	private static final Logger log = LoggerFactory.getLogger(RoleService.class);

	@Transactional
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Transactional
	public Role createRole(Role role) {
		roleRepository.save(role);
		log.info("Se ha creado correcamente el rol: {}, con el id: {}", role.getName());
		return role;
	}

	public Role updateRole(Integer roleId, Role role) {
		log.info("Se procede a buscar el rol con el siguiente id {}", roleId);
		Optional<Role> buscarRol = roleRepository.findById(roleId);
		
		if (buscarRol.isPresent()) {
			log.info("Se procede actualizar el role: {}", buscarRol.get().getName());
			roleRepository.save(role);
			log.info("Rol actulizado: {}", role.getName());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					String.format("Role id $d no está en la BBDD", roleId));
		}

		return role;
	}

	public void deleteRol(Integer roleId) {
		log.info("Se procede a borrar el role con el id:{}",roleId);
		Optional<Role> buscarRol = roleRepository.findById(roleId);
		
		if(buscarRol.isPresent()) {
			roleRepository.delete(buscarRol.get());
			log.info("Se ha borrado correcamente el siguiente ROL: {}", buscarRol.get().getName());
		}
		
	}
	
}
