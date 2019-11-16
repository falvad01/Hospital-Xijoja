package es.unileon.xijoja.hospital;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	Toolkit screen;
	private static final int PWIDTH = 969;
	private static final int PHEIGH = 809;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldNIFNIE;
	private JTextField textFieldCBancaria;
	private JTextField textFieldEmail;

	private JLabel lblUsuario;
	private JLabel lblContrasea;

	private PersonalDAO dao;

	@SuppressWarnings("rawtypes")
	JComboBox comboBoxPuesto;
	private JTextField textField;
	private JTable table;

	public AdminWindow() {

		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Login");

		try {
			initComponents();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void initComponents() throws SQLException {

		dao = new PersonalDAO();

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		listener list = new listener();

		JButton crossButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/cross.png")));
		crossButton.setBounds(944, 11, 15, 15);
		getContentPane().add(crossButton);
		crossButton.setBackground(null);
		crossButton.setBorder(null);
		crossButton.setOpaque(false);
		crossButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		JButton btnNewButton = new JButton("Añadir trabajador");
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(28, 33, 234, 57);
		getContentPane().add(btnNewButton);

		JButton minButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/min.png")));
		minButton.setBounds(918, 11, 15, 15);
		getContentPane().add(minButton);
		minButton.setBorder(null);
		minButton.setBackground(null);
		minButton.setOpaque(false);
		minButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.CROSSHAIR_CURSOR);
			}
		});

		JButton btnVerPlantilla = new JButton("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 113, 234, 57);
		getContentPane().add(btnVerPlantilla);

		JButton btnDespedirUsuario = new JButton("Despedir usuario");
		btnDespedirUsuario.setBackground(Color.WHITE);
		btnDespedirUsuario.setOpaque(false);
		btnDespedirUsuario.setBounds(28, 191, 234, 57);
		getContentPane().add(btnDespedirUsuario);

		JPanel RegisterPanel = new JPanel();
		RegisterPanel.setForeground(Color.WHITE);
		RegisterPanel.setBackground(Color.WHITE);
		RegisterPanel.setBounds(284, 11, 650, 706);
		getContentPane().add(RegisterPanel);
		RegisterPanel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNombre.setBounds(10, 27, 63, 23);
		RegisterPanel.add(lblNombre);

		JLabel lblApellidos = new JLabel("1º Apellido");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellidos.setBounds(10, 78, 80, 23);
		RegisterPanel.add(lblApellidos);

		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(100, 30, 143, 20);
		RegisterPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldApellidos = new JTextField();
		textFieldApellidos.setBounds(100, 81, 143, 20);
		RegisterPanel.add(textFieldApellidos);
		textFieldApellidos.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNifnie.setBounds(10, 170, 63, 23);
		RegisterPanel.add(lblNifnie);

		textFieldNIFNIE = new JTextField();
		textFieldNIFNIE.setColumns(10);
		textFieldNIFNIE.setBounds(100, 173, 143, 20);
		RegisterPanel.add(textFieldNIFNIE);

		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria");
		lblCuentaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCuentaBancaria.setBounds(10, 215, 120, 23);
		RegisterPanel.add(lblCuentaBancaria);

		textFieldCBancaria = new JTextField();
		textFieldCBancaria.setColumns(10);
		textFieldCBancaria.setBounds(132, 218, 143, 20);
		RegisterPanel.add(textFieldCBancaria);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPuesto.setBounds(390, 27, 63, 23);
		RegisterPanel.add(lblPuesto);

		comboBoxPuesto = new JComboBox();
		comboBoxPuesto.setBackground(Color.WHITE);
		comboBoxPuesto.setModel(
				new DefaultComboBoxModel(new String[] { "Administrador\t", "Medico", "Enfermero", "Secretario" }));
		comboBoxPuesto.setOpaque(false);
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
		btnGenerate.setBackground(Color.WHITE);
		btnGenerate.setOpaque(false);
		btnGenerate.setBounds(400, 125, 193, 23);
		RegisterPanel.add(btnGenerate);
		btnGenerate.addActionListener(list);

		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(400, 159, 93, 23);
		RegisterPanel.add(lblUsuario);

		lblContrasea = new JLabel("Contraseña");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(503, 159, 90, 23);
		RegisterPanel.add(lblContrasea);

		JButton btnRegister = new JButton("Registrar");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		btnRegister.setBounds(428, 264, 212, 47);
		RegisterPanel.add(btnRegister);

		JLabel lblApellido = new JLabel("2º Apellido");
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido.setBounds(10, 123, 80, 23);
		RegisterPanel.add(lblApellido);

		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(100, 126, 143, 20);
		RegisterPanel.add(textField);

		String[] columnNames = { "Nombre", "Apellido" };
		Object[][] data = { { " id", "", }, { " Nombre", "", }, { " Apellidos", "" }, { " NIE", "" }, { " Fecha", "" },
				{ " Fecha", "" }, { " Cuanta Bancaria", "" }, { " Puesto", "" }, { " Contraseña", "" },
				{ " Usuario", "" }, { " Mail", "" } };

		table = new JTable(data, columnNames);
		table.setRowSelectionAllowed(false);
		table.setBounds(31, 266, 212, 194);
		RegisterPanel.add(table);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(274, 11, 117, 474);
		getContentPane().add(separator);

		JLabel iconLabel = new JLabel("New label");
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		iconLabel.setBounds(10, 273, 252, 194);
		getContentPane().add(iconLabel);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 21, 61, 41);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(20, 11, 117, 41);
		getContentPane().add(separator_2);
		btnRegister.addActionListener(list);

	}

	/**
	 * Metodo para generar una contraseña aleatoria
	 * 
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

	public class listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Generar usuario y contraseña")) {

				StringBuilder sb = new StringBuilder();// Formamos el nombre de usuario

				sb.append(textFieldNombre.getText().charAt(0));// Primera letra del nombre

				String[] parts = textFieldApellidos.getText().split(" ");

				for (int i = 0; i < 3; i++) {
					sb.append(parts[0].charAt(i));// Primeras 3 letras del primer apellido
				}

				for (int i = 0; i < 3; i++) {
					sb.append(parts[1].charAt(i));// Primeras 3 letras del segundo apellido
				}

				// TODO aniadir numeros al final de que nombre de usuario en caso de que se
				// encuentre repetido

				lblUsuario.setText(sb.toString().toLowerCase());
				lblContrasea.setText(randomPassword());// Creamos la contraseña aleatoriamente

			} else if (arg0.getActionCommand().equals("Registrar")) {

				String[] parts = textFieldApellidos.getText().split(" ");// TODO poner apellidos en campos diferentes

				int id = dao.getLastID();

				Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual en
				String[] data;															// formato para usarla en la
				try {
					data = dao.getEmployee(dao.getLastID());
					System.out.println("last ID: "+dao.getLastID());
					table.setValueAt(data[0], 0, 1);
					table.setValueAt(data[1], 1, 1);
					table.setValueAt(data[2], 2, 1);
					table.setValueAt(data[3], 3, 1);
					table.setValueAt(data[4], 4, 1);
					table.setValueAt(data[5], 5, 1);
					table.setValueAt(data[6], 6, 1);
					table.setValueAt(data[7], 7, 1);
					table.setValueAt(data[8], 8, 1);
					table.setValueAt(data[9], 9, 1);
					table.setValueAt(data[10], 10, 1);
					
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
						
				try {
					dao.getEmployee(dao.getLastID() - 1);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				try {
					dao.addUser(id, textFieldNombre.getText(), parts[0], parts[1], textFieldNIFNIE.getText(), date,
							textFieldCBancaria.getText(), comboBoxPuesto.getSelectedItem(), lblContrasea.getText(),
							lblUsuario.getText(), textFieldEmail.getText());
				} catch (SQLException e) {

					e.printStackTrace();
				}

			}
		}
	}
}
