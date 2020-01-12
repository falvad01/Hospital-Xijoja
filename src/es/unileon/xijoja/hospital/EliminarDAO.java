package es.unileon.xijoja.hospital;


import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JTextField;

import java.sql.PreparedStatement;

/**
 *
 * @author Xijoja
 *
 */
public class EliminarDAO {

	private Connection conn;
	private Conexion co;

	/**
	 *
	 */
	public EliminarDAO() {
	}
	
	public void eliminadosAct(int cont) {

  		co = Conexion.getInstance();
  		conn = co.getConnection();
  		Instant instant = Instant.now();  			
  		ZoneId zoneId = ZoneId.of( "Europe/Madrid" );
  		ZonedDateTime zdt = ZonedDateTime.ofInstant( instant , zoneId );
  		DayOfWeek dow = DayOfWeek.from( zdt );
  		int dowNumber = dow.getValue();
  		Locale locale = Locale.ENGLISH;
  		String output = dow.getDisplayName( TextStyle.FULL , locale );
  		int elim;
  		int T=getEliminated();
  		System.out.println("eliminadosAct"+output);
  		
  		try {
  			Statement st = conn.createStatement();
  			if(output.equals("Monday")) {
  				elim=0;
  				String sql = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";
  				elim=cont+T;
  				String sql1 = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";
  				System.out.println("contenido elim"+elim);
	  			st.executeUpdate(sql);	
	  			st.executeUpdate(sql1);	
  
  			}else if(output.equals("Thuesday")){
  				elim=cont+T;
	  			String sql = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

	  			st.executeUpdate(sql);	
			
  			}else if(output.equals("Wednesday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
		
  			}else if(output.equals("Thursday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
		
  			}else if(output.equals("Friday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
	
  			}else if(output.equals("Saturday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);
  				st.executeUpdate(sql);	
	
  			}else if(output.equals("Sunday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `eliminado` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
  			}
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		
  		co.disconect();// Desconectamos la base de datos
  	}
	public void eliminadosB(int cont) {

  		co = Conexion.getInstance();
  		conn = co.getConnection();
  		Instant instant = Instant.now();  			
  		ZoneId zoneId = ZoneId.of( "Europe/Madrid" );
  		ZonedDateTime zdt = ZonedDateTime.ofInstant( instant , zoneId );
  		DayOfWeek dow = DayOfWeek.from( zdt );
  		int dowNumber = dow.getValue();
  		Locale locale = Locale.ENGLISH;
  		String output = dow.getDisplayName( TextStyle.FULL , locale );
  		int elim;
  		int T=getAdd();  		
	
  		try {
  			Statement st = conn.createStatement();
  			
  	  		//int T=Integer.parseInt(sqlT);
  			
  			if(output.equals("Monday")) {
  				elim=0;
  				String sql = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";
  				elim=cont+T;
  				String sql1 = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";
  				System.out.println("contenido elim"+elim);
	  			st.executeUpdate(sql);	
	  			st.executeUpdate(sql1);	
  
  			}else if(output.equals("Thuesday")){
  				elim=cont+T;
	  			String sql = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

	  			st.executeUpdate(sql);	
			
  			}else if(output.equals("Wednesday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
		
  			}else if(output.equals("Thursday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
		
  			}else if(output.equals("Friday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
	
  			}else if(output.equals("Saturday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);
  				st.executeUpdate(sql);	
	
  			}else if(output.equals("Sunday")){
  				elim=cont+T;
  				String sql = "UPDATE `eliminados` SET `añadido` = '"+elim+"'";	
  				System.out.println("contenido elim"+elim);

  				st.executeUpdate(sql);	
  			}
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  		
  		co.disconect();// Desconectamos la base de datos
  	}
	
	 public int getEliminated() {

	  		co = Conexion.getInstance();
	  		conn = co.getConnection();
	  		int ret = 0;
			String sql = "SELECT `eliminado` FROM `eliminados`";		  

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
	  		
	  		int elm =ret;
		  	System.out.println("eliminadosget"+ret);
	  	  		
	  		co.disconect();// Desconectamos la base de datos
	  		
	  		return elm;
	  	}
	 
	 public int getAdd() {

	  		co = Conexion.getInstance();
	  		conn = co.getConnection();
	  		int ret = 0;
			String sql = "SELECT `añadido` FROM `eliminados`";		  

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
	  		
	  		int elm =ret;
		  	System.out.println("eliminadosget"+ret);
	  	  		
	  		co.disconect();// Desconectamos la base de datos
	  		
	  		return elm;
	  	}
		public void eliminadosM(int cont) {

	  		co = Conexion.getInstance();
	  		conn = co.getConnection();
	  		Instant instant = Instant.now();  			
	  		ZoneId zoneId = ZoneId.of( "Europe/Madrid" );
	  		ZonedDateTime zdt = ZonedDateTime.ofInstant( instant , zoneId );
	  		DayOfWeek dow = DayOfWeek.from( zdt );
	  		int dowNumber = dow.getValue();
	  		Locale locale = Locale.ENGLISH;
	  		String output = dow.getDisplayName( TextStyle.FULL , locale );
	  		int elim;
	  		int T=getElimMedicines();  		
		
	  		try {
	  			Statement st = conn.createStatement();
	  			
	  	  		//int T=Integer.parseInt(sqlT);
	  			
	  			if(output.equals("Monday")) {
	  				elim=0;
	  				String sql = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";
	  				elim=cont+T;
	  				String sql1 = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";
		  			st.executeUpdate(sql);	
		  			st.executeUpdate(sql1);	
	  
	  			}else if(output.equals("Thuesday")){
	  				elim=cont+T;
		  			String sql = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";	

		  			st.executeUpdate(sql);	
				
	  			}else if(output.equals("Wednesday")){
	  				elim=cont+T;
	  				String sql = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";	

	  				st.executeUpdate(sql);	
			
	  			}else if(output.equals("Thursday")){
	  				elim=cont+T;
	  				String sql = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";	

	  				st.executeUpdate(sql);	
			
	  			}else if(output.equals("Friday")){
	  				elim=cont+T;
	  				String sql = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";

	  				st.executeUpdate(sql);	
		
	  			}else if(output.equals("Saturday")){
	  				elim=cont+T;
	  				String sql = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";	
	  				st.executeUpdate(sql);	
		
	  			}else if(output.equals("Sunday")){
	  				elim=cont+T;
	  				String sql = "UPDATE `eliminados` SET `medicinas` = '"+elim+"'";	

	  				st.executeUpdate(sql);	
	  			}
	  		} catch (SQLException e) {
	  			e.printStackTrace();
	  		}
	  		
	  		co.disconect();// Desconectamos la base de datos
	  	}
		
		 public int getElimMedicines() {

		  		co = Conexion.getInstance();
		  		conn = co.getConnection();
		  		int ret = 0;
				String sql = "SELECT `medicinas` FROM `eliminados`";		  

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
		  		
		  		int elm =ret;
			  	System.out.println("eliminadosget"+ret);
		  	  		
		  		co.disconect();// Desconectamos la base de datos
		  		
		  		return elm;
		  	}
	
}