package com.curso.spring.autowire;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalcularAreaServicio {

	@Autowired
	private List<Figura> figura;
	
	public double calcularArear() {
		double area = 0;
		for(Figura f: figura) {
			area = f.calcularArea();
		}
		return area;
	}
	
}
