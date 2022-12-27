package com.curso.spring.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class PointcutExample {

	/* Otra forma para reutilizar el Advice sería crear un @Poincut el cual se le indicará la claes a interveniry los demás metodos solo tendrán que heredar
		este método. La ventaja que si un día cambia nuestra clase de lugar, con modificar este Poincut nos bastará en toda la aplicación que herede de el.
	
	
	*/
	//@Pointcut("execution(* com.curso.spring.aop.TargetObject.*(..))")
	// Se aplica a todos los métodos que se encuentre detro del paquete
	//@Pointcut("within(com.curso.spring.aop.*)")
	
	//Se aplica a todos los métodos que se encuentre dentro del paquete y sus subpaquetes 
	//@Pointcut("within(com.curso.spring.aop..*)")
	
	// Especifica solo el nombre de la clase en el caso de que se encuentre dentro del mismo paquete (com.curso.spring.aop)
	@Pointcut("within(TargetObject)")
	public void metodosTargetObject() {
		// no hace nada esté método, solo define el pointcut que se va a utilizar.
	}
}
