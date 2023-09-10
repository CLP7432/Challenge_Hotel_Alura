package com.latal.alura.hotel.controller;

import java.util.List;

import com.latam.alura.hotel.factory.ConnectionFactory;
import com.latam.alura.hotel.modelo.Huesped;
import com.latam.alura.hotel.persistencia.HuespedDao;

public class HuespedController {

	private HuespedDao huespedDao;
	
	public HuespedController() {
		this.huespedDao = new HuespedDao(new ConnectionFactory().recuperaConexion());
	}
	public List<Huesped> mostrar(String criterio) {		
		return huespedDao.mostrar(criterio); 
	}
	public int modificar(Huesped huesped) {		
		return huespedDao.modificar(huesped);
	}
	public int eliminar(Integer id) {		
		return huespedDao.eliminar(id);
	}
	public Integer guardarHuesped(Huesped miHuesped) {
		return huespedDao.guardarHuesped(miHuesped); 		
	}
	
}
