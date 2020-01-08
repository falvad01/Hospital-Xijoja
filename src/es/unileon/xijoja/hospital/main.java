package es.unileon.xijoja.hospital;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;
import es.unileon.xijoja.hospital.medicWindow.MedicWindow;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow;

public class main {

	public static void main(String[] args) throws IOException {

		// llamada al login
		 //LoginWindow window = new LoginWindow();
		
		AdminWindow window = null;
		try {
			window = new AdminWindow("root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// Creamos la ventana del administrador
		
		LoginWindow w2=new LoginWindow();

		//window.setVisible(true);
		window.setVisible(true);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
