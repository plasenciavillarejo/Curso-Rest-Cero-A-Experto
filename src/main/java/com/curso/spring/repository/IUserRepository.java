package com.curso.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.curso.spring.entity.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

	
	/* 1.- Utilizamos Query Method que nos facilita JPA para buscar informacion en la BBDD.
	 Cuando creamos nuestra propia query para buscar el nombre del usuario sería de la siguiente forma:	
	 	-> Si buscamos por el nombre de usuario debemos de indicar findBy y posteriormente ver el como se llama nuesta columna:
	 		String username -> Esto se debe indicar con la primera letra en mayuscula -> findBy:Username.
	 		String userName -> En este caso debemos de indicarlo -> findBy:UserName 
	 Si no seguimos estos patrones de diseño nos arrojara un error que no encuentra el nombre para hacer match con nuestra columna.
	 		
	 Ejemplo:
	 	String username;
	 		findByUserName -> No property 'userName' found for type 'User' Did you mean ''username''
	 	Solución:
	 		findByUsername.
	 */
	
	public Optional<User> findByUsername(String username);
	
	public Optional<User> findByUsernameAndPassword(String username, String password);
	
}
