package es.unileon.xijoja.hospital;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;
import es.unileon.xijoja.hospital.medicWindow.MedicWindow;
import es.unileon.xijoja.hospital.nurse.NurseWindow;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow;

public class main {

	public static void main(String[] args) throws IOException {

		// llamada al login
		 //LoginWindow window = new LoginWindow();
		
		NurseWindow window = null;
		window = new NurseWindow("enfermero", "enfermero");
		
		SecretaryWindow w2=new SecretaryWindow();

		//window.setVisible(true);
		w2.setVisible(true);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
