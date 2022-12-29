package com.curso.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
public class FakerBeanConfig {
	
	/* 1.-Añadimos la clase Faker de forma explicita:
	  		Con esto estamos anotamos un bean al contexto de spring de forma externa 
			de este modo ya podemos inyectar getFaker() sin necesidad de utilizar el operador -> new Faker();
		
		De este modo podemo inyectar la clase (Como si se tratara de una interfaz):
			@Autowired
			private Faker faker;
	*/
	@Bean
	public Faker getFaker() {
		return new Faker();
	}
	
}
