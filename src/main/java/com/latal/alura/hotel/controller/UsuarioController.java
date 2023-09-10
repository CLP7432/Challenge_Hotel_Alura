package com.latal.alura.hotel.controller;

import com.latam.alura.hotel.factory.ConnectionFactory;
import com.latam.alura.hotel.modelo.Usuario;
import com.latam.alura.hotel.persistencia.UsuarioDao;

public class UsuarioController {
	
	private UsuarioDao usuarioDao;
	
	public UsuarioController() {
		this.usuarioDao = new UsuarioDao(new ConnectionFactory().recuperaConexion());
	}

	public boolean validar(Usuario usuario) {
		return usuarioDao.validar(usuario);		
	}
 
}
