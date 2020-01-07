package es.unileon.xijoja.hospital;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;

import java.sql.PreparedStatement;

/**
 *
 * @author Xijoja
 *
 */
public class PacientesDAO {

	private Connection conn;
	private Conexion co;

	/**
	 *
	 */
	public PacientesDAO() {
	}

	/**
	 *
	 * @param user
	 * @param password
	 * @return devuelve la profesion, si el login no es correcto o ha ocurrido algun
	 *         fallo con la profesion devuelve un null
	 */

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
	 */
	public void addPatient(int id, String name, String surname1, String surname2, String NIE, Date date, int room,
			String disease, int idMedic, int idNurse) throws SQLException {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "INSERT INTO pacientes (idPaciente, Nombre, Apellido1, Apellido2, NIFNIE, FechaBaja, Habitacion, Enfermedad, fk_idProducto, fk_idMedico, UMedicamento, fk_idEnfermero) VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setInt(1, id);
		st.setString(2, name);
		st.setString(3, surname1);
		st.setString(4, surname2);
		st.setString(5, NIE);
		st.setDate(6, date);
		st.setInt(7, room);
		st.setString(8, disease);
		st.setNull(9, Types.INTEGER);
		st.setInt(10, idMedic);
		st.setInt(11, 0);
		st.setInt(12, idNurse);

		st.executeUpdate();
		System.out.println("se ha introducido un paciente");
		co.disconect();// Desconectamos la base de datos

		co.disconect();// Cerramos la conexion con la base de datos

	}

	public boolean deletePatient(String name, String surname1, String surname2, String DNI) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		boolean ret = false;
		String sql = "DELETE FROM pacientes WHERE Nombre='" + name + "' AND Apellido1='" + surname1 + "' AND Apellido2='"
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
		System.out.println(ret);
		return ret;
	}

	public void AsignMedicine(int unidades, int medicamento, String DNI) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		try {
			Statement st = conn.createStatement();
			String sql = "UPDATE `pacientes` SET `fk_idProducto` = '" + medicamento + "', `UMedicamento` = '" + unidades
					+ "' WHERE `pacientes`.`NIFNIE` = '" + DNI + "'";
			st.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Desconectamos la base de datos
	}

	/**
	 * 
	 * @param id
	 */
	public void setfkProductoNull(int id) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		try {
			String sql = "UPDATE pacientes SET fk_idProducto=? WHERE pacientes.idPaciente=?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setNull(1, Types.INTEGER);
			st.setInt(2, id);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Desconectamos la base de datos
	}

	/**
	 * 
	 * @param unidades
	 * @param id
	 */
	public void UseMedicine(int unidades, int id) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		try {
			Statement st = conn.createStatement();
			String sql = "UPDATE `pacientes` SET `UMedicamento` = '" + unidades + "' WHERE `pacientes`.`idPaciente` = '"
					+ id + "'";
			st.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Desconectamos la base de datos
	}

	/**
	 * 
	 * @param medic
	 * @param id
	 * @return
	 */
	public ArrayList<String[]> getPatientsByNurseOrMedic(boolean medic, int id) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		ArrayList<String[]> ret = new ArrayList<String[]>();

		try {
			String sql;
			Statement st = conn.createStatement();
			if (medic) {
				sql = "SELECT * FROM pacientes WHERE fk_idMedico='" + id + "'";
			} else {
				sql = "SELECT * FROM pacientes WHERE fk_idEnfermero='" + id + "'";
			}

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			String[] ret2 = new String[2];
			while (rs.next()) {
				ret2 = new String[2];

				ret2[0] = rs.getString(1); // ID
				ret2[1] = rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4); // nombre y apellidos
				ret.add(ret2);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public int getMedicineUnits(int id) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		ArrayList<String[]> ret = new ArrayList<String[]>();

		try {
			String sql;
			Statement st = conn.createStatement();

			sql = "SELECT * FROM pacientes WHERE idPaciente='" + id + "'";

			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				return Integer.parseInt(rs.getString(11)); // ID

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	public int getLastID() {

		co = Conexion.getInstance();
		conn = co.getConnection();
		int ret = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select idPaciente from pacientes");

			while (rs.next()) {
				ret = (ret < rs.getInt(1)) ? rs.getInt(1) : ret;

			}
			System.out.println("El id m�s alto es: " + ret);
		} catch (SQLException e) {

		}
		co.disconect();// Cerramos la conexion con la base de datos

		return ret;
	}

	/**
	 * 
	 * @return
	 */
	public int getLastIDM() {

		co = Conexion.getInstance();
		conn = co.getConnection();
		int ret = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select idPaciente from pacientes");

			if (rs.last()) {// Nos posicionamos al final
				ret = rs.getInt(1);// sacamos la ultima id

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
	 * @return
	 */
	public int getNumRow() {

		co = Conexion.getInstance();
		conn = co.getConnection();
		int ret = -1;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select idPaciente from pacientes");
			int count = 0;

			while (rs.next()) {
				++count;
				// Get data from the current row and use it
			}

			ret = count;
			System.out.println("El numero total de filas es " + ret);
		} catch (SQLException e) {

		}
		co.disconect();// Cerramos la conexion con la base de datos

		return ret;
	}

	/**
	 * 
	 * @param search
	 * @param isdni
	 * @return
	 */
	public boolean checkPatientExist(String search, boolean isdni) {
		boolean ret = false;

		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("Aqui parece que si entra");
		String sql;
		if (isdni) {
			sql = "SELECT * FROM pacientes WHERE NIFNIE='" + search + "'";
		} else {
			sql = "SELECT * FROM pacientes WHERE Habitacion='" + search + "'";
		}

		Statement st;
		
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			//rs.getString(1); // ID
			System.out.println("RS " + !rs.next());
			if (!rs.next()) {
				
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

	/**
	 * 
	 * @param room
	 * @return
	 */
	public boolean checkIfRoomIsBusy(int room) {
		boolean ret = false;
		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("Aqui parece que si entra");
		String sql = "SELECT * FROM pacientes WHERE Habitacion='" + room + "'";

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

			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos

		return ret;

	}

	/**
	 * 
	 * @return
	 */
	public int firstRoomFree() {
		boolean finish = false;
		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("Aqui parece que si entra");
		int room = 1;
		while (!finish) {

			String sql = "SELECT * FROM pacientes WHERE Habitacion='" + room + "'";
			System.out.println(sql);

			try {
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql);

				if (checkIfRoomIsBusy(room)) {

					System.out.println("existe la habitacion numero: " + room);
					room++;
				} else {

					System.out.println("Habitacion libre: " + room);
					finish = true;
					return room;

				}

			} catch (SQLException e) {
				e.printStackTrace();
			}

			co.disconect();// Cerramos la conexion con la base de datos

		}
		return room;
	}

	/**
	 * 
	 * @return
	 */
	public int firstIdFree() {
		boolean finish = false;
		co = Conexion.getInstance();
		conn = co.getConnection();
		int firstId = 0;
		while (!finish) {

			try {
				Statement st = conn.createStatement();
				String sql = "SELECT * FROM pacientes WHERE idPaciente='" + firstId + "'";
				System.out.println(sql);
				ResultSet rs = st.executeQuery(sql);

				if (rs.next()) {
					System.out.println("existe la id numero " + firstId);
					firstId++;
				} else {

					System.out.println("Id libre: " + firstId);
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

	/**
	 * 
	 * @param firstId
	 * @return
	 */
	public boolean checkIfIdIsBusy(int firstId) {
		boolean ret = false;
		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("Aqui parece que si entra");
		String sql = "SELECT * FROM pacientes WHERE ='" + firstId + "'";

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

			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos

		return ret;

	}

	/**
	 * 
	 * @param DNI
	 * @return
	 */
	public String[] getPatientDNI(String DNI) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "SELECT * FROM pacientes WHERE NIFNIE='" + DNI + "'";
		Statement st;
		String[] ret = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ret = new String[12];
			while (rs.next()) {

				ret[0] = rs.getString(1); // ID
				ret[1] = rs.getString(2); // Nombre
				ret[2] = rs.getString(3); // Apelllido1
				ret[3] = rs.getString(4); // Apellido2
				ret[4] = rs.getString(5); // NIE
				ret[5] = rs.getString(6); // Fecha
				ret[6] = rs.getString(7); // Habitacion
				ret[7] = rs.getString(8); // Enfermedad
				ret[8] = rs.getString(9); // medicinas
				ret[9] = rs.getString(10); // idmedico
				ret[10] = rs.getString(11); // cantidad de medicamento
				ret[11] = rs.getString(12); // idenfermero

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public String[] getPatient(int id) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "SELECT * FROM pacientes WHERE idPaciente=" + id;
		Statement st;
		String[] ret = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ret = new String[12];
			while (rs.next()) {

				ret[0] = rs.getString(1); // ID
				ret[1] = rs.getString(2); // Nombre
				ret[2] = rs.getString(3); // Apelllido1
				ret[3] = rs.getString(4); // Apellido2
				ret[4] = rs.getString(5); // NIE
				ret[5] = rs.getString(6); // Fecha
				ret[6] = rs.getString(7); // Habitacion
				ret[7] = rs.getString(8); // Enfermedad
				ret[8] = rs.getString(9); // medicinas
				ret[9] = rs.getString(10); // idmedico
				ret[10] = rs.getString(11); // cantidad de medicamento
				ret[11] = rs.getString(12); // idenfermero

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}

	/**
	 * 
	 * @param id
	 * @param idEmployee
	 * @return
	 */
	public String[] getPatient(int id, int idEmployee, boolean isMedic) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		String sql;
		if (isMedic) {
			sql = "SELECT * FROM pacientes WHERE idPaciente='" + id + "' && fk_idMedico='" + idEmployee + "'";
		} else {
			sql = "SELECT * FROM pacientes WHERE idPaciente='" + id + "' && fk_idEnfermero='" + idEmployee + "'";
		}

		System.out.println(sql);

		String[] ret = null;
		Statement st;
		try {
			st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);
			ret = new String[12];
			while (rs.next()) {

				ret[0] = rs.getString(1); // ID
				ret[1] = rs.getString(2); // Nombre
				ret[2] = rs.getString(3); // Apelllido1
				ret[3] = rs.getString(4); // Apellido2
				ret[4] = rs.getString(5); // NIE
				ret[5] = rs.getString(6); // Fecha
				ret[6] = rs.getString(7); // Habitacion
				ret[7] = rs.getString(8); // Enfermedad
				ret[8] = rs.getString(9); // medicinas
				ret[9] = rs.getString(10); // idmedico
				ret[10] = rs.getString(11); // cantidad de medicamento
				ret[11] = rs.getString(12); // idenfermero

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}

	/**
	 * 
	 * @param search
	 * @param isdni
	 * @return
	 */
	public String[] getPatient(String search, boolean isdni) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("DNI: " + search);

		String sql;

		if (isdni) {
			search = search.replaceFirst("[\\s\\S]{0,1}$", "");// TODO no funciona, quitar la letra del DNI para que
																// funcione

			sql = "SELECT * FROM pacientes WHERE NIFNIE=" + search;
		} else {
			sql = "SELECT * FROM pacientes WHERE Habitacion=" + search;
		}
		Statement st;
		String[] ret = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ret = new String[12];
			while (rs.next()) {
				ret[0] = rs.getString(1); // ID
				ret[1] = rs.getString(2); // Nombre
				ret[2] = rs.getString(3); // Apelllido1
				ret[3] = rs.getString(4); // Apellido2
				ret[4] = rs.getString(5); // NIE
				ret[5] = rs.getString(6); // Fecha
				ret[6] = rs.getString(7); // Habitacion
				ret[7] = rs.getString(8); // Enfermedad
				ret[8] = rs.getString(9); // medicinas
				ret[9] = rs.getString(10); // idmedico
				ret[10] = rs.getString(11); // cantidad de medicamento
				ret[11] = rs.getString(12); // idenfermero

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;

	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<String[]> getAllPatients() {

		ArrayList<String[]> ret = new ArrayList<String[]>();

		int lastId = this.getLastIDM();
		for (int i = 0; i <= lastId; i++) {

			ret.add(getPatient(i));

		}

		return ret;
	}

	/**
	 * 
	 * @param idNurse
	 * @return
	 */
	public ArrayList<String[]> getAllPatients(int idNurse, boolean isMedic) {

		ArrayList<String[]> ret = new ArrayList<String[]>();

		int lastId = this.getLastID();
		String[] retu;
		for (int i = 0; i <= lastId; i++) {
			retu = getPatient(i, idNurse, isMedic);
			System.out.println(retu[0]);
			if (retu[0] != null) {
				System.out.println("a�adido");
				ret.add(retu);
			}

		}

		return ret;
	}

}