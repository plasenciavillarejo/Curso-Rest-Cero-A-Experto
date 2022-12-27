package com.curso.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.curso.spring.aop.TargetObject;

@SpringBootApplication
public class EjecucionAoop {

	
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(EjecucionAoop.class);
		TargetObject target = context.getBean(TargetObject.class);
		target.hellou("Hola plasencia");
	}

}
