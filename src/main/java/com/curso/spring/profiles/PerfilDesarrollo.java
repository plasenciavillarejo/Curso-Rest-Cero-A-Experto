package com.curso.spring.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class PerfilDesarrollo implements PerfilesServicios{

	
	private static final Logger log = LoggerFactory.getLogger(PerfilDesarrollo.class);

	@Value("${entrono.activo}")
	String profileOn;
	
	@Value("${spring.datasource.driver-class-name}")
	String bdActiva;
	
	@Value("${spring.profiles.active}")
	String perfilActivo;
	
	@Override
	public String getEnviroment() {
		log.info("El Perfil que está activo es: {}",perfilActivo);
		log.info("Perfil Activado: {}",profileOn);
		log.info("BBDD activa: {}", bdActiva);
		return "Dev";
	}

}
