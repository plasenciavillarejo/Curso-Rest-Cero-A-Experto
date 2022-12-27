package com.curso.spring.profiles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("pro")
public class PerfilProduccion implements PerfilesServicios {
	
	@Value("${entrono.activo}")
	String profileOn;
	
	@Value("${spring.datasource.driver-class-name}")
	String bdActiva;
	
	@Value("${spring.profiles.active}")
	String perfilActivo;
	
	
	private static final Logger log = LoggerFactory.getLogger(PerfilProduccion.class);

	@Override
	public String getEnviroment() {
		log.info("El Perfil que está activo es: {}",perfilActivo);
		log.info("Perfil Activado: {}",profileOn);
		log.info("BBDD activa: {}", bdActiva);
		
		return "Pro";
	}

}
