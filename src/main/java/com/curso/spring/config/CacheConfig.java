package com.curso.spring.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

	public CacheManager getManager() {
		return new ConcurrentMapCacheManager("usuario","listaUsuarios","listaRoles","listaPaginacion");
	}
}
