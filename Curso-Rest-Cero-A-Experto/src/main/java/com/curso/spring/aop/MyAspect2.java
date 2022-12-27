package com.curso.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class MyAspect2 {

	private static final Logger log = LoggerFactory.getLogger(MyAspect2.class);

	@Before(value = "PointcutExample.metodosTargetObject()")
	public void before(JoinPoint joinPoint) {
		System.out.println("\n");
		log.info("Ejecutando en segunda instancia -> MyAspect2.java. ");
		
		log.info("Nombre del método que se está interceptando: {}", joinPoint.getSignature().getName());
		log.info("Clase y método que está interceptando: {}", joinPoint.getSignature().getDeclaringTypeName());
		log.info("Objetos que está modificando: {}", joinPoint.getSignature().getModifiers());
		log.info("Argumentos que se le está pasando al objeto : {}", joinPoint.getArgs());

		log.info("Before advice");
	}
}
