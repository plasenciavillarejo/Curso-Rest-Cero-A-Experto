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
@Order(0)
public class MyAspect {

	
	private static final Logger log = LoggerFactory.getLogger(MyAspect.class);

	/* 
	1.- Definir el método que se ejecutara durante el Advice, before() interceptará al método Hello que tenemos dentro del TargetObject.java
	 	Le indicamos con expression languague -> 
		Primer valor: Lo que retorna la clase, en nuestro caso un "*" indica lo que sea
		Segundo valor: Direccion de la clase, el método que queramos ejecutar y (..) indica que no interesa los parámetros que reciba  -> 
			com.curso.spring.aop.TargetObject.*(..)
		
		Ejecucion: 
		Cuando se ejecute la aplicación se ejecutará primero la clase before() y posteriormente el/los métodos dentro de TargetObject.java
	
	2.- Para interceptar los que está enviado el método se utiliza -> JointPoint
	
	3.- Cuando tenemos varios Aspectos que intercepta un mismo método, podemo indicarle el orden para que se ejecute uno antes que el otro @Order(0,1,2,3...)
	
	4.- Se comenta el value="execution(* com.curso.spring.aop.TargetObject.*(..))" -> Se utiliza una clase @Pointcut el cual ya tiene la clase a interceptar.
		Se debe indicar ahora value="clase y metodo el cual contiene la clase a interceptar" -> value="PointcutExample.metodosTargetObject()"
	*/
	@Before(value = "PointcutExample.metodosTargetObject()")
	public void before(JoinPoint joinPoint) {
		System.out.println("\n");
		log.info("Ejecutando en primera instancia -> MyAspect.java. ");
		
		log.info("Nombre del método que se está interceptando: {}", joinPoint.getSignature().getName());
		log.info("Clase y método que está interceptando: {}", joinPoint.getSignature().getDeclaringTypeName());
		log.info("Objetos que está modificando: {}", joinPoint.getSignature().getModifiers());
		log.info("Argumentos que se le está pasando al objeto : {}", joinPoint.getArgs());
		
		log.info("Before advice");
	}
	
}
