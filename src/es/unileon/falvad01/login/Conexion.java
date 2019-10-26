package es.unileon.falvad01.login;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {
	private static Conexion instance;	// Singleton
	private Connection conn;
	
	/*
	 * Datos de BBDD en local
	 */
	private final String urlServerLocal = "jdbc:mysql://localhost:3306/xijoja base de datos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final String serverLocal = "localhost";
	private final String userLocal = "root";
	private final String passwordLocal = "";
	
	/*
	 * Nombre de BBDD comÃºn a servidor y local
	 */
	private final String databaseName = "xijoja base de datos";

	private Conexion() {
		try {
			// obtenemos el driver de para mysql
			Class.forName("com.mysql.jdbc.Driver"); // com.mysql.cj.jdbc.Drivercom.mysql.jdbc.Driver is deprecated in
														// new versions
			// obtenemos la conexiÃ³n
			/*
			 * ConexiÃ³n para servidor BBDD remoto
			 */
			//conn = DriverManager.getConnection(urlServer, user, password);
			
			/*
			 * ConexiÃ³n para BBDD local
			 */
			conn = DriverManager.getConnection(urlServerLocal, userLocal, passwordLocal);

			if (conn == null) {
				JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos.","Conexion BBDD", JOptionPane.ERROR_MESSAGE);
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

	public void disconect() {
		
		instance = null;
	}
}

