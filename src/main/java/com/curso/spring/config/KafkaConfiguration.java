package com.curso.spring.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfiguration {

	/* ### Inicio: Configuracion Consumer ### */
	/* ###################################### */
	private Map<String , Object> consumerProps() {
		Map<String, Object> props = new HashMap<>();
		// Broker de Kafka al cual nos vamos a conectar
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8080");
		// Identificado de consumidor con el que voy a consumir los mensajes
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "plasenciaGroup");
		// Por defecto le indicamos que se va hacer commit cuando se reciba los mensajes.
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		// Se hace el auto-commit cada 100 ms.
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		// Tiempo de sesion de vida
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		// Se le indica la el key y el valor que contendrá el mensaje
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return props;
	}
	
	@Bean
	public ConsumerFactory<Integer, String> consumerFactory(){
		return new DefaultKafkaConsumerFactory<>(consumerProps());
	}
	
	
	// Creacion de el listener encargado de estár a la escucha de cualquier mensaje y poder realizar alguna acción una vez que sea recibido.
	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<Integer, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
	
	
	/* ### Fin: Configuracion Consumer ### */
	/* ###################################### */	
	
	
	/* ### Inicio: Configuracion Producer ### */
	/* ##################################### */
	
	private Map<String, Object> producerProps() {
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8080");
		// Numero de reintentos que espera para entregar el mensaje.
		props.put(ProducerConfig.RETRIES_CONFIG, 2);
		// Mejora el envio de los mensajes. Se crean los batch con un tamaño determinado y luego se envían, de modo que se los mensajes se van agrupando por batch para enviarse juntos
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		// Tiempo en el que se va a enviar los batch
		props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		// Define cuantos batch se van a crear para almacenar los mensajes en cada uno de los batchs.
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		
		return props;
	}
	
	// Método encargado para el envío de mis mensajes.
	
	@Bean
	public KafkaTemplate<Integer, String> createTemplate() {
		Map<String, Object> sernderProps = new HashMap<>();
		ProducerFactory<Integer, String> producer = new DefaultKafkaProducerFactory<Integer,String>(sernderProps);
		KafkaTemplate<Integer, String> template = new KafkaTemplate<>(producer);
		return template;
	}
	
	
	/* ### Fin: Configuracion Producer ### */
	/* ##################################### */
	
	
}
