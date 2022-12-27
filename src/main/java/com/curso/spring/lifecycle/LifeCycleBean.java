package com.curso.spring.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Component
public class LifeCycleBean implements BeanNameAware,InitializingBean,DisposableBean {

	private static final Logger log = LoggerFactory.getLogger(LifeCycleBean.class);

	/*
	 * Se ejecuta durante la construcción del bean "BeanNameAware"
	 */
	@Override
	public void setBeanName(String name) {
		log.info("Bean name: {}", name);
	}
	
	/*
	 * Se ejecuta despues de la inyección de dependencias
	 */
	
	@PostConstruct
	public void initBean() {
		log.info("Post Construct");
	}
	
	/*
	 * Se ejecuta antes de que el bean sea destruido
	 * No se ejecuta para beans con scope("prototype")
	 * Se ejecuta cuando finaliza el el proyecto de forma manual.
	 */
	
	@PreDestroy
	public void destroyBean() {
		log.info("Pre Destroy");
	}

	
	@Override
	public void destroy() throws Exception {
		log.info("DisposableBean Destroy.");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("Init mediante afterPropertiesSet");
		
	}
}
