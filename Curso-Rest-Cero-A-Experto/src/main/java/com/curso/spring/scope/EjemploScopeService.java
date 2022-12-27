package com.curso.spring.scope;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton") // Crea una sola instancia es el por defecto
//@Scope("prototype") // Crea una nueva instancia cada vez que se solicita
public class EjemploScopeService {

}
