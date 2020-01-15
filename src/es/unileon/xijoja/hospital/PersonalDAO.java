package es.unileon.xijoja.hospital;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import es.unileon.xijoja.hospital.login.LoginWindow;

/**
 *
 * @author Xijoja
 *
 */
public class PersonalDAO {

	private Connection conn;
	private Conexion co;
	private Logs log;

	/**
	 *
	 */
	public PersonalDAO() {
		log = new Logs();
	}

	/**
	 *
	 * @param user
	 * @param password
	 * @return
	 * 
	 *         Devuelve la profesion, si el login no es correcto o ha ocurrido algun
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
		int ret = 0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select idTrabajador from personal");

			while (rs.next()) {
				ret = (ret < rs.getInt(1)) ? rs.getInt(1) : ret;

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
	 * @param DNI
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
	public ArrayList<String[]> getNuseAndMedic(boolean medic) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		ArrayList<String[]> ret = new ArrayList<String[]>();

		String sql = "SELECT * FROM personal WHERE Puesto=?";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			if (medic) {

				st.setString(1, "Medico");

			} else {

				st.setString(1, "Enfermero");
			}

			ResultSet rs = st.executeQuery();
			String[] ret2 = new String[2];
			while (rs.next()) {
				ret2 = new String[2];

				ret2[0] = rs.getString(1); // ID
				ret2[1] = "Dr. " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4); // nombre y
																									// apellidos
				ret.add(ret2);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}

	public void addEmployee(int id, String name, String surname1, String surname2, String DNI, Date date,
			String bankAccount, String job, String password, String user, String email) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "INSERT INTO personal (idTrabajador, Nombre, Apellido1, Apellido2, NIFNIE, FechaAlta, CuentaBancaria, Puesto, contrasenia, usuario, Email) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			st.setString(2, name);
			st.setString(3, surname1);
			st.setString(4, surname2);
			st.setString(5, DNI);
			st.setDate(6, date);
			st.setString(7, bankAccount);
			st.setString(8, job);
			st.setString(9, password);
			st.setString(10, user);
			st.setString(11, email);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Desconectamos la base de datos

		// Mensaje a enviar por correo

		String msn = "Saludos " + name + " " + surname1 + surname2
				+ ", ha entrado a formar parte de la plantilla del hospital Xijoja, le adjuntamos el usuario y contrase�a\n\n"
				+ "Usuario: " + user + "\n" + "Contrase�a: " + password;

		Email mail = new Email(email, "NO CONTESTAR A ESTE CORREO\n" + "ALTA HOSPITAL XIJOJA", msn);
		try {
			mail.send();// Enviamos el email }

		} catch (IOException e) {
			e.printStackTrace();

		}

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
	 *         Obtenemos un empleado introduciendo su DNI
	 */
	public String[] getEmployee(String DNI) {

		co = Conexion.getInstance();
		DNI = DNI.replaceFirst("[\\s\\S]{0,1}$", "");
		conn = co.getConnection();

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
			e.printStackTrace();
		}
		System.out.println("En ret");
		for (int i = 0; i < ret.length; i++) {
			System.out.println(ret[i]);
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
	 *         Obtenemos todos los puestos de trbajo para contarlos
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
	 *         Metemos todos los empleados en un arraylist
	 */
	public ArrayList<String[]> getAllEmployees() {

		ArrayList<String[]> ret = new ArrayList<String[]>();

		int lastId = this.getLastID();//

		for (int i = 0; i <= lastId; i++) {
			ret.add(getEmployee(i));

		}

		return ret;
	}
	// esto es inutil de momento
//	public ArrayList<String[]> getAllMedicOrNurse(boolean medic) {
//		System.out.println("ghfghf");
//
//		ArrayList<String[]> ret = new ArrayList<String[]>();
//
//		int lastId = this.getLastID();
//
//		for (int i = -1; i < lastId; i++) { // TODO no entiendo porque aqui es -1
//
//			ret.add(getNuseAndMedic(medic));
//
//		}
//
//		return ret;
//	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param surname1
	 * @param surname2
	 * @param DNI
	 * @param bankAccount
	 * @param job
	 * @param user
	 * @param email
	 * 
	 *                    Metodo para editar un empleado
	 */
	public void editEmployee(int id, String name, String surname1, String surname2, String DNI, String bankAccount,
			String job, String user, String email) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "UPDATE personal SET Nombre='" + name + "',Apellido1='" + surname1 + "',Apellido2='" + surname2
				+ "',NIFNIE='" + DNI + "',CuentaBancaria='" + bankAccount + "',Puesto='" + job + "',usuario='" + user
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
	public boolean deleteEmployee(String name, String surname1, String surname2, String DNI) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		boolean ret = false;
		String sql = "DELETE FROM personal WHERE Nombre='" + name + "' AND Apellido1='" + surname1 + "' AND Apellido2='"
				+ surname2 + "' AND NIFNIE='" + DNI + "'";
		Statement st;

		try {
			st = conn.createStatement();
			int rs = st.executeUpdate(sql);

			if (rs == 0) {
				ret = false;
			} else {
				ret = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		co.disconect();// Desconectamos la base de datos

		return ret;
	}

	/**
	 * 
	 * @param DNI
	 * @return
	 * 
	 *         Metodo para comprobar que el DNI introducido pertenece a algun
	 *         empleado
	 */
	public boolean checkEmployeeExist(String DNI) {
		boolean ret = false;

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "SELECT * FROM personal WHERE NIFNIE='" + DNI + "'";
		Statement st;

		System.out.println(sql);
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			if (!rs.isBeforeFirst()) {

				ret = false;
			} else {
				ret = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		System.out.println(ret);
		return ret;

	}

	public int getIdByUserAndPass(String user, String pass) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		Statement st;
		String sql = "SELECT * FROM personal WHERE usuario='" + user + "' && contrasenia='" + pass + "'";

		int ret = 0;
		try {
			st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ret = rs.getInt(1); // ID

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;

	}

	public int firstIdFree() {
		boolean finish = false;
		co = Conexion.getInstance();
		conn = co.getConnection();
		int firstId = 0;
		while (!finish) {

			try {
				Statement st = conn.createStatement();
				String sql = "SELECT * FROM personal WHERE idTrabajador='" + firstId + "'";

				ResultSet rs = st.executeQuery(sql);

				if (rs.next()) {

					firstId++;
				} else {

					finish = true;
					return firstId;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			co.disconect();// Cerramos la conexion con la base de datos

		}
		return firstId;
	}

	public void reset() throws IOException {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "DROP TABLE `pacientes`, `personal`, `almacen`, `eliminados`";
		Statement st;
		try {
			st = conn.createStatement();

			st.execute(sql);
			log.InfoLog("Borrada correctamente la base de datos");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		//File a = new File(PersonalDAO.class.getClassLoader().getResource("resources/xijoja_base_de_datos.sql"));
		InputStream resourceStream =  PersonalDAO.class.getResourceAsStream("/resources/xijoja_base_de_datos.sql");
		InputStreamReader r = new InputStreamReader(resourceStream);
		BufferedReader in = new BufferedReader(r);
		String str;
		StringBuffer sb = new StringBuffer();

		try {
			st = conn.createStatement();
			String line = null;
			// read script line by line
			while ((line = in.readLine()) != null) {
				// execute query
				st.execute(line);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close file reader
			if (in != null) {
				in.close();
			}

		}

		System.out.println("---------------------BASE DE DATOS RESETEADA----------------------");
		log.InfoLog("Base de datos reseteada completamente");

		co.disconect();// Desconectamos la base de datos
	}

}