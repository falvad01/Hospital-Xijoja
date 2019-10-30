package es.unileon.falvad01.login;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JButton;

import javax.swing.JPanel;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.DefaultComboBoxModel;

public class AdminWindow extends JFrame {

	Toolkit screen;

	private static final int PWIDTH = 750;
	private static final int PHEIGH = 348;

	private ResultSet rs;

	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldNIFNIE;
	private JTextField textFieldCBancaria;
	private JTextField textFieldEmail;

	private JLabel lblUsuario;
	private JLabel lblContrasea;

	JComboBox comboBoxPuesto;

	public AdminWindow(ResultSet rs) {

		this.rs = rs;
		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 6, 969, 809);

		// setUndecorated(true);

		setTitle("Login");

		try {
			initComponents();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initComponents() throws SQLException {

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		listener list = new listener();

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 79, 252, 57);
		getContentPane().add(btnNewButton);

		JPanel RegisterPanel = new JPanel();
		RegisterPanel.setBounds(272, 11, 671, 723);
		getContentPane().add(RegisterPanel);
		RegisterPanel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(10, 27, 63, 23);
		RegisterPanel.add(lblNombre);

		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellidos.setBounds(10, 72, 63, 23);
		RegisterPanel.add(lblApellidos);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(83, 30, 143, 20);
		RegisterPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(83, 75, 143, 20);
		RegisterPanel.add(textFieldApellidos);
		textFieldApellidos.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNifnie.setBounds(10, 123, 63, 23);
		RegisterPanel.add(lblNifnie);

		textFieldNIFNIE = new JTextField();
		textFieldNIFNIE.setColumns(10);
		textFieldNIFNIE.setBounds(83, 126, 143, 20);
		RegisterPanel.add(textFieldNIFNIE);

		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria");
		lblCuentaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuentaBancaria.setBounds(10, 169, 120, 23);
		RegisterPanel.add(lblCuentaBancaria);

		textFieldCBancaria = new JTextField();
		textFieldCBancaria.setColumns(10);
		textFieldCBancaria.setBounds(140, 172, 143, 20);
		RegisterPanel.add(textFieldCBancaria);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPuesto.setBounds(390, 27, 63, 23);
		RegisterPanel.add(lblPuesto);

		comboBoxPuesto = new JComboBox();
		comboBoxPuesto.setModel(
				new DefaultComboBoxModel(new String[] { "Administrador\t", "Medico", "Enfermero", "Secretario" }));
		comboBoxPuesto.setBounds(450, 29, 133, 23);
		RegisterPanel.add(comboBoxPuesto);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEmail.setBounds(390, 78, 63, 23);
		RegisterPanel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setColumns(10);
		textFieldEmail.setBounds(450, 81, 143, 20);
		RegisterPanel.add(textFieldEmail);

		JButton btnGenerate = new JButton("Generar usuario y contraseña");
		btnGenerate.setBounds(377, 125, 193, 23);
		RegisterPanel.add(btnGenerate);
		btnGenerate.addActionListener(list);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(377, 159, 93, 23);
		RegisterPanel.add(lblUsuario);

		lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(480, 159, 90, 23);
		RegisterPanel.add(lblContrasea);

		JButton btnRegister = new JButton("Registrar");
		btnRegister.setBounds(435, 240, 212, 47);
		RegisterPanel.add(btnRegister);
		btnRegister.addActionListener(list);

		JLabel lbTitle = new JLabel("Administrador: " + this.rs.getString(2) + " " + this.rs.getString(3));
		lbTitle.setBounds(272, 11, 671, 14);
		getContentPane().add(lbTitle);
		setBackground(Color.WHITE);

	}

	
	/**
	 * Metodo para generar una contraseña aleatoria
	 * @return
	 */
	private String randomPassword() {

		String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder password = new StringBuilder();

		int i = 0;
		while (i < 7) {
			int rand = (int) ((Math.random() * ((61 - 0) + 1)));

			password.append(alphabet.charAt(rand));

			i++;
		}

		return password.toString();
	}

	/**
	 * Metodo para enviar un correo a los empleados dados de alta
	 * @param destinatario A quien le enviamos el correo
	 * @param asunto       //Ausinto del correo
	 * @param cuerpo       //Mensaje del correo
	 * @throws IOException
	 */
	private void sendMail(String destinatario, String asunto, String cuerpo) throws IOException {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "hospitalxijoja"; // Para la dirección nomcuenta@gmail.com
		
		/*
		String cadena;
		FileReader f = new FileReader("documents/password");// Leemos el archivo donde esta la contraseña del correo
															// emisor
		BufferedReader b = new BufferedReader(f);
		while ((cadena = b.readLine()) != null) {
			System.out.println(cadena);
		}
		b.close();
		System.out.println(cadena);
*/		

		
		InputStream is = getClass().getResourceAsStream("/documents/password.txt"); //lee el fichero txt de contraseñas
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		while ((linea = br.readLine()) != null) {
			System.out.println(linea);
		}
		
		
		
		Properties props = System.getProperties();
		
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "patata24"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario);
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, "patata24");// Cambiar y pedir la contraseña del archivo
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public class listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Generar usuario y contraseña")) {

				StringBuilder sb = new StringBuilder();//Formamos el nombre de usuario

				sb.append(textFieldNombre.getText().charAt(0));//Primera letra del nombre

				String[] parts = textFieldApellidos.getText().split(" ");

				for (int i = 0; i < 3; i++) {
					sb.append(parts[0].charAt(i));//Primeras 3 letras del primer apellido
				}

				for (int i = 0; i < 3; i++) {
					sb.append(parts[1].charAt(i));//Primeras 3 letras del segundo apellido
				}

				// TODO aniadir numeros al final de que nombre de usuario en caso de que se
				// encuentre repetido

				lblUsuario.setText(sb.toString().toLowerCase());
				lblContrasea.setText(randomPassword());//Creamos la contraseña aleatoriamente

			} else if (arg0.getActionCommand().equals("Registrar")) {
				System.out.println("se ha introducido una persona");
				Conexion co = Conexion.getInstance();
				Connection conn = co.getConnection();
				String[] parts = textFieldApellidos.getText().split(" ");
				System.out.println(comboBoxPuesto.getSelectedItem());

				try {
					Statement st = conn.createStatement();
					// TODO para el id, hay que contar el numero de empleados y sumarle uno
					String sql = "INSERT INTO personal (idTrabajador, Nombre, Apellido1, Apellido2, NIFNIE, FechaAlta, CuentaBancaria, Puesto, contrasenia, usuario, Email) VALUES(1, '"
							+ textFieldNombre.getText() + "', '" + parts[0] + "', '" + parts[1] + "', '"
							+ textFieldNIFNIE.getText() + "', '2019-10-25', '" + textFieldCBancaria.getText() + "', '"
							+ comboBoxPuesto.getSelectedItem() + "', '12345', '" + lblUsuario.getText() + "', '"
							+ textFieldEmail.getText() + "')";

					st.executeUpdate(sql);
					System.out.println("se ha introducido una persona");
					co.disconect();
					// Mensaje a enviar por correo
					String msn = "Saludos, ha entrado a formar parte de la plantilla del hospital Xijoja, de ladjuntamos el usuario y contraseña\n\n"
							+ "Usuario: " + lblUsuario.getText() + "\n" + "Contraseña: " + lblContrasea.getText();
					try {
						sendMail(textFieldEmail.getText(), "Alta en hospital Xijoja", msn);
					} catch (IOException e) {

						e.printStackTrace();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
