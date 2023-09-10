package com.latam.alura.hotel.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.latam.alura.hotel.modelo.*;

public class HuespedDao {

	final private Connection con;

	public HuespedDao(Connection con) {
		this.con = con;
	}

	public Integer guardarHuesped(Huesped huesped) {
		Integer numeroHuesped = null;
		try {

			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO huesped " + "(nombre, apellido, fechaNacimiento, nacionalidad, telefono, IdReserva) "
							+ " VALUES(?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaGuardar(huesped, statement);
				numeroHuesped = huesped.getId();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return numeroHuesped;
	}

	private void ejecutaGuardar(Huesped huesped, PreparedStatement statement) throws SQLException {

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, new java.sql.Date(huesped.getFechaNacimiento().getTime()));
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReservacion());
		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet) {
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				JOptionPane.showMessageDialog(null, "Usted ha heco una reservacion con Id: " + huesped.getId());
			}
		}

	}

	public List<Huesped> mostrar(String criterio) {

		List<Huesped> resultado = new ArrayList<Huesped>();

		try {

			final ResultSet resultSet = devolverResulset(criterio);
			try(resultSet){
				while (resultSet.next()) {
					Huesped fila = new Huesped(resultSet.getInt("Id"), resultSet.getString("nombre"),
							resultSet.getString("apellido"), resultSet.getDate("fechaNacimiento"),
							resultSet.getString("nacionalidad"), resultSet.getString("telefono"),
							resultSet.getInt("IdReserva"));
					resultado.add(fila);
				}
				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	private ResultSet devolverResulset(String criterio) throws SQLException {
		String sql = "";

		if (criterio.equals("")) {
			sql = "SELECT * FROM huesped";
			final PreparedStatement statement = con.prepareStatement(sql);
		
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				return resultSet;
			
		}
		if (esNumeroEntero(criterio)) {
			Integer Id = Integer.parseInt(criterio);
			Integer IdReserva = Integer.parseInt(criterio);
			sql = "SELECT * FROM huesped WHERE Id= ? OR IdReserva = ?";
			final PreparedStatement statement = con.prepareStatement(sql);
			
				statement.setInt(1, Id);
				statement.setInt(2, IdReserva);
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				return resultSet;
			}
		

		if (isFecha(criterio)) {
			Date fechaN = null;
			try {
				fechaN = new SimpleDateFormat("yyyy-MM-dd").parse(criterio);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql = "SELECT * FROM huesped WHERE fechaNacimiento = ?";
			final PreparedStatement statement = con.prepareStatement(sql);
			
				statement.setDate(1, new java.sql.Date(fechaN.getTime()));
				statement.execute();
				final ResultSet resultSet = statement.getResultSet();
				return resultSet;
			}
		

		if (!criterio.isEmpty()) {
			sql = "SELECT * FROM huesped WHERE nombre= ? OR apellido= ? OR nacionalidad= ? OR telefono =?";
			String tipo = criterio;
			final PreparedStatement statement = con.prepareStatement(sql);
		
				statement.setString(1, tipo);
				statement.setString(2, tipo);
				statement.setString(3, tipo);
				statement.setString(4, tipo);
				statement.execute();
			
				 ResultSet resultSet = statement.getResultSet();
				return resultSet;
			
		} else {
			return null;
		}
	}

	private boolean isFecha(String criterio) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dateFormat.parse(criterio);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public int modificar(Huesped huesped) {

		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE huesped SET nombre=?, apellido=?, fechaNacimiento=?, nacionalidad=?, telefono=?, IdReserva=? WHERE Id=?");
			try (statement) {
				statement.setString(1, huesped.getNombre());
				statement.setString(2, huesped.getApellido());
				statement.setDate(3, new java.sql.Date(huesped.getFechaNacimiento().getTime()));
				statement.setString(4, huesped.getNacionalidad());
				statement.setString(5, huesped.getTelefono());
				statement.setInt(6, huesped.getIdReservacion());
				statement.setInt(7, huesped.getId());
				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM huesped WHERE ID=? ");
			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean esNumeroEntero(String criterio) {
		try {
			Integer.parseInt(criterio);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
