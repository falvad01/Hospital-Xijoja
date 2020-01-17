package es.unileon.xijoja.hospital.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.medicWindow.MedicWindow;
import es.unileon.xijoja.hospital.nurse.NurseWindow;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow;

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
				MedicWindow windowMedic = null;
				 windowMedic = new MedicWindow();// Creamos la ventana del
				// administrador
				windowMedic.setVisible(true);
				

			} else if (job.equals("Administrador")) {
				log.InfoLog("Se ha logeado como administrador el usuario: " + window.loginUser.getText());

				AdminWindow adminWindow = null;
				try {
					adminWindow = new AdminWindow(window.loginUser.getText().toString(),
							window.loginPassword.getText().toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

				adminWindow.setVisible(true);

			} else if (job.equals("Enfermero")) {
				log.InfoLog("Se ha logeado como enfermero el usuario: " + window.loginUser.getText());
				NurseWindow windowNurse= null;
				
				windowNurse = new NurseWindow(window.loginUser.getText().toString(),window.loginPassword.getText().toString());
				System.out.println();
			
				windowNurse.setVisible(true);

			} else if (job.equals("Secretario")) {
				SecretaryWindow windowSecretario= null;
				log.InfoLog("Se ha logeado como secretario el usuario: " + window.loginUser.getText());
				 windowSecretario = new SecretaryWindow();
				windowSecretario.setVisible(true);

			} else {
				log.InfoLog("No se ha podido encontrar la prfofesion: " + job);
				window.lblLoginError.setText("Ususario o contraseÃ±a incorrectos");
			}
		}
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Login")) {

			enterAction();// LLamamos a la accion

		}else if(arg0.getActionCommand().equals("Resetear la base de datos")){
			try {
				dao.reset();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else if(arg0.getActionCommand().equals("Exportar la base de datos")){
			JFileChooser ch = new JFileChooser();
	        ch.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        int se = ch.showSaveDialog(null);
	        String ruta="";
	        String name = "\\backus.sql";
	        if(se == JFileChooser.APPROVE_OPTION){
	             ruta = ch.getSelectedFile().getPath();
	        }
				   try {
				      Process p = Runtime
				            .getRuntime()
				            .exec("C:\\xampp\\mysql\\bin\\mysqldump -u root -B xijoja -r "+ruta+name);
				      JOptionPane.showMessageDialog(null, "Backus creado: "+ruta);
		            }catch(Exception ex){
		                JOptionPane.showMessageDialog(null, ex.getMessage());
		            }
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
