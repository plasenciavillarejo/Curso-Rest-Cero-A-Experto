package com.curso.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
/* Damos más seguridad a nuestra clase de servicio además de los controladores. Con securedEnables = true, podremos anotar en nuestras
	clases @Service la anotacion @Secured("ROLE_(rol_definido)") para asegurarnos que solo ese rol puede ejecutar ese servicio en particular

Cuando se indica jsr250Enabled -> Quiere endicar que la etiqueta en la que se anotará el servicio no es propio de spring @RolesAllowd({ROLE_ADMIN});

 */
@EnableMethodSecurity(securedEnabled =  true)
public class SpringSecurityConfig {

	// 1.- Creación de Usuario en Memoría
	/*
	@Bean
	public UserDetailsService userDetailsService() throws Exception {

		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user")
				.password(passwordEncoder().encode("user"))
				.roles("USER").build());
		
		manager.createUser(User.withUsername("admin").
				password(passwordEncoder().encode("admin"))
				.roles("ADMIN", "USER").build());

		return manager;
	}
	*/
	
	// 2.- Creacion Bean passwordEncoder.
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	// 3.- Filtro de protección en las URLS.
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeHttpRequests().requestMatchers("/users/**").hasAnyRole("ADMIN")
			.and()
			/* Vamos a permitir cualquier ruta de /users a cualquier usuario que este autenticado. 
			 	Esto vale para acceder al endpoint cualquier persona que este autenticada sin necesidad de terner algún tipo de ROL especificado */
			.authorizeHttpRequests().requestMatchers("/roles/**").permitAll().anyRequest().authenticated()
			.and()
			.httpBasic();

		return http.build();
	}

}
