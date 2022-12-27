package com.curso.spring.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/*
	Nos permite registrar acciones antes y despues de la ejecución de un Bean.
	Si queresmos saber que beans está creando nuestra aplicación creamos una clase que implemente el BeanPostProcessor y en el se verán todos los bean inicializados.
 */

@Component
public class BeanPostProcess implements BeanPostProcessor {

	private static final Logger log = LoggerFactory.getLogger(BeanPostProcess.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// Vamos a ver el log unica y exclusivamente al que pertenece a LifeCycleBean
		if (bean instanceof LifeCycleBean) {
			System.out.println("\n");
			log.info("Befores initialition {}", beanName);
		}

		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// Vamos a ver el log unica y exclusivamente al que pertenece a LifeCycleBean
		if (bean instanceof LifeCycleBean) {
			log.info("After initialition {}", beanName);
			System.out.println("\n");
		}

		return bean;
	}

}
