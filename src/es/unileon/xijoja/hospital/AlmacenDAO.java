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
    
}
 