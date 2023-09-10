package com.latam.alura.hotel.modelo;

import java.util.Date;

public class Reservacion {

	
	private Integer id;
	private Date fechaInicio;
	private Date fechaFinal;
	private Double valorReservacion;
	private String formaPago;
	
	
	
	public Reservacion(Integer id, Date fechaInicio, Date fechaFinal, double valorReservacion, String formaPago) {	
		this.id = id;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.valorReservacion = valorReservacion;
		this.formaPago = formaPago;
	}


	public Reservacion(Date fechaInicial, Date fechaFinal2, double valorReservacion, String formaPago) {		
		this.fechaInicio = fechaInicial;
		this.fechaFinal = fechaFinal2;
		this.valorReservacion = valorReservacion;
		this.formaPago = formaPago;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public double getValorReservacion() {
		return valorReservacion;
	}

	public void setValorReservacion(double valorReservacion) {
		this.valorReservacion = valorReservacion;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	@Override
	public String toString() {
		return "Reservacion [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFinal=" + fechaFinal
				+ ", valorReservacion=" + valorReservacion + ", formaPago=" + formaPago + "]";
	}
	
	
}
