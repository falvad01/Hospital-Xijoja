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
  		System.out.println("dao"+ret);
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
    

}
 