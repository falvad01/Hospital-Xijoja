package es.unileon.xijoja.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Xijoja
 *
 */
public class PersonalDAO {

	private Connection conn;
	private Conexion co;

	/**
	 *
	 */
	public PersonalDAO() {
	}

	/**
	 *
	 * @param user
	 * @param password
	 * @return
	 * 
	 * 		Devuelve la profesion, si el login no es correcto o ha ocurrido algun
	 *         fallo con la profesion devuelve un null
	 */
	public String getProfessionCorrectUser(String user, String password) {
		co = Conexion.getInstance();
		conn = co.getConnection();
		String ret = null;

		try {

			Statement st = conn.createStatement();
			// Comprobamos si el usuario y contrase�a coinciden
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

				} else {// Fallo con la profesion
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

	/**
	 *
	 * @return devuelve el ultimo id de la tabla
	 */
	public int getLastID() {

		co = Conexion.getInstance();
		conn = co.getConnection();
		int ret = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select idTrabajador from personal");

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
	 * @param job
	 * @param password
	 * @param user
	 * @param Email
	 * @throws SQLException
	 * 
	 *                      Añadimos un empleado a la base de datos
	 */
	public void addEmployee(int id, String name, String surname1, String surname2, String NIE, Date date,
			String bankAccount, String job, String password, String user, String email) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "INSERT INTO personal (idTrabajador, Nombre, Apellido1, Apellido2, NIFNIE, FechaAlta, CuentaBancaria, Puesto, contrasenia, usuario, Email) VALUES('"
				+ id + "', '" + name + "', '" + surname1 + "', '" + surname2 + "', '" + NIE + "', '" + date + "', '"
				+ bankAccount + "', '" + job + "', '" + password + "', '" + user + "', '" + email + "')";

		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("se ha introducido una persona");
		co.disconect();// Desconectamos la base de datos

		// Mensaje a enviar por correo
		/*
		 * String msn = "Saludos " + name + " " + surname1 + surname2 +
		 * ", ha entrado a formar parte de la plantilla del hospital Xijoja, le adjuntamos el usuario y contrase�a\n\n"
		 * + "Usuario: " + user + "\n" + "Contrase�a: " + password;
		 * 
		 * Email mail = new Email(email, "NO CONTESTAR A ESTE CORREO\n" +
		 * "ALTA HOSPITAL XIJOJA", msn); try { mail.send();// Enviamos el email } catch
		 * (IOException e) { e.printStackTrace(); }
		 */
		co.disconect();// Cerramos la conexion con la base de datos

	}

	/**
	 *
	 * @param id
	 * @return
	 * @throws SQLException
	 * 
	 *                      Obtenemos un empleado introduciendo su ID
	 */
	public String[] getEmployee(int id) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "SELECT * FROM personal WHERE IdTrabajador=" + id;
		Statement st;
		String[] ret = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ret = new String[11];
			while (rs.next()) {
				ret[0] = rs.getString(1); // ID
				ret[1] = rs.getString(2); // Nombre
				ret[2] = rs.getString(3); // Apelllido1
				ret[3] = rs.getString(4); // Apellido2
				ret[4] = rs.getString(5); // NIE
				ret[5] = rs.getString(6); // Fecha
				ret[6] = rs.getString(7); // Cuenta bancaria
				ret[7] = rs.getString(8); // Puesto
				ret[8] = rs.getString(9); // Contrasenia
				ret[9] = rs.getString(10); // Usuario
				ret[10] = rs.getString(11);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}

	/**
	 * 
	 * @param DNI
	 * @return
	 * 
	 * 		Obtenemos un empleado introduciendo su DNI
	 */
	public String[] getEmployee(String DNI) {

		co = Conexion.getInstance();
		DNI = DNI.replaceFirst("[\\s\\S]{0,1}$", "");// TODO no funciona, quitar la letra del DNI para que funcione
		conn = co.getConnection();
		System.out.println("DNI: " + DNI);
		String sql = "SELECT * FROM personal WHERE NIFNIE=" + DNI;
		Statement st;
		String[] ret = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ret = new String[11];
			while (rs.next()) {
				ret[0] = rs.getString(1); // ID
				ret[1] = rs.getString(2); // Nombre
				ret[2] = rs.getString(3); // Apelllido1
				ret[3] = rs.getString(4); // Apellido2
				ret[4] = rs.getString(5); // NIE
				ret[5] = rs.getString(6); // Fecha
				ret[6] = rs.getString(7); // Cuenta bancaria
				ret[7] = rs.getString(8); // Puesto
				ret[8] = rs.getString(9); // Contrasenia
				ret[9] = rs.getString(10); // Usuario
				ret[10] = rs.getString(11);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;

	}

	/**
	 *
	 * @return
	 * @throws SQLException
	 * 
	 *                      Obtnemos los nombres de ususario de los empeados para
	 *                      comprobar que no se repitan
	 */
	public String[] getNamesEmployees() {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "SELECT * FROM personal";
		Statement st;
		ArrayList<String> names = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			names = new ArrayList<String>();
			while (rs.next()) {
				names.add(rs.getString(10));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] ret = names.toArray(new String[names.size()]);
		co.disconect();
		return ret;
	}

	/**
	 * 
	 * @return
	 * 
	 * 		Obtenemos todos los puestos de trbajo para contarlos
	 */
	public String[] getJobsEmployees() {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "SELECT * FROM personal";
		Statement st;
		ArrayList<String> names = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			names = new ArrayList<String>();
			while (rs.next()) {
				names.add(rs.getString(8));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String[] ret = names.toArray(new String[names.size()]);
		co.disconect();
		return ret;
	}

	/**
	 * 
	 * @return
	 * 
	 * 		Metemos todos los empleados en un arraylist
	 */
	public ArrayList<String[]> getAllEmployees() {

		ArrayList<String[]> ret = new ArrayList<String[]>();

		int lastId = this.getLastID();

		for (int i = -1; i < lastId; i++) { // TODO no entiendo porque aqui es -1

			ret.add(getEmployee(i));

		}

		return ret;
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param surname1
	 * @param surname2
	 * @param NIE
	 * @param bankAccount
	 * @param job
	 * @param user
	 * @param email
	 * 
	 *                    Metodo para editar un empleado
	 */
	public void editEmployee(int id, String name, String surname1, String surname2, String NIE, String bankAccount,
			String job, String user, String email) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "UPDATE personal SET Nombre='" + name + "',Apellido1='" + surname1 + "',Apellido2='" + surname2
				+ "',NIFNIE='" + NIE + "',CuentaBancaria='" + bankAccount + "',Puesto='" + job + "',usuario='" + user
				+ "',Email='" + email + "' WHERE idTrabajador = " + id;
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		co.disconect();// Desconectamos la base de datos

	}

	/**
	 * 
	 * @param name
	 * @param surname1
	 * @param surname2
	 * @param DNI
	 * 
	 *                 Metodo para borrar un empleado
	 */
	public void deleteEmployee(String name, String surname1, String surname2, String DNI) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "DELETE FROM personal WHERE Nombre='" + name + "' && Apellido1='" + surname1 + "' && Apellido2='"
				+ surname2 + "' && NIFNIE='" + DNI + "'";
		Statement st;
		try {
			st = conn.createStatement();

			st.executeQuery(sql);
			System.out.println("PAN");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("AQUI");
		co.disconect();// Desconectamos la base de datos
	}

	/**
	 * 
	 * @param DNI
	 * @return
	 * 
	 * 		Metodo para comprobar que el DNI introducido pertenece a algun
	 *         empleado
	 */
	public boolean checkEmployeeExist(String DNI) {
		boolean ret = false;

		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("Aqui ni entra");
		String sql = "SELECT * FROM personal WHERE NIFNIE='" + DNI + "'";
		Statement st;

		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				rs.getString(1); // ID

				if (rs.getString(1) == null) {

					ret = false;
				} else {
					ret = true;
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos

		return ret;

	}

}