package com.latam.alura.hotel.modelo;

import java.util.Date;

public class Huesped {

	private Integer Id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private String telefono;
	private Integer IdReservacion;
		
	public Huesped(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad,
			String telefono, Integer idReservacion) {		
		this.Id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		IdReservacion = idReservacion;
	}

	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			Integer idReservacion) {		
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;				
		this.IdReservacion = idReservacion;
	}
	
	public Integer getId() {
		return Id;
	}	
	public void setId(Integer id) {
		Id = id;
	}
	public String getNombre() {
		return nombre;
	}	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getIdReservacion() {
		return IdReservacion;
	}

	public void setIdReservacion(Integer idReservacion) {
		IdReservacion = idReservacion;
	}	
	
}
