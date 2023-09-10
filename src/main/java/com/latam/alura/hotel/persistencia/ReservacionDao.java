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

import com.latam.alura.hotel.modelo.Reservacion;

public class ReservacionDao {

	private Connection con;

	public ReservacionDao(Connection con) {
		this.con = con;
	}

	public Integer guardarReservacion(Reservacion reservacion) {

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO reservacion " + "(FechaEntrada, FechaSalida, Valor, FormaPago)" + " VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				statement.setDate(1, new java.sql.Date(reservacion.getFechaInicio().getTime()));
				statement.setDate(2, new java.sql.Date(reservacion.getFechaFinal().getTime()));
				statement.setDouble(3, reservacion.getValorReservacion());
				statement.setString(4, reservacion.getFormaPago());

				statement.execute();
				final ResultSet resultSet = statement.getGeneratedKeys();
				try (resultSet) {
					while (resultSet.next()) {
						reservacion.setId(resultSet.getInt(1));
						JOptionPane.showMessageDialog(null,
								"Usted ha heco una reservacion con Id: " + reservacion.getId());
					}
					Integer numeroReservacion = reservacion.getId();
					return numeroReservacion;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reservacion> mostrar(String criterio) {
		try {

			List<Reservacion> resultado = new ArrayList<Reservacion>();

			final ResultSet resultSet = devolverResulset(criterio);
			try (resultSet) {
				while (resultSet.next()) {
					Reservacion fila = new Reservacion(resultSet.getInt("Id"), resultSet.getDate("FechaEntrada"),
							resultSet.getDate("FechaSalida"), resultSet.getDouble("Valor"),
							resultSet.getString("FormaPago"));
					resultado.add(fila);
				}
				return resultado;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificar(Reservacion reservacion) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE reservacion SET FechaEntrada=?, FechaSalida=?, Valor=?, FormaPago=? WHERE Id=?");
			try (statement) {
				statement.setDate(1, new java.sql.Date(reservacion.getFechaInicio().getTime()));
				statement.setDate(2, new java.sql.Date(reservacion.getFechaFinal().getTime()));
				statement.setDouble(3, reservacion.getValorReservacion());
				statement.setString(4, reservacion.getFormaPago());
				statement.setInt(5, reservacion.getId());
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
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservacion WHERE ID=?");
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

	private boolean esNumeroDouble(String criterio) {
		try {
			Double.parseDouble(criterio);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private ResultSet devolverResulset(String criterio) throws SQLException {
		String sql = "";

		if (criterio.equals("")) {
			sql = "SELECT * FROM reservacion ";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			return resultSet;
		}
		if (esNumeroEntero(criterio)) {
			Integer Id = Integer.parseInt(criterio);
			sql = "SELECT * FROM reservacion WHERE Id = ? ";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, Id);
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			return resultSet;
		}
		if (esNumeroDouble(criterio)) {
			double Valor = Double.parseDouble(criterio);
			sql = "SELECT * FROM reservacion WHERE Valor= ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDouble(1, Valor);
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			return resultSet;
		}
		if (criterio.equalsIgnoreCase("Tarjeta de Credito") || criterio.equalsIgnoreCase("Dinero en efectivo")
				|| criterio.equalsIgnoreCase("Tarjeta de Debito")) {
			sql = "SELECT * FROM reservacion WHERE FormaPago=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, criterio);
			statement.execute();
			ResultSet resultSet = statement.getResultSet();
			return resultSet;
		}
		if (isFecha(criterio)) {
			Date fecha = null;
			try {
				fecha = new SimpleDateFormat("yyyy-MM-dd").parse(criterio);
				System.out.println(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			sql = "SELECT * FROM reservacion WHERE FechaEntrada=? OR FechaSalida=?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setDate(1, new java.sql.Date(fecha.getTime()));
			statement.setDate(2, new java.sql.Date(fecha.getTime()));
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
			e.printStackTrace();
			return false;
		}
	}
}
