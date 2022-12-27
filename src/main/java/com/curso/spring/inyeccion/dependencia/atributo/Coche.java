package com.curso.spring.inyeccion.dependencia.atributo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Inyeccion de dependencias mediante Atributo. Se está utilizando el atributo Motor para hacer la inyeccion de la dependencia.

@Component
public class Coche {

	@Value("Mercedes")
	private String marca;
	@Value("1292")
	private Integer modelo;
	
	@Autowired
	private Motor motor;
	
	public Coche() {
		
	}

	public Coche(String marca, Integer modelo, Motor motor) {
		super();
		this.marca = marca;
		this.modelo = modelo;
		this.motor = motor;
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

	public Motor getMotor() {
		return motor;
	}

	public void setMotor(Motor motor) {
		this.motor = motor;
	}

	@Override
	public String toString() {
		return "Coche [marca=" + marca + ", modelo=" + modelo + ", motor=" + motor.getMarca() + "]";
	}
	
	
	
	
}
