package es.unileon.falvad01.login;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JButton;

import javax.swing.JPanel;

import com.sun.corba.se.spi.orbutil.fsm.State;

import es.unileon.falvad01.login.LoginWindow.listener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
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

		JLabel lblContrasea = new JLabel("Contraseña");
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

	public class listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Generar usuario y contraseña")) {

				StringBuilder sb = new StringBuilder();
				System.out.println("1");

				sb.append(textFieldNombre.getText().charAt(0));

				String[] parts = textFieldApellidos.getText().split(" ");

				System.out.println(parts[0]);
				System.out.println(parts[1]);

				for (int i = 0; i < 3; i++) {

					sb.append(parts[0].charAt(i));
				}
				for (int i = 0; i < 3; i++) {

					sb.append(parts[1].charAt(i));
				}

				// TODO aniadir numeros al final de que nombre de usuario en caso de que se
				// encuentre repetido

				System.out.println("2");
				lblUsuario.setText(sb.toString().toLowerCase());

			} else if (arg0.getActionCommand().equals("Registrar")) {
				System.out.println("se ha introducido una persona");
				Conexion co = Conexion.getInstance();
				Connection conn = co.getConnection();
				String[] parts = textFieldApellidos.getText().split(" ");
				System.out.println(comboBoxPuesto.getSelectedItem());

				try {
					Statement st = conn.createStatement();
					//TODO para el id, hay que contar el numero de empleados y sumarle uno
					String sql = "INSERT INTO personal (idTrabajador, Nombre, Apellido1, Apellido2, NIFNIE, FechaAlta, CuentaBancaria, Puesto, contrasenia, usuario, Email) VALUES(1, '" + textFieldNombre.getText()+"', '" + parts[0]+ "', '" + parts[1]+"', '" +textFieldNIFNIE.getText() +"', '2019-10-25', '" + textFieldCBancaria.getText()+"', '" +comboBoxPuesto.getSelectedItem() +"', '12345', '" + lblUsuario.getText() +"', '" +textFieldEmail.getText() + "')";

					st.executeUpdate(sql);
					System.out.println("se ha introducido una persona");
					co.disconect();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}
}
