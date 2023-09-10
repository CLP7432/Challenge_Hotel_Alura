package com.latal.alura.hotel.controller;

import java.util.List;

import com.latam.alura.hotel.factory.*;
import com.latam.alura.hotel.modelo.Reservacion;
import com.latam.alura.hotel.persistencia.*;

public class ReservacionController {
	private ReservacionDao reservacionDao;
	
	public ReservacionController() {
		this.reservacionDao = new ReservacionDao(new ConnectionFactory().recuperaConexion());
	}

	public int modificar(Reservacion reservacion) {		
		return reservacionDao.modificar(reservacion);
	}

	public List<Reservacion> mostrar(String criterio) {		
		return reservacionDao.mostrar(criterio); 
	}

	public int eliminar(Integer id) {		
		return reservacionDao.eliminar(id);
	}

	public Integer guardarReservacion(Reservacion miReservacion) {	
		return reservacionDao.guardarReservacion(miReservacion);
	}
	
}
