package com.curso.spring.inyeccion.dependencia.qualifiers;

// El animal no puede existes si no es una raza por lo que lo anotamos como "abstract"
public abstract class Animal {

	private Integer edad;
	private String nombre;
	
	public Animal(Integer edad, String nombre) {
		super();
		this.edad = edad;
		this.nombre = nombre;
	}
	
	public Integer getEdad() {
		return edad;
	}
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
