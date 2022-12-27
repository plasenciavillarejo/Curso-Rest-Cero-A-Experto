package com.curso.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


/* Cuando creamos un fichero properties nuevo podemos hacer la carga de las propiedades de la siguiente manera */
@Configuration
@PropertySource("classpath:areas.properties")
public class FigurePropertyConfiguration {

	// Cargamos las propiedades para poder inyectarlas en nuestro ficheros, de está forma ya podremos llamarlo en cualquier objeto que requiera de
	// los argumentos que haya dentro. Se utiliza en la clase Circulo.java y RaizCuadrada.java
	@Bean
	public PropertySourcesPlaceholderConfigurer loadProperties() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
}
