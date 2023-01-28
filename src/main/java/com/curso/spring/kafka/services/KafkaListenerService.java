package com.curso.spring.kafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {

	
	private static final Logger log = LoggerFactory.getLogger(KafkaListenerService.class);

	// Clase encargada de procesar los mensajes
	@KafkaListener(topics = "plasencia-topic", groupId = "plasenciaGroup")
	public void listener(String message) throws InterruptedException {
		log.info("Se ha recibido el message, {}, se procede a redireccionar a la tabla de Auditoria", message);
		Thread.sleep(50L);
		log.info("Todo ha ido correcto, se ha realizado la auditoría satisfactoriamente.");
	}
	
}
