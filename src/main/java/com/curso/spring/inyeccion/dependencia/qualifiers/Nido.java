package com.curso.spring.inyeccion.dependencia.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// Está clase implementará un Animal con las anotaciones @Autowired y @Qualifier

@Component
public class Nido {

	
	private static final Logger log = LoggerFactory.getLogger(Nido.class);

	@Autowired
	@Qualifier("pajaro")
	private Animal animal;

	public void imprimir() {
		log.info("Este nido contiene dentro el animal {}", animal.getNombre());
	}
	
}
