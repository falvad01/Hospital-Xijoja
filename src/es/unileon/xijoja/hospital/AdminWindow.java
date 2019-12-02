package es.unileon.xijoja.hospital;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javax.swing.border.BevelBorder;

//TODO lanzar excepcion cuando algun cmapo este vacio
//TODO mostrar en tabla todos los empleados
//TODO establecer limites de empleados y pacientes

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	private JPanel seeEmployeesPanel;
	private JPanel addEmployeePane;

	Toolkit screen;
	
	private JTextField textFieldName;
	private JTextField textFieldSurname1;
	private JTextField textFieldNIFNIE;
	private JTextField textFieldBankAccount;
	private JTextField textFieldEmail;

	private JLabel lblUser;
	private JLabel lblPassword;

	private PersonalDAO dao;

	
	JComboBox<Object> comboBoxJob;
	private JTextField textFieldSurname2;
	

	public AdminWindow() {

		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Login");

		try {
			initComponents();
			initComponentsAddEmployeePanel();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		dao = new PersonalDAO();
	}

	private void initComponents() {

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

		JButton minButton = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/min.png")));
		minButton.setBounds(918, 11, 15, 15);
		getContentPane().add(minButton);
		minButton.setBorder(null);
		minButton.setBackground(null);
		minButton.setOpaque(false);
		minButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.CROSSHAIR_CURSOR);
			}
		});

		JButton btnNewButton = new JButton("Añadir trabajador");
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(28, 33, 234, 57);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(list);

		JButton btnVerPlantilla = new JButton("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 113, 234, 57);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(list);

		JButton btnFireEmployee = new JButton("Despedir trabajador");
		btnFireEmployee.setBackground(Color.WHITE);
		btnFireEmployee.setOpaque(false);
		btnFireEmployee.setBounds(28, 191, 234, 57);
		getContentPane().add(btnFireEmployee);
		btnFireEmployee.addActionListener(list);

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

	}

	private void initComponentsAddEmployeePanel() throws SQLException {

		listener list = new listener();

		seeEmployeesPanel = new JPanel();

		seeEmployeesPanel.setLayout(null);
		seeEmployeesPanel.setForeground(Color.WHITE);
		seeEmployeesPanel.setBackground(Color.WHITE);
		seeEmployeesPanel.setBounds(284, 11, 624, 450);
		seeEmployeesPanel.setPreferredSize(new Dimension(630, 700));
		seeEmployeesPanel.setVisible(false);
		getContentPane().add(seeEmployeesPanel);

		addEmployeePane = new JPanel();
		addEmployeePane.setForeground(Color.WHITE);
		addEmployeePane.setBackground(Color.WHITE);
		addEmployeePane.setBounds(284, 11, 630, 450);
		getContentPane().add(addEmployeePane);
		addEmployeePane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 27, 63, 23);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblNombre);

		JLabel lblApellidos = new JLabel("1º Apellido");
		lblApellidos.setBounds(10, 78, 80, 23);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblApellidos);

		textFieldName = new JTextField();
		textFieldName.setBounds(100, 30, 143, 20);
		addEmployeePane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname1 = new JTextField();
		textFieldSurname1.setBounds(100, 81, 143, 20);
		addEmployeePane.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setBounds(10, 170, 63, 23);
		lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblNifnie);

		textFieldNIFNIE = new JTextField();
		textFieldNIFNIE.setBounds(100, 173, 143, 20);
		textFieldNIFNIE.setColumns(10);
		addEmployeePane.add(textFieldNIFNIE);

		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria");
		lblCuentaBancaria.setBounds(10, 215, 120, 23);
		lblCuentaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblCuentaBancaria);

		textFieldBankAccount = new JTextField();
		textFieldBankAccount.setBounds(132, 218, 143, 20);
		textFieldBankAccount.setColumns(10);
		addEmployeePane.add(textFieldBankAccount);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(390, 27, 63, 23);
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblPuesto);

		comboBoxJob = new JComboBox<Object>();
		comboBoxJob.setBounds(450, 29, 133, 23);
		comboBoxJob.setBackground(Color.WHITE);
		comboBoxJob.setModel(
				new DefaultComboBoxModel<Object>(new String[] { "Administrador\t", "Medico", "Enfermero", "Secretario" }));
		comboBoxJob.setOpaque(false);
		addEmployeePane.add(comboBoxJob);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(390, 78, 63, 23);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(450, 81, 143, 20);
		textFieldEmail.setColumns(10);
		addEmployeePane.add(textFieldEmail);

		JButton btnGenerate = new JButton("Generar usuario y contraseña");
		btnGenerate.setBounds(400, 125, 193, 23);
		btnGenerate.setBackground(Color.WHITE);
		btnGenerate.setOpaque(false);
		addEmployeePane.add(btnGenerate);
		btnGenerate.addActionListener(list);

		lblUser = new JLabel("Usuario");
		lblUser.setBounds(400, 159, 93, 23);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblUser);

		lblPassword = new JLabel("Contraseña");
		lblPassword.setBounds(503, 159, 90, 23);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblPassword);

		JButton btnRegister = new JButton("Registrar");
		btnRegister.setBounds(390, 256, 212, 47);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		addEmployeePane.add(btnRegister);
		btnRegister.addActionListener(list);

		JLabel lblApellido = new JLabel("2º Apellido");
		lblApellido.setBounds(10, 123, 80, 23);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblApellido);

		textFieldSurname2 = new JTextField();
		textFieldSurname2.setBounds(100, 126, 143, 20);
		textFieldSurname2.setColumns(10);
		addEmployeePane.add(textFieldSurname2);

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

	/**
	 * 
	 * @author xijoja
	 *
	 */
	public class listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Generar usuario y contraseña")) {

				StringBuilder sbName = new StringBuilder();// Formamos el nombre de usuario

				sbName.append(textFieldName.getText().charAt(0));// Primera letra del nombre

				sbName.append(textFieldSurname1.getText().charAt(0));
				sbName.append(textFieldSurname1.getText().charAt(1));// Dos primeras letras del primer apellido
				sbName.append(textFieldSurname2.getText().charAt(0));
				sbName.append(textFieldSurname2.getText().charAt(1));// Dos primeras letras del segundo apellido

				String[] names = null;

				try {
					names = dao.getNamesEmployees();
				} catch (SQLException e) {

					e.printStackTrace();
				}
				int numberOfUser = 0;
				for (int i = 1; i < names.length; i++) {

					char[] nameBUffer = names[i].toCharArray();
					char[] secondBuffer = new char[5];

					for (int j = 0; j < 5; j++) {
						secondBuffer[j] = nameBUffer[j];// Quitamos los numero del nombre de usuario
					}

					if (String.valueOf(secondBuffer).equals(sbName.toString().toLowerCase())) {

						numberOfUser++;// Contamos los ususarios con el mismo nombre y aniadimos un numero para que no
										// se repita

					}
				}

				sbName.append(numberOfUser);// Aniadimos el numero

				lblUser.setText(sbName.toString().toLowerCase());
				lblPassword.setText(randomPassword());// Generamos la contraseña aleatoriamente

			} else if (arg0.getActionCommand().equals("Registrar")) {

				int id = dao.getLastID();

				Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual

				try {
					dao.getEmployee(dao.getLastID() - 1);
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
				try {
					dao.addUser(id, textFieldName.getText(), textFieldSurname1.getText(), textFieldSurname2.getText(),
							textFieldNIFNIE.getText(), date, textFieldBankAccount.getText(),
							comboBoxJob.getSelectedItem().toString(), lblPassword.getText(), lblUser.getText(),
							textFieldEmail.getText());
				} catch (SQLException e) {

					e.printStackTrace();
				}

			} else if (arg0.getActionCommand().equals("Añadir trabajador")) {

				seeEmployeesPanel.setVisible(false);
				addEmployeePane.setVisible(true);

			} else if (arg0.getActionCommand().equals("Ver plantilla")) {
				seeEmployeesPanel.setVisible(true);
				addEmployeePane.setVisible(false);

				ArrayList<String[]> insert = null;

				try {
					String[] titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "Fecha",
							"Cuenta Bancaria", "Puesto", "Contraseña", "Usuario", "Email" }; //Titulos de la tabla de los empreados
					insert = dao.getAllEmployees();//ArrayList de Arrays
					System.out.println("Size " + insert.size());
					String[][] matrixToInsert = new String[insert.size() + 1][11];

					for (int i = 0; i < insert.size(); i++) { //rellenamos la matriz que meteremos en la tabla a partir del ArrayList de arrays devuelto del DAO
						for (int j = 0; j < 11; j++) {
							if (i == 0) {

								matrixToInsert[i][j] = titles[j];
								System.out.println(matrixToInsert[0][j]);

							} else {
								matrixToInsert[i][j] = insert.get(i)[j];
							}
						}
					}

					for (int i = 0; i < matrixToInsert.length; i++) {
						for (int j = 0; j < matrixToInsert[i].length; j++) {
							System.out.print(matrixToInsert[i][j] + " "); // Imprime elemento
						}
						System.out.println(); // Imprime salto de línea
					}

					JTable employeesTable = new JTable(matrixToInsert, titles);
					employeesTable.setCellSelectionEnabled(true);
					employeesTable.setBounds(20, 20, 600, 456);
					employeesTable.getColumnModel().getColumn(6).setPreferredWidth(110);
					employeesTable.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), Color.BLACK, Color.BLACK, Color.BLACK));
					employeesTable.setEnabled(false);
					seeEmployeesPanel.add(employeesTable);

				} catch (SQLException e) {
					e.printStackTrace();
				}

			} else if (arg0.getActionCommand().equals("Despedir trabajador")) {

			}
		}
	}
}
