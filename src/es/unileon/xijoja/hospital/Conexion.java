package es.unileon.xijoja.hospital;

import java.sql.*;

import javax.swing.JOptionPane;

public class Conexion {
	private static Conexion instance; // Singleton
	private Connection conn;

	/*
	 * Datos de BBDD en local
	 */
	private final  String urlServerLocal = "jdbc:mysql://localhost:3306/xijoja?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private final  String userLocal = "root";
	private final  String passwordLocal = "";

	
	/**
	 * Constructor privado del singleton
	 */
	private Conexion() {
		try {
			// obtenemos el driver de para mysql
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(urlServerLocal, userLocal, passwordLocal);

			if (conn == null) {
				JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos.", "Conexion BBDD",
						JOptionPane.ERROR_MESSAGE);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtenemos la instancia
	 * 
	 * @return
	 */
	public synchronized static Conexion getInstance() {

		if (instance == null) {
			instance = new Conexion();
		}
		return instance;
	}

	/**
	 * Retornamos la conexion de la base de datos
	 * 
	 * @return
	 */
	public Connection getConnection() {

		return conn;
	}

	/**
	 * Metodo para desconectar la conexion con la base de datos
	 */
	public void disconect() {

		instance = null;
	}

}
