package es.unileon.xijoja.hospital.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.SecretarioWindow;
import es.unileon.xijoja.hospital.admin.AdminWindow;

public class ControlerLoginWindow implements ActionListener, KeyListener {

	private LoginWindow window;
	private Logs log;
	private PersonalDAO dao;

	public ControlerLoginWindow(LoginWindow window) {
		dao = new PersonalDAO();
		log = new Logs();
		this.window = window;
	}

	/**
	 * Metodo al que se llama cuando se pulsa el boton login o cuando se presiona la
	 * tecla enter
	 */
	private void enterAction() {
		// LLamamos al dao y nos devuelve la profesion
		String job = dao.getProfessionCorrectUser(window.loginUser.getText(),
				window.loginPassword.getText().toString());

		if (job == null) {
			window.lblLoginError.setText("Ususario o contraseña incorrectos");

		} else {

			if (job.equals("Medico")) {
				log.InfoLog("Se ha logeado como medico el usuario: " + window.loginUser);
				JOptionPane.showMessageDialog(null, "SOY UN MEDICO.", "Login", JOptionPane.INFORMATION_MESSAGE);

			} else if (job.equals("Administrador")) {
				log.InfoLog("Se ha logeado como administrador el usuario: " + window.loginUser.getText());

				AdminWindow adminWindow = null;
				try {
					adminWindow = new AdminWindow(window.loginUser.toString(),
							window.loginPassword.getText().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				adminWindow.setVisible(true);

			} else if (job.equals("Enfermero")) {
				log.InfoLog("Se ha logeado como enfermero el usuario: " + window.loginUser.getText());
				JOptionPane.showMessageDialog(null, "SOY UN ENFERMERO.", "Login", JOptionPane.INFORMATION_MESSAGE);
			} else if (job.equals("Secretario")) {
				log.InfoLog("Se ha logeado como secretario el usuario: " + window.loginUser.getText());
				JOptionPane.showMessageDialog(null, "SOY UN SECRETARIO.", "Login", JOptionPane.INFORMATION_MESSAGE);
				SecretarioWindow windowSecretario = new SecretarioWindow();// Creamos la ventana del
																			// administrador
				windowSecretario.setVisible(true);

			} else {
				log.InfoLog("No se ha podido encontrar la prfofesion: " + job);
				window.lblLoginError.setText("Ususario o contraseña incorrectos");
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Login")) {

			enterAction();// LLamamos a la accion

		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			enterAction();// LLamamos a la accion
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}
