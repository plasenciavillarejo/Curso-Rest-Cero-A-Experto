package com.curso.spring.lazy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Lazy
@Component
public class BeanA {

	
	private static final Logger log = LoggerFactory.getLogger(BeanA.class);

	@PostConstruct
	public void init() {
		log.info("Cargando el BeanA utilizando la anotacion Lazy");
	}
}
