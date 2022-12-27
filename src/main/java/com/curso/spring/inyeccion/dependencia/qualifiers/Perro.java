package com.curso.spring.inyeccion.dependencia.qualifiers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Perro extends Animal{

	public Perro(@Value("13") Integer edad, @Value("Leo") String nombre) {
		super(edad, nombre);
	}

}
