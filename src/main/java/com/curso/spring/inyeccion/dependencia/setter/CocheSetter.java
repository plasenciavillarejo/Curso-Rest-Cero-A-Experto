package com.curso.spring.inyeccion.dependencia.setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// Inyeccion de dependencias mediante Constructor.
//Definimos la clase como component para que lo administre por spring.
@Component
public class CocheSetter {

	private String marca;
	
	private Integer modelo;
	
	private MotorSetter motor;
	
	public String getMarca() {
		return marca;
	}

	@Value("Mercedes")
	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Integer getModelo() {
		return modelo;
	}

	@Value("1292")
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}

	public MotorSetter getMotor() {
		return motor;
	}

	@Autowired
	public void setMotor(MotorSetter motor) {
		this.motor = motor;
	}

	@Override
	public String toString() {
		return "Coche [marca=" + marca + ", modelo=" + modelo + ", motor=" +  motor + " ]";
	}
	
	
	
	
}
