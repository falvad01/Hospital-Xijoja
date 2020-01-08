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
public class AlmacenDAO {
 
	private Connection conn;
    private Conexion co;
 
    /**
     *
     */
    public AlmacenDAO() {
    }
    public String getMedicineName (int id) {

  		co = Conexion.getInstance();
  		conn = co.getConnection();
  		ArrayList<String[]> ret = new ArrayList<String[]>();

  		
  		
  		try {
  			String sql;
  			Statement st = conn.createStatement();
  	  
  	  			sql = "SELECT * FROM almacen WHERE idProducto='"+id+"'";  	 	 
  			
  	  		
  	
  	  		System.out.println(sql);
  			ResultSet rs = st.executeQuery(sql);
  			while (rs.next()) {
  	  		

  				return rs.getString(3); // name
  				

  			}
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}

  		co.disconect();// Cerramos la conexion con la base de datos
  		return "";
  	}
    
    public int MedicineA(int unidades, int medicamento) {

  		co = Conexion.getInstance();
  		conn = co.getConnection();
  		int ret = 0;
  		int UActuales;
		String sql = "SELECT `Cantidad` FROM `almacen` WHERE `idProducto`= '"+medicamento+"'";		  

  		try {
  			Statement st = conn.createStatement();
  			st.executeQuery(sql);
  			ResultSet rs = st.executeQuery(sql);
 			while (rs.next()) {
 				
 				ret = rs.getInt(1);
 				
 				
 			}
 			
 			
 	  		
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		
  		int UBase =ret;
	  		
  	  		
  		co.disconect();// Desconectamos la base de datos
  		
  		return UBase;
  	}
    
    
    
    public int Medicine(int unidades, int medicamento) {

  		co = Conexion.getInstance();
  		conn = co.getConnection();
  		int ret = 0;
  		int UActuales;
		String sql = "SELECT `Cantidad` FROM `almacen` WHERE `idProducto`= '"+medicamento+"'";		  

  		try {
  			Statement st = conn.createStatement();
  			st.executeQuery(sql);
  			ResultSet rs = st.executeQuery(sql);
 			while (rs.next()) {
 				
 				ret = rs.getInt(1);
 				
 				
 			}
 			
 			
 	  		
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		
  		int UBase =ret;
	  		UActuales=UBase-unidades;
	  		
  		
  		
  		co.disconect();// Desconectamos la base de datos
  		
  		return UActuales;
  	}
    
    public void restMedicine(int unidades, int medicamento) {

  		co = Conexion.getInstance();
  		conn = co.getConnection();
  		
  		int UActuales;
  		UActuales=Medicine(unidades, medicamento);
  		System.out.println(UActuales);
  		try {
  			Statement st = conn.createStatement();
  			String sql = "UPDATE `almacen` SET `Cantidad` = '"+UActuales+"' WHERE `almacen`.`idProducto` = '"+medicamento+"'";	  
  			st.executeUpdate(sql);

  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		
  		co.disconect();// Desconectamos la base de datos
  	}
    
    public ArrayList<String[]> getMedicine() {

  		co = Conexion.getInstance();
  		conn = co.getConnection();
  		ArrayList<String[]> ret = new ArrayList<String[]>();

  		String sql = "SELECT * FROM almacen WHERE idProducto=?" ;
  		
  		try {
  			PreparedStatement st = conn.prepareStatement(sql);
  	 	        st.setString(1, "Medicinas");
  	 	    
  			ResultSet rs = st.executeQuery();
  			String[] ret2 = new String[2];
  			while (rs.next()) {
  	  			ret2 = new String[2];

  				ret2[0] = rs.getString(1); // ID
  				ret2[1] = "Medicamento. "+ rs.getString(2) + " "+ rs.getString(3)+" "+ rs.getString(4); //nombre y apellidos
  				ret.add(ret2);

  			}
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}

  		co.disconect();// Cerramos la conexion con la base de datos
  		return ret;
  	}
    
    
    public String[] getMedicine(int id) {

		co = Conexion.getInstance();
		conn = co.getConnection();

		String sql = "SELECT * FROM almacen WHERE idProducto=" + id;
		Statement st;
		String[] ret = null;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ret = new String[4];
			while (rs.next()) {
				
				ret[0] = rs.getString(1); // ID
				ret[1] = rs.getString(2); // Cantidad
				ret[2] = rs.getString(3); // Nombre
				ret[3] = rs.getString(4); // Iteraciones
				


			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}
    
   
	public ArrayList<String[]> getMedicineMedic() {
		

			co = Conexion.getInstance();
			conn = co.getConnection();
			ArrayList<String[]> ret = new ArrayList<String[]>();

			try {
				String sql;
				Statement st = conn.createStatement();
				
					sql = "SELECT * FROM almacen";
				

				
				ResultSet rs = st.executeQuery(sql);
				String[] ret2 = new String[2];
				while (rs.next()) {
					ret2 = new String[2];

					ret2[0] = rs.getString(1); // ID
					ret2[1] = rs.getString(3); // nombre y apellidos
					ret.add(ret2);
					

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			co.disconect();// Cerramos la conexion con la base de datos
			return ret;
		}
	
	public int getCountMedicine(String medicine) {
		
		
		co = Conexion.getInstance();
		conn = co.getConnection();

		Statement st;
		String sql = "SELECT * FROM almacen WHERE Nombre='" + medicine + "'";

		int ret = 0;
		try {
			st = conn.createStatement();

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				ret = rs.getInt(2); // cantidad

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		co.disconect();// Cerramos la conexion con la base de datos
		return ret;
	}
}
 
