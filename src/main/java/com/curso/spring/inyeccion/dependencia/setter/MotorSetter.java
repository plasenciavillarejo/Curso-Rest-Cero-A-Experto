package com.curso.spring.inyeccion.dependencia.setter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Definimos la clase como component para que lo administre por spring.
@Component
public class MotorSetter {
	
	
	private String marca;
	private Integer modelo;

	public MotorSetter() {
		
	}
	
	public String getMarca() {
		return marca;
	}

	@Value("XC")
	public void setMarca(String marca) {
		this.marca = marca; 
	}

	public Integer getModelo() {
		return modelo;
	}

	@Value("291")
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	
	
}
