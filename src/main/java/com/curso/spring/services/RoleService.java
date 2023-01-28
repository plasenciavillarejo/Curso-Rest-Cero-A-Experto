package com.curso.spring.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.curso.spring.entity.AuditoriaDetails;
import com.curso.spring.entity.Role;
import com.curso.spring.entity.User;
import com.curso.spring.repository.IRoleRepository;
import com.curso.spring.repository.IUserInRoleRepository;
import com.curso.spring.util.MetaAnotacionSpringSecurity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.security.RolesAllowed;

@Service
/* Si se desea acotar toda está clase con un @PreAuthorize y @PostAutorize se podrá indicar a nivel de clase la MetaAnotacion.
@MetaAnotacionSpringSecurity */
public class RoleService {

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private IUserInRoleRepository userInRoleRepository;
	
	
	// Inyectamos el KafkaTemplate encargado de enviar los mensajes
	@Autowired
	private KafkaTemplate<Integer, String> kafkaTemplate;
	
	private ObjectMapper formatoJson = new ObjectMapper();
	
	private static final Logger log = LoggerFactory.getLogger(RoleService.class);

	/* 
	Le indicamos que solo este rol podrá ejecutar nuestro método.
		 @Secured({"ROLE_ADMIN"})
	Cuando queremos utilizar una validación fuera de el contexto de spring podemos utlizar @RolesAllowed:	
		 @RolesAllowed({"ROLE_ADMIN"})
	*/
	
	@Secured({"ROLE_ADMIN"})
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	/*
		Utilizando  @PreAuthorize permitimos que unos ciertos roles puedan ejecutar esté método
		Cuando se utiliza @PostAuthorize se le está indicando que una vez que se haya ejecutado solo devolverá el resultado aquellos roles que estén indicados.
			@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
			@PostAuthorize("hasRole('ROLE_ADMIN')")
		Se puede Crear una MetaAnotacion que se anote con el @Pre/@Post y evitar tener que ir anotando en cada método con estas anotaciones repetitivas.
	*/ 
	@MetaAnotacionSpringSecurity
	public List<User> getUsersByRole(String roleName){
		return userInRoleRepository.findByUserByRole(roleName);
	}
	
	public Role createRole(Role role) {
		AuditoriaDetails auditoria = new AuditoriaDetails(
				SecurityContextHolder.getContext().getAuthentication().getName(), role.getName());

		try {
			log.info("Se procede a enviar el mensaje mediante kafkaTemplate");
			kafkaTemplate.send("plasencia-topic", formatoJson.writeValueAsString(auditoria));
		} catch (JsonProcessingException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error al parsear el mensaje");
		}
		roleRepository.save(role);
		log.info("Se ha creado correcamente el rol: {}, con el id: {}", role.getName(), role.getId());
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
