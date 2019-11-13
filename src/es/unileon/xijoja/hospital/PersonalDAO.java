package es.unileon.xijoja.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;

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
					ret = "F";
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

	public int getLastID() {

		co = Conexion.getInstance();
		conn = co.getConnection();
		int ret = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select idTrabajador from personal");

			int id = 0;
			if (rs.last()) {// Nos posicionamos al final
				ret = rs.getRow();// sacamos la cantidad de filas/registros

			}

			while (rs.next()) {
				// VOLCAR LOS DATOS
			}

		} catch (SQLException e) {

		}
		co.disconect();// Cerramos la conexion con la base de datos

		return ret;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param surname1
	 * @param surname2
	 * @param NIE
	 * @param date
	 * @param textFieldCBancaria
	 * @param object
	 * @param password
	 * @param user
	 * @param Email
	 * @throws SQLException
	 */
	public void addUser(int id, String name, String surname1, String surname2, String NIE, Date date,
			String bankAccount, Object object, String password, String user, String email) throws SQLException {

		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println(bankAccount);
		String sql = "INSERT INTO personal (idTrabajador, Nombre, Apellido1, Apellido2, NIFNIE, FechaAlta, CuentaBancaria, Puesto, contrasenia, usuario, Email) VALUES('"
				+ id + "', '" + name + "', '" + surname1 + "', '" + surname2 + "', '" + NIE + "', '" + date + "', '"
				+ bankAccount + "', '" + object + "', '" + password + "', '" + user + "', '" + email + "')";

		Statement st = conn.createStatement();
		st.executeUpdate(sql);
		System.out.println("se ha introducido una persona");
		co.disconect();// Desconectamos la base de datos

		// Mensaje a enviar por correo
		String msn = "Saludos " + name + " " + surname1 + surname2
				+ ", ha entrado a formar parte de la plantilla del hospital Xijoja, le adjuntamos el usuario y contraseña\n\n"
				+ "Usuario: " + user + "\n" + "Contraseña: " + password;

		Email mail = new Email(email, "NO CONTESTAR A ESTE CORREO\n" + "ALTA HOSPITAL XIJOJA", msn);
		try {
			mail.send();// Enviamos el email
		} catch (IOException e) {

			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos

	}

}