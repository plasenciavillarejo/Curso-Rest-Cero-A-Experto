package com.curso.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.curso.spring.inyeccion.dependencia.atributo.Coche;
import com.curso.spring.inyeccion.dependencia.atributo.Motor;

public class EjecucionEjemplos {

	public static void main(String[] args) {
		
		// 1.- Inyeccion de dependencias mediante el constructor.
		Motor motor = new Motor("X1", 1999);
		Coche coche = new Coche("DD", 1009,motor);

		System.out.println(coche);
		
		
	}

}
