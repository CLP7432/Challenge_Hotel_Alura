package com.latam.alura.hotel.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.latam.alura.hotel.factory.ConnectionFactory;

public class CrearBaseDatos {

	public static void crearBase() {
		String dbName = "hotel_alura";
		// String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + dbName;

		String[] createTableQueries = {
				"CREATE TABLE IF NOT EXISTS `reservacion` (" + "  `Id` int(15) NOT NULL AUTO_INCREMENT,"
						+ "  `FechaEntrada` date NOT NULL," + "  `FechaSalida` date NOT NULL,"
						+ "  `Valor` double NOT NULL," + "  `FormaPago` varchar(50) NOT NULL," + "  PRIMARY KEY (`Id`)"
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;",
				"CREATE TABLE IF NOT EXISTS `huesped` (" + "  `Id` int(11) NOT NULL AUTO_INCREMENT,"
						+ "  `nombre` varchar(50) NOT NULL," + "  `apellido` varchar(50) NOT NULL,"
						+ "  `fechaNacimiento` date DEFAULT NULL," + "  `nacionalidad` varchar(60) DEFAULT NULL,"
						+ "  `telefono` varchar(12) DEFAULT NULL," + "  `IdReserva` int(11) DEFAULT NULL,"
						+ "  PRIMARY KEY (`Id`),"
						+ "  CONSTRAINT `fk_id_reserva` FOREIGN KEY (`IdReserva`) REFERENCES `reservacion` (`Id`) ON DELETE CASCADE"
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;",
				"CREATE TABLE IF NOT EXISTS `usuarios` (" 
						+ "  `usuario` varchar(15) NOT NULL," 
						+ "  `clave` varchar(30) NOT NULL"						
						+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;" };

		String insertUsuarioQuery = "INSERT INTO `usuarios` ( `usuario`, `clave`) VALUES ('admin', 'admin');";

		try {
			Connection con = new ConnectionFactory().recuperaConexion();
			
			final Statement statement = con.createStatement();			
			try(statement) {
				// Usar la base de datos recién creada
				statement.execute("USE " + dbName);
	
				// Crear las tablas
				for (String createTableQuery : createTableQueries) {
					statement.executeUpdate(createTableQuery);
				}
				// Insertar un nuevo usuario
		        statement.executeUpdate(insertUsuarioQuery);
			}
			System.out.println("Base de datos y tablas creadas exitosamente.");
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
