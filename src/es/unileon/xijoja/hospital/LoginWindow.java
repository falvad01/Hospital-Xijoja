package es.unileon.xijoja.hospital;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class LoginWindow extends JFrame {

	Toolkit screen;
	
	private static final int PWIDTH = 750;
	private static final int PHEIGH = 348;

	/* LOGIN */
	JPanel loginPanel;
	private JTextField loginUser;
	private JPasswordField loginPassword;
	private PersonalDAO dao;
	private Logs log = new Logs(); 

	public LoginWindow() throws IOException {
		
		log.InfoLog("SE INICIA LA PANTALLA DE LOGIN");
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);

		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 6, PWIDTH, PHEIGH);

		setUndecorated(true);

		setTitle("Login");

		try {
			initComponents();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initComponents() throws IOException {

		dao = new PersonalDAO();// LLamamos al patron
		
		

		getContentPane().setLayout(null);
		listener list = new listener();

		JButton crossButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/cross.png")));
		crossButton.setBounds(720, 11, 15, 15);
		getContentPane().add(crossButton);
		crossButton.setBackground(null);
		crossButton.setBorder(null);
		crossButton.setOpaque(false);
		crossButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JButton minButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/min.png")));
		minButton.setBorder(null);
		minButton.setBackground(null);
		minButton.setBounds(690, 11, 15, 15);
		minButton.setOpaque(false);
		getContentPane().add(minButton);
		minButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.CROSSHAIR_CURSOR);
			}
		});

		JLabel backgroundLabel = new JLabel(new ImageIcon(LoginWindow.class.getResource("/resources/fondo.jpg")));
		backgroundLabel.setBounds(218, 0, 532, 348);
		getContentPane().add(backgroundLabel);

		loginPanel = new JPanel();
		loginPanel.setBackground(Color.WHITE);
		loginPanel.setBounds(0, 0, 750, 348);
		getContentPane().add(loginPanel);
		loginPanel.setLayout(null);
		JLabel iconLabel = new JLabel(new ImageIcon(LoginWindow.class.getResource("/resources/icon.png")));
		iconLabel.setBounds(23, 62, 45, 45);
		loginPanel.add(iconLabel);

		loginUser = new JTextField();
		loginUser.setBackground(Color.WHITE);
		loginUser.setBounds(83, 115, 115, 20);
		loginPanel.add(loginUser);
		loginUser.setColumns(10);

		loginPassword = new JPasswordField();
		loginPassword.setBounds(83, 146, 115, 20);
		loginPanel.add(loginPassword);

		JLabel lblUser = new JLabel("USER");
		lblUser.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lblUser.setBounds(49, 118, 73, 14);
		loginPanel.add(lblUser);

		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		lblPassword.setBounds(23, 146, 90, 14);
		loginPanel.add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 11));
		btnLogin.setBounds(125, 177, 73, 23);
		loginPanel.add(btnLogin);
		btnLogin.setBackground(null);
		// btnLogin.setBorder(null);
		btnLogin.setOpaque(false);
		btnLogin.addActionListener(list);

	}

	public class listener implements ActionListener {

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Login")) {

				String job = dao.getProfessionCorrectUser(loginUser.getText(),
						loginPassword.getText().toString());// LLamamos al dao y en caso de que el login sea correcto
															// recibimos la profesion, en caso contratio un null

				if (job == null) {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				} else {

					if (job.equals("Medico")) {
						log.InfoLog("Se ha logeado como medico el usuario: " + loginUser);
						JOptionPane.showMessageDialog(null, "SOY UN MEDICO.", "Login", JOptionPane.INFORMATION_MESSAGE);

					} else if (job.equals("Administrador")) {
						log.InfoLog("Se ha logeado como administrador el usuario: " + loginUser);
						AdminWindow window = new AdminWindow(loginUser.toString(), loginPassword.getText().toString());// Creamos la ventana del administrador
						window.setVisible(true);
						

					} else if (job.equals("Enfermero")) {
						log.InfoLog("Se ha logeado como enfermero el usuario: " + loginUser);
						JOptionPane.showMessageDialog(null, "SOY UN ENFERMERO.", "Login",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (job.equals("Secretario")) {
						log.InfoLog("Se ha logeado como secretario el usuario: " + loginUser);
						JOptionPane.showMessageDialog(null, "SOY UN SECRETARIO.", "Login",
						JOptionPane.INFORMATION_MESSAGE);
						SecretarioWindow windowSecretario = new SecretarioWindow();// Creamos la ventana del administrador
						windowSecretario.setVisible(true);

					} else {
						log.InfoLog("No se ha podido encontrar la prfofesion: " + job);
						JOptionPane.showMessageDialog(null, "Profesion incorrecta.", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		}
	}
}