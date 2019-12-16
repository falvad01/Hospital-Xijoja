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
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//TODO panel despedir empleados
//TODO problema con los ID al eliminar trabajadores

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	private Logs log = new Logs();
	
	private String user;
	private String password;

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	private JPanel seeEmployeesPanel;
	private JPanel addEmployeePane;
	// private JScrollPane panelquebaja;

	Toolkit screen;

	private JTextField textFieldName;
	private JTextField textFieldSurname1;
	private JTextField textFieldSurname2;
	private JTextField textFieldNIFNIE;
	private JTextField textFieldBankAccount;
	private JTextField textFieldEmail;

	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblError;

	private PersonalDAO dao;

	JComboBox<Object> comboBoxJob;

	private JButton btnVerPlantilla;

	private JLabel lblAdministrador;
	private JLabel lblMedico;
	private JLabel lblEnfermero;
	private JLabel lblSecretario;
	private JLabel lblTotal;

	private int numAdmin;
	private int numDoc;
	private int numNurse;
	private int numSecre;
	private JPanel editEmployeesPanel;
	private JLabel label;
	private JLabel lblApellido_1;
	private JTextField textFieldNameEdit;
	private JTextField textFieldSurname1Edit;
	private JLabel label_2;
	private JTextField textFieldDNIEdit;
	private JLabel label_3;
	private JTextField textFieldBankEdit;
	private JLabel label_4;
	private JComboBox<Object> comboBoxJobEdit;
	private JLabel label_5;
	private JTextField textFieldEmailEdit;
	private JLabel labelUserNameEdit;
	private JButton btnSaveEdit;
	private JLabel lblApellido_2;
	private JTextField textFieldSurname2Edit;
	private JLabel label_9;
	private JLabel label_10;
	private JTextField textFieldSearch;

	public AdminWindow(String user, String password) {
		log.InfoLog("Iniciada la sesion del administrador");
		this.user = user;
		this.password = password;

		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Administrador");

		try {
			dao = new PersonalDAO();
			initComponents();
			initComponentsPanels();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void initComponents() throws SQLException {

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		setNumberEmployees();
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
		btnNewButton.setBounds(28, 33, 234, 41);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(list);

		btnVerPlantilla = new JButton("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 139, 234, 41);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(list);

		JButton btnEditEmployee = new JButton("Editar trabajador");
		btnEditEmployee.setBackground(Color.WHITE);
		btnEditEmployee.setOpaque(false);
		btnEditEmployee.setBounds(28, 85, 234, 41);
		getContentPane().add(btnEditEmployee);
		btnEditEmployee.addActionListener(list);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(274, 11, 7, 474);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 21, 7, 41);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(20, 11, 117, 8);
		getContentPane().add(separator_2);

		lblAdministrador = new JLabel(String.valueOf(numAdmin));
		lblAdministrador.setBorder(new TitledBorder("Administradores"));
		lblAdministrador.setBounds(29, 315, 108, 35);
		getContentPane().add(lblAdministrador);

		lblMedico = new JLabel(String.valueOf(numDoc));
		lblMedico.setBorder(new TitledBorder("Medicos"));
		lblMedico.setBounds(29, 355, 108, 35);
		getContentPane().add(lblMedico);

		lblEnfermero = new JLabel(String.valueOf(numNurse));
		lblEnfermero.setBorder(new TitledBorder("Enfermeros"));
		lblEnfermero.setBounds(154, 355, 108, 35);
		getContentPane().add(lblEnfermero);

		lblSecretario = new JLabel(String.valueOf(numSecre));
		lblSecretario.setBorder(new TitledBorder("Secretarios"));
		lblSecretario.setBounds(152, 315, 108, 35);
		getContentPane().add(lblSecretario);

		lblTotal = new JLabel(String.valueOf(numAdmin + numDoc + numNurse + numSecre));
		lblTotal.setBorder(new TitledBorder("TOTAL"));
		lblTotal.setBounds(78, 401, 124, 35);
		getContentPane().add(lblTotal);

		System.out.println("NUMEROS " + numAdmin + " " + numDoc + " " + numNurse + " " + numSecre);

	}

	private void initComponentsPanels() throws SQLException {

		listener list = new listener();
//-----------------------------------------------PANEL VER EMPLEADOS-----------------------------------------//
		seeEmployeesPanel = new JPanel();
		seeEmployeesPanel.setLayout(null);
		seeEmployeesPanel.setForeground(Color.WHITE);
		seeEmployeesPanel.setBackground(Color.WHITE);
		seeEmployeesPanel.setBounds(284, 11, 624, 450);
		seeEmployeesPanel.setPreferredSize(new Dimension(630, 700));
		seeEmployeesPanel.setVisible(false);
// -----------------------------------------------PANEL EDITAR EMPLEADOS-------------------------------------//
		editEmployeesPanel = new JPanel();
		editEmployeesPanel.setBounds(278, 11, 630, 450);
		getContentPane().add(editEmployeesPanel);
		editEmployeesPanel.setLayout(null);
		editEmployeesPanel.setForeground(Color.WHITE);
		editEmployeesPanel.setBackground(Color.WHITE);
		editEmployeesPanel.setVisible(false);

		label = new JLabel("Nombre");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 74, 63, 23);
		editEmployeesPanel.add(label);

		lblApellido_1 = new JLabel("1º Apellido");
		lblApellido_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido_1.setBounds(10, 125, 80, 23);
		editEmployeesPanel.add(lblApellido_1);

		textFieldNameEdit = new JTextField();
		textFieldNameEdit.setEnabled(false);
		textFieldNameEdit.setColumns(10);
		textFieldNameEdit.setBounds(100, 77, 143, 20);
		editEmployeesPanel.add(textFieldNameEdit);

		textFieldSurname1Edit = new JTextField();
		textFieldSurname1Edit.setEnabled(false);
		textFieldSurname1Edit.setColumns(10);
		textFieldSurname1Edit.setBounds(100, 128, 143, 20);
		editEmployeesPanel.add(textFieldSurname1Edit);

		label_2 = new JLabel("NIF/NIE");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(10, 217, 63, 23);
		editEmployeesPanel.add(label_2);

		textFieldDNIEdit = new JTextField();
		textFieldDNIEdit.setEnabled(false);
		textFieldDNIEdit.setColumns(10);
		textFieldDNIEdit.setBounds(100, 220, 143, 20);
		editEmployeesPanel.add(textFieldDNIEdit);

		label_3 = new JLabel("Cuenta bancaria");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(10, 262, 120, 23);
		editEmployeesPanel.add(label_3);

		textFieldBankEdit = new JTextField();
		textFieldBankEdit.setEnabled(false);
		textFieldBankEdit.setColumns(10);
		textFieldBankEdit.setBounds(132, 265, 143, 20);
		editEmployeesPanel.add(textFieldBankEdit);

		label_4 = new JLabel("Puesto");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_4.setBounds(390, 74, 63, 23);
		editEmployeesPanel.add(label_4);

		comboBoxJobEdit = new JComboBox<Object>();
		comboBoxJobEdit.setEnabled(false);
		comboBoxJobEdit.setOpaque(false);
		comboBoxJobEdit.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Administrador", "Medico", "Enfermero", "Secretario" }));
		comboBoxJobEdit.setBackground(Color.WHITE);
		comboBoxJobEdit.setBounds(450, 76, 133, 23);
		editEmployeesPanel.add(comboBoxJobEdit);

		label_5 = new JLabel("Email");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_5.setBounds(390, 125, 63, 23);
		editEmployeesPanel.add(label_5);

		textFieldEmailEdit = new JTextField();
		textFieldEmailEdit.setEnabled(false);
		textFieldEmailEdit.setColumns(10);
		textFieldEmailEdit.setBounds(450, 128, 133, 20);
		editEmployeesPanel.add(textFieldEmailEdit);

		labelUserNameEdit = new JLabel("");
		labelUserNameEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelUserNameEdit.setBorder(new TitledBorder("Usuario"));
		labelUserNameEdit.setBounds(390, 173, 193, 41);
		editEmployeesPanel.add(labelUserNameEdit);

		btnSaveEdit = new JButton("Guardar");
		btnSaveEdit.addActionListener(list);
		btnSaveEdit.setOpaque(false);
		btnSaveEdit.setBackground(Color.WHITE);
		btnSaveEdit.setBounds(390, 252, 212, 47);
		editEmployeesPanel.add(btnSaveEdit);

		lblApellido_2 = new JLabel("2º Apellido");
		lblApellido_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido_2.setBounds(10, 170, 80, 23);
		editEmployeesPanel.add(lblApellido_2);

		textFieldSurname2Edit = new JTextField();
		textFieldSurname2Edit.setEnabled(false);
		textFieldSurname2Edit.setColumns(10);
		textFieldSurname2Edit.setBounds(100, 173, 143, 20);
		editEmployeesPanel.add(textFieldSurname2Edit);

		label_9 = new JLabel("");
		label_9.setForeground(Color.RED);
		label_9.setBounds(390, 241, 212, 14);
		editEmployeesPanel.add(label_9);

		label_10 = new JLabel("New label");
		label_10.setBounds(43, 292, 252, 194);
		editEmployeesPanel.add(label_10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 610, 33);
		editEmployeesPanel.add(separator);

		JLabel lblNewLabel = new JLabel("Introduce nombre y apellidos o DNI");
		lblNewLabel.setBounds(10, 11, 223, 23);
		editEmployeesPanel.add(lblNewLabel);

		textFieldSearch = new JTextField();
		textFieldSearch.setBounds(219, 12, 133, 20);
		editEmployeesPanel.add(textFieldSearch);
		textFieldSearch.setColumns(10);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(list);

		btnNewButton_1.setBounds(362, 11, 89, 23);
		editEmployeesPanel.add(btnNewButton_1);

// -----------------------------------------------PANEL AÑADIR EMPLEADOS-----------------------------------------//

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
		comboBoxJob.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Administrador", "Medico", "Enfermero", "Secretario" }));
		comboBoxJob.setOpaque(false);
		addEmployeePane.add(comboBoxJob);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(390, 78, 63, 23);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(450, 81, 133, 20);
		textFieldEmail.setColumns(10);
		addEmployeePane.add(textFieldEmail);

		lblUser = new JLabel("");
		lblUser.setBorder(new TitledBorder("Usuario"));
		lblUser.setBounds(390, 123, 193, 41);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblUser);

		lblPassword = new JLabel("");
		lblPassword.setBorder(
				new TitledBorder(null, "Contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblPassword.setBounds(390, 178, 193, 41);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblPassword);

		JButton btnRegister = new JButton("Registrar");
		btnRegister.setBounds(390, 260, 212, 47);
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

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(390, 241, 212, 14);
		addEmployeePane.add(lblError);

		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(43, 245, 252, 194);
		addEmployeePane.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		getContentPane().add(seeEmployeesPanel);

	}

	/**
	 *
	 * @throws SQLException
	 * 
	 *                      Obtenemos el numero de empleados en cada profesion
	 */
	private void setNumberEmployees() throws SQLException {

		numAdmin = 0;
		numDoc = 0;
		numNurse = 0;
		numSecre = 0;
		String[] jobs = dao.getJobsEmployees();
		System.out.println("JOBS " + jobs.length);
		for (int i = 0; i < jobs.length; i++) {
			System.out.print(jobs[i] + "->");
			if (jobs[i].equals("Medico")) {
				numDoc++;
				System.out.println(jobs[i]);
			} else if (jobs[i].equals("Administrador")) {
				numAdmin++;
				System.out.println(jobs[i]);
			} else if (jobs[i].equals("Enfermero")) {
				numNurse++;
				System.out.println(jobs[i]);
			} else if (jobs[i].equals("Secretario")) {
				numSecre++;
				System.out.println(jobs[i]);
			}

		}

	}

	/**
	 * 
	 * @param name
	 * @param surname1
	 * @param surname2
	 * @return
	 * 
	 * 		Generamos un usuario con el nombre y apellidos pasados por parametro
	 */
	private String genUser(String name, String surname1, String surname2) {

		StringBuilder sbName = new StringBuilder();// Formamos el nombre de usuario

		sbName.append(name.charAt(0));// Primera letra del nombre

		sbName.append(surname1.charAt(0));
		sbName.append(surname1.charAt(1));// Dos primeras letras del primer apellido
		sbName.append(surname2.charAt(0));
		sbName.append(surname2.charAt(1));// Dos primeras letras del segundo apellido

		String[] names = null;

		try {
			names = dao.getNamesEmployees();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		for (int i = 0; i < names.length; i++) {
			System.out.println(i + ": " + names[i]);
		}

		int numberOfUser = 0;
		for (int i = 1; i < names.length; i++) {// Vamos comprobando nombre por nombre

			char[] nameBUffer = names[i].toCharArray();
			char[] secondBuffer = new char[5];

			for (int j = 0; j < 5; j++) {
				System.out.println(j);
				secondBuffer[j] = nameBUffer[j];// Quitamos los numero del nombre de usuario
			}

			if (String.valueOf(secondBuffer).equals(sbName.toString().toLowerCase())) {

				numberOfUser++;// Contamos los ususarios con el mismo nombre y aniadimos un numero para que no
								// se repita

			}

		}
		sbName.append(numberOfUser);// Aniadimos el numero
		log.InfoLog("[ADMINWINDOW]:Usuario " + sbName.toString().toLowerCase() + " generado correctamente");
		return sbName.toString().toLowerCase();

	}

	/**
	 * 
	 * @return
	 * 
	 * 		Generamos una contraseña aleatoria
	 */
	private String genPassword() {

		String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder password = new StringBuilder();

		int i = 0;
		while (i < 7) {
			int rand = (int) ((Math.random() * ((61 - 0) + 1)));// Con el random generamos la contraseña sacando los
																// careacteres del array alphabet

			password.append(alphabet.charAt(rand));

			i++;
		}

		return password.toString();
	}

	/**
	 * 
	 * @param state
	 * 
	 *              Metodo que permite activar o desactivar los componentes de
	 *              editar empleado, de esta forma ahorramos tiempo de ponerlo cada
	 *              vez que lo necesitamos
	 */
	@SuppressWarnings("deprecation")
	private void enableAllEdit(boolean state) {

		textFieldNameEdit.enable(state);
		textFieldSurname1Edit.enable(state);
		textFieldSurname2Edit.enable(state);
		textFieldDNIEdit.enable(state);
		textFieldBankEdit.enable(state);
		textFieldEmailEdit.enable(state);
		comboBoxJobEdit.enable(state);
		btnSaveEdit.enable(state);

		if (!state) {// Si es falso borramos las string que habian anteriormente

			textFieldSearch.setText("");
			textFieldNameEdit.setText("");
			textFieldSurname1Edit.setText("");
			textFieldSurname2Edit.setText("");
			textFieldDNIEdit.setText("");
			textFieldBankEdit.setText("");
			textFieldEmailEdit.setText("");

		}

	}

	/**
	 *
	 * @author xijoja
	 *
	 */
	public class listener implements ActionListener {

		String[] employeeToEdit = null;

		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Registrar")) {////////////////////////////////// REGISTRAR

				log.InfoLog("Se ha pulsado el boton de registrar");
				
				
				boolean add = true;

				if ((textFieldName.getText().equals("")) || (textFieldSurname1.getText().equals(""))
						|| (textFieldSurname2.getText().equals("")) || (textFieldNIFNIE.getText().equals(""))
						|| (textFieldBankAccount.getText().equals("")) || (textFieldEmail.getText().equals(""))) {// Comprobamos
					// si algum
					// campo esta
					// vacio

					add = false;
					lblError.setText("Hay campos vacios");
					log.InfoLog("Hay campos vacios");
				} else {
					lblError.setText("");
				}

				if (add) {// Si da error no se añade el empleado
					
					lblUser.setText(
							genUser(textFieldName.getText(), textFieldSurname1.getText(), textFieldSurname1.getText()));
					lblPassword.setText(genPassword());

					int id = dao.getLastID();

					Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual

					try {

						dao.addEmployee(id, textFieldName.getText(), textFieldSurname1.getText(),
								textFieldSurname2.getText(), textFieldNIFNIE.getText(), date,
								textFieldBankAccount.getText(), comboBoxJob.getSelectedItem().toString(),
								lblPassword.getText(), lblUser.getText(), textFieldEmail.getText());// LLamamos a la
																									// funcion del DAO
																									// que inserta el
																									// empleado
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
					try {
						setNumberEmployees();
						lblAdministrador.setText(String.valueOf(numAdmin));
						lblMedico.setText(String.valueOf(numDoc));
						lblEnfermero.setText(String.valueOf(numNurse));
						lblSecretario.setText(String.valueOf(numSecre));
						lblTotal.setText(String.valueOf(numAdmin + numDoc + numNurse + numSecre));
					} catch (SQLException e) {

						e.printStackTrace();
					}
				}
				
				log.InfoLog("Usuario + " + lblUser.getText() + " añadido correctamente");

			} else if (arg0.getActionCommand().equals("Añadir trabajador")) {///////////////////////////////// ADD

				seeEmployeesPanel.setVisible(false);
				addEmployeePane.setVisible(true);
				editEmployeesPanel.setVisible(false);
				btnVerPlantilla.setText("Ver plantilla");
				enableAllEdit(false);

			} else if ((arg0.getActionCommand().equals("Ver plantilla"))
					|| (arg0.getActionCommand().equals("Recargar"))) {

				seeEmployeesPanel.setVisible(true);
				addEmployeePane.setVisible(false);
				editEmployeesPanel.setVisible(false);
				btnVerPlantilla.setText("Recargar");

				ArrayList<String[]> insert = null;

				String[] titles = null;

				String[][] matrixToInsert = null;

				try {
					titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "Fecha",
							"Cuenta Bancaria", "Puesto", "Contrase�a", "Usuario", "Email" }; // Titulos de la tabla de
																								// los empleados
					insert = dao.getAllEmployees();// ArrayList de Arrays
					System.out.println("Size " + insert.size());
					matrixToInsert = new String[insert.size() + 1][11];
					seeEmployeesPanel.setPreferredSize(new Dimension(624, 20 + 20 * insert.size()));
					seeEmployeesPanel.setBounds(284, 11, 624, 20 + 20 * insert.size());

					for (int i = 0; i < insert.size(); i++) { // rellenamos la matriz que meteremos en la tabla a partir
																// del ArrayList de arrays devuelto del DAO
						for (int j = 0; j < 11; j++) {
							if (i == 0) {

								matrixToInsert[i][j] = titles[j];

							} else {
								matrixToInsert[i][j] = insert.get(i)[j];
							}
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				JTable employeesTable = new JTable();
				employeesTable.setBounds(20, 20, 600, 20 + 20 * insert.size());

				employeesTable.setVisible(true);
				seeEmployeesPanel.add(employeesTable);
				/*
				 * //TODO lo que hizo xian es esto que esta comentado panelquebaja = new
				 * JScrollPane(seeEmployeesPanel);
				 *
				 * panelquebaja.setHorizontalScrollBarPolicy(JScrollPane.
				 * HORIZONTAL_SCROLLBAR_NEVER);
				 * panelquebaja.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
				 * ); panelquebaja.setBounds(seeEmployeesPanel.getBounds());
				 * System.out.println(panelquebaja.getBounds());
				 * getContentPane().add(panelquebaja);
				 *
				 * panelquebaja.setVisible(true);
				 */
				employeesTable.setAutoscrolls(true);

				System.out.println("VIVA DROTIUM");
				DefaultTableModel tableModel = new DefaultTableModel(matrixToInsert, titles);
				employeesTable.setModel(tableModel);

			} else if (arg0.getActionCommand().equals("Editar trabajador")) {

				seeEmployeesPanel.setVisible(false);
				addEmployeePane.setVisible(false);
				editEmployeesPanel.setVisible(true);
				btnVerPlantilla.setText("Ver plantilla");
				enableAllEdit(false);

			} else if (arg0.getActionCommand().equals("Buscar")) {

				if (Character.isDigit(textFieldSearch.getText().charAt(0))) {
					enableAllEdit(true);
					System.out.println("Busqueda por DNI");

					try {
						employeeToEdit = dao.getEmployee(textFieldSearch.getText().toString());

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					textFieldNameEdit.setText(employeeToEdit[1]);
					textFieldSurname1Edit.setText(employeeToEdit[2]);
					textFieldSurname2Edit.setText(employeeToEdit[3]);
					textFieldDNIEdit.setText(employeeToEdit[4]);
					textFieldBankEdit.setText(employeeToEdit[6]);
					comboBoxJobEdit.setSelectedItem(employeeToEdit[7]);
					textFieldEmailEdit.setText(employeeToEdit[10]);
					labelUserNameEdit.setText(employeeToEdit[9]);

				} else {

					System.out.println("Busqueda por nombre y apellidos");
				}

			} else if (arg0.getActionCommand().equals("Guardar")) {

				System.out.println("Comenzamos a editar el trabajador");
				boolean out = false;

				while (!out) {// Repetimos hasta que se cancela o se introduce la contraseña correcta
					JPasswordField pf = new JPasswordField();
					int option = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);
					System.out.println(pf.getText());

					if (option == JOptionPane.CANCEL_OPTION) {// si se pulsa cancelar
						out = true;
						System.out.println("Se ha cancelado");
					} else {

						if (pf.getText().equals(password)) {// Si se acierta la contraseña
							out = true;

							labelUserNameEdit.setText(genUser(textFieldNameEdit.getText(),
									textFieldSurname1Edit.getText(), textFieldSurname2Edit.getText()));// Generamos el
																										// nuevo usuario

							try {
								dao.editEmployee(Integer.parseInt(employeeToEdit[0]), textFieldNameEdit.getText(),
										textFieldSurname1Edit.getText(), textFieldSurname2Edit.getText(),
										textFieldDNIEdit.getText(), textFieldBankEdit.getText(),
										comboBoxJobEdit.getSelectedItem().toString(), labelUserNameEdit.getText(),
										textFieldEmailEdit.getText());// Llamamos a editar del DAO
							} catch (NumberFormatException | SQLException e) {

								e.printStackTrace();
							}

						} else {// Si se falla la contraseña
							JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					}

				}

			}
		}
	}
}