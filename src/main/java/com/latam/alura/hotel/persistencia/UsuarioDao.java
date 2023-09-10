package com.latam.alura.hotel.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.latam.alura.hotel.factory.ConnectionFactory;
import com.latam.alura.hotel.modelo.Usuario;
import com.latam.alura.hotel.view.MenuUsuario;

public class UsuarioDao {
	
	private Connection con;
	 
	public UsuarioDao(Connection con) {
		this.con = con;
	}

	public boolean validar(Usuario usuario) {
		String sql = "SELECT * FROM usuarios WHERE usuario=? AND clave=?";
		try {
		con = new ConnectionFactory().recuperaConexion();
		PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);		
	
			statement.setString(1, usuario.getUsuario());
			statement.setString(2, usuario.getClave());
			statement.execute();
			ResultSet resultSet = statement.getResultSet();	
			while(resultSet.next()) {
					return true;
			}
		} catch (SQLException e) {			
			throw new RuntimeException(e);			
		}
		return false;
	}
	
	

}
