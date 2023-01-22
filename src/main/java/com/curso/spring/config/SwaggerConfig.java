package com.curso.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

	
	@Bean
	public Docket getDocket() {
		// Tipo de Documentación queremos: SWAGGER_2
		return new Docket(DocumentationType.SWAGGER_2)
				// Selectores por Path y por Request exponiendo todos los recuros/direcciones
				// (any())
				.select().apis(RequestHandlerSelectors.basePackage("com.mayware.gforks.controllers"))
				.paths(PathSelectors.any()).build()
				.apiInfo(new ApiInfoBuilder().title("Delivery").description("Doc").build());
	}
	

}
