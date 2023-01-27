package com.curso.spring.util;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

// Está clase nos sirve para fusionar dos anotaciones -> @PreAuthorize o @PostAutorhize. Tenemos la funcionalidad de las dos sin necesidad de repetir la lógica

@Retention(RUNTIME)
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
@PostAuthorize("hasRole('ROLE_ADMIN')")
public @interface MetaAnotacionSpringSecurity {

}
