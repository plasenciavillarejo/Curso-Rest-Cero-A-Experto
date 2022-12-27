package com.curso.spring.inyeccion.dependencia.constructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Definimos la clase como component para que lo administre por spring.
@Component
public class MotorConstructor {
	
	
	private String marca;
	private Integer modelo;

	public MotorConstructor(@Value("XC") String marca,@Value("291") Integer modelo) {
		this.marca = marca;
		this.modelo = modelo;
	}
	
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca; 
	}

	public Integer getModelo() {
		return modelo;
	}

	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	
	
}
