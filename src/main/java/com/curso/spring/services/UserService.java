package com.curso.spring.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	/* 1.- Ejecucion @Cacheable ->
 	 Procedemos a meter en cache la lista de usuario 
	 */
	@Cacheable("listaUsuarios" )
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Cacheable("usuario")
	public User getUserById(Integer id) {
		User buscarUsuario = userRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
						String.format("El usuario con el id: $d no se encuentra en la BBDD.", id)));
		return buscarUsuario;
	}

	public User getUserByUsername(String username) {
		User buscarUsuario = userRepository.findByUsername(username).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User ds not found.", username)));
		return buscarUsuario;
	}

	
	public User getUserByUsernameAndPassword(User user) {
		User buscarUsuario = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User $d not found.", user)));
		return buscarUsuario;
	}
	
	public Page<User> getUsersPageable(int page, int size){
		return userRepository.findAll(PageRequest.of(page, size));
	}
	
	/* Cuando Utilizamos la propiedad '@Cacheable' en una lista y queremos que una vez que esté en memoría si sufre alguna actulizacion no matenga los valores estáticos 
	  deberemos de indicarle al mapping que vaya 'Acutalizar/Borrar' la anotacion @CacheEvict() donde el value contendrá el objeto donde se ha cacheado y el allEntries = true
	 	Ejemplo:  
	 		Tenemos una lista con 10.000 usuarios la primera vez carga la lista tirando de BBDD luego la mantiene en memoria y si sufre alguna modificacion está realizar cualquier
	 		cambio en BBDD y tambíen sufriá los cambios en la memoría. Con esto seguimos manteniendo la mejora de rendimiento a la hora de listar los 9.999 registros sin sobrecargar
	 		la BBDD.
	   */
	@CacheEvict(value = {"usuario","listaUsuarios"}, allEntries = true)
	public void deleteByUsername(String name) {
		User user = getUserByUsername(name);
		userRepository.delete(user);
	}
	
}
