package com.curso.spring.lazy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Lazy()
@Component
public class BeanD {

	@Autowired
	private BeanC beanC;
	
	private static final Logger log = LoggerFactory.getLogger(BeanD.class);

	@PostConstruct
	public void init() {
		log.info("Cargando el BeanD utilizando la anotacion lazy(false) y al hacer autowired se carga tambien el BeanC");
	}
}
