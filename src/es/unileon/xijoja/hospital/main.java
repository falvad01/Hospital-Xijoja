package es.unileon.xijoja.hospital;

import java.io.IOException;

import javax.swing.JFrame;

public class main {

	public static void main(String[] args) throws IOException {

		// llamada al login
		 LoginWindow window = new LoginWindow();
		//AdminWindow window = new AdminWindow();// Creamos la ventana del administrador

		window.setVisible(true);

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
