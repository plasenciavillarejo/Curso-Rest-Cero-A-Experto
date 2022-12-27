package com.curso.spring.autowire;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RaizCuadrada implements Figura {

	@Value("${raiz.side}")
	private double lado;
	
	@Override
	public double calcularArea() {
		// TODO Auto-generated method stub
		return lado*lado;
	}

}
