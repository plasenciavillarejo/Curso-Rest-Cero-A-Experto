package com.curso.spring.inyeccion.dependencia.qualifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("pajaro")
public class Pajaro extends Animal implements Volador{

	private static final Logger log = LoggerFactory.getLogger(Pajaro.class);
	
	public Pajaro(@Value("4") Integer edad, @Value("Currete") String nombre) {
		super(edad, nombre);
	}

	@Override
	public void volar() {
		log.info("Soy un pajarraco y estoy volando");				
	}

	
	
}
