package com.curso.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.curso.spring.autowire.CalcularAreaServicio;
import com.curso.spring.inyeccion.dependencia.qualifiers.Animal;
import com.curso.spring.inyeccion.dependencia.qualifiers.Avion;
import com.curso.spring.inyeccion.dependencia.qualifiers.Nido;
import com.curso.spring.inyeccion.dependencia.qualifiers.Pajaro;
import com.curso.spring.inyeccion.dependencia.qualifiers.Perro;
import com.curso.spring.lazy.BeanA;
import com.curso.spring.lazy.BeanD;
import com.curso.spring.lifecycle.ExplicitBean;
import com.curso.spring.lifecycle.LifeCycleBean;
import com.curso.spring.profiles.PerfilesServicios;
import com.curso.spring.scope.EjemploScopeService;

@SpringBootApplication
public class CursoRestCeroAExpertoApplication {

	
	private static final Logger log = LoggerFactory.getLogger(CursoRestCeroAExpertoApplication.class);

	@Bean(initMethod = "init", destroyMethod = "destroy")
	public ExplicitBean getBean() {
		return new ExplicitBean();
	}
	
	
	public static void main(String[] args) {
		// 2.- Inyeccion de dependencias utilizando el contexto de Spring
		ConfigurableApplicationContext context = SpringApplication.run(CursoRestCeroAExpertoApplication.class, args);
		/*
		Coche coche = context.getBean(Coche.class);
		log.info("\n Inyeccion de dependencias por el atributo/constructor. Indicado en el objeto Coche");
		log.info("Imprimiendo la dependencia proporcionada por spring: " + coche);
		*/
		
		System.out.println("\n");
		System.out.println(" ################################################################## ");
		System.out.println(" ##### DIFERENTES TIPOS DE INYECCION DE DEPENDENCIAS EN SPRING #### ");
		System.out.println(" ################################################################## ");
		
		Perro perro = context.getBean(Perro.class);
		Pajaro pajaro = context.getBean(Pajaro.class);
		Avion avion = context.getBean(Avion.class);
		
		System.out.println("\n");
		log.info("Nombre: {} Edad: {}",perro.getNombre() , perro.getEdad());
		
		System.out.println("\n");
		log.info("Pajaro: {} Edad: {}", pajaro.getNombre() , pajaro.getEdad());
		
		System.out.println("\n");
		avion.volar();
		
		System.out.println("\n");
		// Utilizando la clase padre para inyectar una dependencia utilizando @Qualifiers mediante el contexto de spring
		// Cuando se utiliza @Qualifiers mediante anotacion se sabe que se debe inyectar la clase con el mismo nombre pero empezando la primera letra en minuscula.
		// @Qualifiers("pajaro") -> Nota: esto se hace mediante anotacion, en este caso lo hacemos por carga inicial del contenedor de spring
		
		Animal animal = context.getBean("pajaro",Animal.class);
		log.info("Animal nombre = {} edad= {}", animal.getNombre(), animal.getEdad());
		
		// Utilizando las anotaciones @Autowired y @Qualifier
		
		log.info("Imprimimos un animal utilizando las anotaciones @Autowired y @Qualifier");
		Nido nido = context.getBean(Nido.class);
		nido.imprimir();
		
		
		
		System.out.println("\n");
		System.out.println(" ########################################################################## ");
		System.out.println(" ##### PERFILES DE EJECUCION SEGÚN EN EL ENTORNO DONDE NOS ENCONTRAMOS #### ");
		System.out.println(" ########################################################################## ");
		
		
		/* 3.- Inyeccion de Profiles según en el entorno de ejecucion donde nos encontremos */
		PerfilesServicios perfilEjecucion =  context.getBean(PerfilesServicios.class);
		log.info("Perfil ejecucion indicando en el application.propeties es: {}", perfilEjecucion.getEnviroment());
		
		
		System.out.println("\n");
		System.out.println(" #################################### ");
		System.out.println(" ##### TIPOS DE SCOPE EN SPRING  #### ");
		System.out.println(" #################################### ");
		
		// AL utilizar scope("prototype") se crea una nueva instancia cada vez que la solicitamos.
		EjemploScopeService scope1 = context.getBean(EjemploScopeService.class);
		EjemploScopeService scope2 = context.getBean(EjemploScopeService.class);
		
		log.info("¿ scope1.equals(scope2) utilizando 'prototype'? -> , {} ", scope1.equals(scope2));
		log.info("¿ scope1 == scope2 utilizando 'prototype'? ->  {} ", scope1 == scope2);
		
		
		System.out.println("\n");
		System.out.println(" ######################################################### ");
		System.out.println(" ##### INYECCION DE DEPENDNECIAS MENDIANTE AUTOWIRED  #### ");
		System.out.println(" ######################################################### ");

		CalcularAreaServicio areaServicio = context.getBean(CalcularAreaServicio.class);
		log.info("Area total: {}" , areaServicio.calcularArear());
		
		
		System.out.println("\n");
		System.out.println(" #################################### ");
		System.out.println(" ##### 	CICLO DE VIDA EN SPRING  #### ");
		System.out.println(" #################################### ");
		
		LifeCycleBean cicleBean = context.getBean(LifeCycleBean.class);
		log.info("Imprime lo siguiente -> \n INFO 22557 --- [  restartedMain] c.curso.spring.lifecycle.LifeCycleBean   : Bean name: lifeCycleBean");
		
		
		System.out.println("\n");
		System.out.println(" ################################################## ");
		System.out.println(" ##### 	Utilizando los métodos LAZY en Spring  #### ");
		System.out.println(" ################################################## ");
		BeanA beanA = context.getBean(BeanA.class);
		
		
		System.out.println("\n");
		System.out.println(" ####################################################### ");
		System.out.println(" ##### 	Utilizando los métodos LAZY de forma EAGER  #### ");
		System.out.println(" ####################################################### ");
		BeanD beanD = context.getBean(BeanD.class);	
		
	}

}
