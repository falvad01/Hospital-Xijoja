package es.unileon.xijoja.hospital;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
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
    public void addPaciente(int id, String name, String surname1, String surname2, String NIE,Date date, int room) throws SQLException {
 
        co = Conexion.getInstance();
        conn = co.getConnection();
        

        String sql = "INSERT INTO pacientes (idPaciente, Nombre, Apellido1, Apellido2, NIFNIE, FechaBaja, Habitacion, Enfermedad, fk_idProducto, fk_idMedico, UMedicamento) VALUES(?,?,?,?,?,?,?,?,?,?,?) "; 
        PreparedStatement st = conn.prepareStatement(sql);
        st.setInt(1, id);
        st.setString(2, name);
        st.setString(3, surname1);
        st.setString(4, surname2);
        st.setString(5, NIE);
        st.setDate(6, date);
        st.setInt(7,room );
        st.setNull(8, Types.INTEGER);
        st.setNull(9, Types.INTEGER);
        st.setInt(10, 1);
        st.setNull(11, Types.INTEGER);
        

        st.executeUpdate();
        System.out.println("se ha introducido un paciente");
        co.disconect();// Desconectamos la base de datos
 
       
        co.disconect();// Cerramos la conexion con la base de datos
 
    }
    
    public int getLastID() {
    	 
        co = Conexion.getInstance();
        conn = co.getConnection();
        int ret = -1;
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("Select idPaciente from pacientes");
 
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
    public boolean checkPatientExist(String search, boolean isdni) {
		boolean ret = false;

		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("Aqui parece que si entra");
		String sql ;
		if (isdni) {
			sql = "SELECT * FROM pacientes WHERE NIFNIE='" + search + "'";
		}else {
			sql = "SELECT * FROM pacientes WHERE Habitacion='" + search + "'";
		}
		
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
    public String[] getPatient(String search, boolean isdni) {

		co = Conexion.getInstance();
		conn = co.getConnection();
		System.out.println("DNI: " + search);
		
		String sql;

		if (isdni) {
			search = search.replaceFirst("[\\s\\S]{0,1}$", "");// TODO no funciona, quitar la letra del DNI para que funcione

			 sql = "SELECT * FROM pacientes WHERE NIFNIE=" + search;
		}else {
			 sql = "SELECT * FROM pacientes WHERE Habitacion=" + search;
		}
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
				ret[6] = rs.getString(7); // Habitacion
				ret[7] = rs.getString(8); // Enfermedad
				ret[8] = rs.getString(9); // medicinas
				ret[9] = rs.getString(10); // idmedico
				ret[10] = rs.getString(11);	//cantidad de medicamento

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
     * @param id
     * @return
     * @throws SQLException
     */
//    public String[] getEmployee(int id) throws SQLException {
// 
//        co = Conexion.getInstance();
//        conn = co.getConnection();
// 
//        String sql = "SELECT * FROM personal WHERE IdTrabajador=" + id;
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(sql);
//        String[] ret = new String[11];
//        while (rs.next()) {
//            ret[0] = rs.getString(1); // ID
//            ret[1] = rs.getString(2); // Nombre
//            ret[2] = rs.getString(3); // Apelllido1
//            ret[3] = rs.getString(4); // Apellido2
//            ret[4] = rs.getString(5); // NIE
//            ret[5] = rs.getString(6); // Fecha
//            ret[6] = rs.getString(7); // Cuenta bancaria
//            ret[7] = rs.getString(8); // Puesto
//            ret[8] = rs.getString(9); // Contrasenia
//            ret[9] = rs.getString(10); // Usuario
//            ret[10] = rs.getString(11);
// 
//        }
//        co.disconect();// Cerramos la conexion con la base de datos
//        return ret;
//    }
//   
//    public String[] getEmployee(String DNI) throws SQLException {
// 
//        co = Conexion.getInstance();
//        DNI.replaceFirst("[\\s\\S]{0,1}$", "");//TODO no funciona, quitar la letra del DNI para que funcione
//        conn = co.getConnection();
//        System.out.println("DNI: " + DNI);
//        String sql = "SELECT * FROM personal WHERE NIFNIE=" + DNI;
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(sql);
//        String[] ret = new String[11];
//        while (rs.next()) {
//            ret[0] = rs.getString(1); // ID
//            ret[1] = rs.getString(2); // Nombre
//            ret[2] = rs.getString(3); // Apelllido1
//            ret[3] = rs.getString(4); // Apellido2
//            ret[4] = rs.getString(5); // NIE
//            ret[5] = rs.getString(6); // Fecha
//            ret[6] = rs.getString(7); // Cuenta bancaria
//            ret[7] = rs.getString(8); // Puesto
//            ret[8] = rs.getString(9); // Contrasenia
//            ret[9] = rs.getString(10); // Usuario
//            ret[10] = rs.getString(11);
// 
//        }
//       
//        co.disconect();// Cerramos la conexion con la base de datos
//        return ret;
//       
//       
//    }
// 
//    public void getEmployee(String name, String surname1, String surname2) {
// 
//    }
// 
//    /**
//     *
//     * @return
//     * @throws SQLException
//     */
//    public String[] getNamesEmployees() throws SQLException {
// 
//        co = Conexion.getInstance();
//        conn = co.getConnection();
// 
//        String sql = "SELECT * FROM personal";
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(sql);
//        ArrayList<String> names = new ArrayList<String>();
//        while (rs.next()) {
//            names.add(rs.getString(10));
// 
//        }
//        String[] ret = names.toArray(new String[names.size()]);
//        co.disconect();
//        return ret;
//    }
// 
//    public String[] getJobsEmployees() throws SQLException {
// 
//        co = Conexion.getInstance();
//        conn = co.getConnection();
// 
//        String sql = "SELECT * FROM personal";
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery(sql);
//        ArrayList<String> names = new ArrayList<String>();
//        while (rs.next()) {
//            names.add(rs.getString(8));
// 
//        }
//        String[] ret = names.toArray(new String[names.size()]);
//        co.disconect();
//        return ret;
//    }
// 
//    public ArrayList<String[]> getAllEmployees() throws SQLException {
// 
//        ArrayList<String[]> ret = new ArrayList<String[]>();
// 
//        int lastId = this.getLastID();
// 
//        for (int i = -1; i < lastId; i++) { // TODO no entiendo porque aqui es -1
// 
//            ret.add(getEmployee(i));
// 
//        }
// 
//        return ret;
//    }
 
   
 
}