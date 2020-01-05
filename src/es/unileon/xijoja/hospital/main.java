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
		 LoginWindow window = new LoginWindow();
		//AdminWindow window = new AdminWindow();// Creamos la ventana del administrador

		window.setVisible(true);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
