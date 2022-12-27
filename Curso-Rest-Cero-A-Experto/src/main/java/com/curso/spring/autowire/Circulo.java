package com.curso.spring.autowire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Circulo implements Figura {
	
	@Value("${circulo.radio}")
	double radio;
	
	@Override
	public double calcularArea() {
		// TODO Auto-generated method stub
		return Math.PI * Math.pow(radio, 2);
	}

}
