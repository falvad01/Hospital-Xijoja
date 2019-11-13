package es.unileon.xijoja.hospital;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PersonalDAO {

	private Connection conn;
	private Conexion co;

	public PersonalDAO() {
	}

	/**
	 * 
	 * @param user
	 * @param password
	 * @return devuelve la profesion, si el login no es correcto o ha ocurrido algun
	 *         fallo con la profesion devuelve un null
	 */
	public String getProfessionCorrectUser(String user, String password) {
		co = Conexion.getInstance();
		conn = co.getConnection();
		String ret = null;
		
		try {

			Statement st = conn.createStatement();
			// Comprobamos si el usuario y contraseña coinciden
			String sql = "Select * from personal where usuario='" + user + "' and contrasenia= '" + password + "'";
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {

				String profession = rs.getString(8);// Obtenemos la profesion para abrir la ventana correcta
				System.out.println(profession);

				if (profession.equals("Medico")) {
					ret = "Medico";

				} else if (profession.equals("Administrador")) {
					ret = "Administrador";

				} else if (profession.equals("Enfermero")) {
					ret = "Enfermero";
				} else if (profession.equals("Secretario")) {
					ret = "Secretario";

				} else {// FAllo con la profesion
					ret = null;
				}

			} else {// Error en el login
				ret = null;
			}

			co.disconect();// Cerramos la conexion con la base de datos

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
