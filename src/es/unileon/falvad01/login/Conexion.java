package es.unileon.falvad01.login;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {
	private static Conexion instance;	// Singleton
	private Connection conn;
	
	/*
	 * Datos de BBDD en local
	 */
	private final String urlServerLocal = "jdbc:mysql://localhost:3306/sql7271929?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String serverLocal = "localhost";
	private final String userLocal = "root";
	private final String passwordLocal = "";
	
	/*
	 * Nombre de BBDD común a servidor y local
	 */
	private final String databaseName = "sql7271929";

	private Conexion() {
		try {
			// obtenemos el driver de para mysql
			Class.forName("com.mysql.jdbc.Driver"); // com.mysql.cj.jdbc.Drivercom.mysql.jdbc.Driver is deprecated in
														// new versions
			// obtenemos la conexión
			/*
			 * Conexión para servidor BBDD remoto
			 */
			//conn = DriverManager.getConnection(urlServer, user, password);
			
			/*
			 * Conexión para BBDD local
			 */
			conn = DriverManager.getConnection(urlServerLocal, userLocal, passwordLocal);

			if (conn == null) {
				JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos.","Conexión BBDD", JOptionPane.ERROR_MESSAGE);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static Conexion getInstance() {
		
		if(instance == null) {
			instance = new Conexion();
		}
		return instance;
	}

	public Connection getConnection() {
		return conn;
	}

	public void desconectar() {
		instance = null;
	}
}
