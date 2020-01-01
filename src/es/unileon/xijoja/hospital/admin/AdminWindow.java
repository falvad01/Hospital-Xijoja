package es.unileon.xijoja.hospital.admin;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

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
import javax.swing.border.TitledBorder;

import es.unileon.xijoja.hospital.InfoWindow;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.login.LoginWindow;

//TODO panel despedir empleados
//TODO problema con los ID al eliminar trabajadores

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	private Logs log = new Logs();
	private ControlerAdmin listener;
	private String user;
	protected String password;

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	protected JPanel seeEmployeesPanel;
	protected JPanel addEmployeePanel;

	// private JScrollPane panelquebaja;

	protected JTextField textFieldName;
	protected JTextField textFieldSurname1;
	protected JTextField textFieldSurname2;
	protected JTextField textFieldDNI;
	protected JTextField textFieldBankAccount;
	protected JTextField textFieldEmail;

	protected JLabel lblUser;
	protected JLabel lblPassword;
	protected JLabel lblError;

	protected JComboBox<Object> comboBoxJob;

	protected JButton btnVerPlantilla;

	protected JLabel lblAdministrador;
	protected JLabel lblMedico;
	protected JLabel lblEnfermero;
	protected JLabel lblSecretario;
	protected JLabel lblTotal;

	protected JPanel editEmployeesPanel;

	protected JLabel lblErrorEdit;
	protected JTextField textFieldNameEdit;
	protected JTextField textFieldSurname1Edit;
	protected JTextField textFieldDNIEdit;
	protected JTextField textFieldBankEdit;

	protected JComboBox<Object> comboBoxJobEdit;

	protected JTextField textFieldEmailEdit;
	protected JLabel labelUserNameEdit;
	protected JButton btnSaveEdit;

	protected JPanel deletePanel;
	protected JTextField textFieldSurname2Edit;
	protected JTextField textFieldSearchDNIEdit;
	protected JTextField textFieldDNIToDelete;
	protected JTextField textFieldNameToDelete;
	protected JTextField textFieldFirstDeleteToDelete;
	protected JTextField textFieldSecondDeleteToDelete;
	protected JLabel lblErrorDelete;

	public AdminWindow(String user, String password) throws SQLException {
		log.InfoLog("Iniciada la sesion del administrador");
		this.user = user;
		this.password = password;

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Administrador");

		this.listener = new ControlerAdmin(this);// Creamos la instancia del controlador
		listener.setNumberEmployees();// OBtenemos los empleados al inicio del programa
		initComponents();
		initComponentsPanels();

	}

	private void initComponents() throws SQLException {

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

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
		btnNewButton.addActionListener(listener);

		btnVerPlantilla = new JButton("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 139, 234, 41);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(listener);

		JButton btnEditEmployee = new JButton("Editar trabajador");
		btnEditEmployee.setBackground(Color.WHITE);
		btnEditEmployee.setOpaque(false);
		btnEditEmployee.setBounds(28, 85, 234, 41);
		getContentPane().add(btnEditEmployee);
		btnEditEmployee.addActionListener(listener);

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

		lblAdministrador = new JLabel(String.valueOf(listener.numAdmin));
		lblAdministrador.setBorder(new TitledBorder("Administradores"));
		lblAdministrador.setBounds(29, 315, 108, 35);
		getContentPane().add(lblAdministrador);

		lblMedico = new JLabel(String.valueOf(listener.numDoc));
		lblMedico.setBorder(new TitledBorder("Medicos"));
		lblMedico.setBounds(29, 355, 108, 35);
		getContentPane().add(lblMedico);

		lblEnfermero = new JLabel(String.valueOf(listener.numNurse));
		lblEnfermero.setBorder(new TitledBorder("Enfermeros"));
		lblEnfermero.setBounds(154, 355, 108, 35);
		getContentPane().add(lblEnfermero);

		lblSecretario = new JLabel(String.valueOf(listener.numSecre));
		lblSecretario.setBorder(new TitledBorder("Secretarios"));
		lblSecretario.setBounds(152, 315, 108, 35);
		getContentPane().add(lblSecretario);

		lblTotal = new JLabel(
				String.valueOf(listener.numAdmin + listener.numDoc + listener.numNurse + listener.numSecre));
		lblTotal.setBorder(new TitledBorder("TOTAL"));
		lblTotal.setBounds(78, 401, 124, 35);
		getContentPane().add(lblTotal);

		System.out.println("NUMEROS " + listener.numAdmin + " " + listener.numDoc + " " + listener.numNurse + " "
				+ listener.numSecre);

		JButton btnBorrarEmpleado = new JButton("Borrar empleado");
		btnBorrarEmpleado.addActionListener(listener);
		btnBorrarEmpleado.setOpaque(false);
		btnBorrarEmpleado.setBackground(Color.WHITE);
		btnBorrarEmpleado.setBounds(28, 196, 234, 41);
		getContentPane().add(btnBorrarEmpleado);

		JButton button = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/--ndice.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoWindow info = new InfoWindow("medic");
				info.setVisible(true);
			}
		});
		button.setOpaque(false);
		button.setBorder(null);
		button.setBackground((Color) null);
		button.setBounds(10, 462, 23, 23);
		getContentPane().add(button);

	}

	private void initComponentsPanels() throws SQLException {

//-----------------------------------------------PANEL VER EMPLEADOS-----------------------------------------//
		seeEmployeesPanel = new JPanel();
		seeEmployeesPanel.setLayout(null);
		seeEmployeesPanel.setForeground(Color.WHITE);
		seeEmployeesPanel.setBackground(Color.WHITE);
		seeEmployeesPanel.setBounds(284, 11, 624, 450);
		seeEmployeesPanel.setPreferredSize(new Dimension(630, 700));
		seeEmployeesPanel.setVisible(false);
		getContentPane().add(seeEmployeesPanel);


		// ---------------------------------------------PANEL BORRAR
		// EMPLEADOS--------------------------------------//
		deletePanel = new JPanel();
		deletePanel.setBackground(Color.WHITE);
		deletePanel.setBounds(274, 0, 649, 467);
		getContentPane().add(deletePanel);
		deletePanel.setLayout(null);
		deletePanel.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(39, 69, 46, 20);
		deletePanel.add(lblNewLabel_1);

		textFieldDNIToDelete = new JTextField();
		textFieldDNIToDelete.setBounds(125, 69, 138, 20);
		deletePanel.add(textFieldDNIToDelete);
		textFieldDNIToDelete.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(39, 119, 46, 14);
		deletePanel.add(lblNewLabel_2);

		textFieldNameToDelete = new JTextField();
		textFieldNameToDelete.setColumns(10);
		textFieldNameToDelete.setBounds(125, 119, 138, 20);
		deletePanel.add(textFieldNameToDelete);

		textFieldFirstDeleteToDelete = new JTextField();
		textFieldFirstDeleteToDelete.setColumns(10);
		textFieldFirstDeleteToDelete.setBounds(125, 166, 138, 20);
		deletePanel.add(textFieldFirstDeleteToDelete);

		textFieldSecondDeleteToDelete = new JTextField();
		textFieldSecondDeleteToDelete.setColumns(10);
		textFieldSecondDeleteToDelete.setBounds(125, 215, 138, 20);
		deletePanel.add(textFieldSecondDeleteToDelete);

		JLabel lblApellido_3 = new JLabel("1º Apellido");
		lblApellido_3.setBounds(39, 169, 60, 14);
		deletePanel.add(lblApellido_3);

		JLabel lblapellido = new JLabel("2ºApellido");
		lblapellido.setBounds(39, 218, 60, 14);
		deletePanel.add(lblapellido);

		JLabel lblNewLabel_3 = new JLabel("Introducir los siguientes datos para borrar");
		lblNewLabel_3.setBounds(39, 23, 275, 20);
		deletePanel.add(lblNewLabel_3);

		JButton btnDelete = new JButton("Borrar");
		btnDelete.addActionListener(listener);
		btnDelete.setBounds(285, 244, 138, 20);
		deletePanel.add(btnDelete);

		 lblErrorDelete = new JLabel("");
		lblErrorDelete.setForeground(Color.RED);
		lblErrorDelete.setBounds(433, 247, 165, 14);
		deletePanel.add(lblErrorDelete);
		// --------------------------------------------PANEL EDITAR
		// EMPLEADOS-------------------------------------//
		editEmployeesPanel = new JPanel();
		editEmployeesPanel.setBounds(284, 11, 624, 450);
		getContentPane().add(editEmployeesPanel);
		editEmployeesPanel.setLayout(null);
		editEmployeesPanel.setForeground(Color.WHITE);
		editEmployeesPanel.setBackground(Color.WHITE);
		editEmployeesPanel.setVisible(false);

		JLabel label = new JLabel("Nombre");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label.setBounds(10, 74, 63, 23);
		editEmployeesPanel.add(label);

		JLabel lblApellido_1 = new JLabel("1º Apellido");
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

		JLabel label_2 = new JLabel("NIF/NIE");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_2.setBounds(10, 217, 63, 23);
		editEmployeesPanel.add(label_2);

		textFieldDNIEdit = new JTextField();
		textFieldDNIEdit.setEnabled(false);
		textFieldDNIEdit.setColumns(10);
		textFieldDNIEdit.setBounds(100, 220, 143, 20);
		editEmployeesPanel.add(textFieldDNIEdit);

		JLabel label_3 = new JLabel("Cuenta bancaria");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label_3.setBounds(10, 262, 120, 23);
		editEmployeesPanel.add(label_3);

		textFieldBankEdit = new JTextField();
		textFieldBankEdit.setEnabled(false);
		textFieldBankEdit.setColumns(10);
		textFieldBankEdit.setBounds(132, 265, 143, 20);
		editEmployeesPanel.add(textFieldBankEdit);

		JLabel label_4 = new JLabel("Puesto");
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

		JLabel label_5 = new JLabel("Email");
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
		btnSaveEdit.addActionListener(listener);
		btnSaveEdit.setOpaque(false);
		btnSaveEdit.setBackground(Color.WHITE);
		btnSaveEdit.setBounds(390, 252, 212, 47);
		editEmployeesPanel.add(btnSaveEdit);

		JLabel lblApellido_2 = new JLabel("2º Apellido");
		lblApellido_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblApellido_2.setBounds(10, 170, 80, 23);
		editEmployeesPanel.add(lblApellido_2);

		textFieldSurname2Edit = new JTextField();
		textFieldSurname2Edit.setEnabled(false);
		textFieldSurname2Edit.setColumns(10);
		textFieldSurname2Edit.setBounds(100, 173, 143, 20);
		editEmployeesPanel.add(textFieldSurname2Edit);

		JLabel label_9 = new JLabel("");
		label_9.setForeground(Color.RED);
		label_9.setBounds(390, 241, 212, 14);
		editEmployeesPanel.add(label_9);

		JLabel label_10 = new JLabel("New label");
		label_10.setBounds(43, 292, 252, 194);
		editEmployeesPanel.add(label_10);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 610, 33);
		editEmployeesPanel.add(separator);

		JLabel lblNewLabel = new JLabel("Introduce el DNI");
		lblNewLabel.setBounds(10, 11, 109, 23);
		editEmployeesPanel.add(lblNewLabel);

		textFieldSearchDNIEdit = new JTextField();
		textFieldSearchDNIEdit.setBounds(110, 12, 133, 20);
		editEmployeesPanel.add(textFieldSearchDNIEdit);
		textFieldSearchDNIEdit.setColumns(10);

		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(listener);

		btnNewButton_1.setBounds(253, 11, 89, 23);
		editEmployeesPanel.add(btnNewButton_1);

		lblErrorEdit = new JLabel("");
		lblErrorEdit.setForeground(Color.RED);
		lblErrorEdit.setBounds(358, 15, 143, 14);
		editEmployeesPanel.add(lblErrorEdit);

// ------------------------------------------PANEL AÑADIR EMPLEADOS-----------------------------------------//

		addEmployeePanel = new JPanel();
		addEmployeePanel.setForeground(Color.WHITE);
		addEmployeePanel.setBackground(Color.WHITE);
		addEmployeePanel.setBounds(284, 11, 624, 450);
		getContentPane().add(addEmployeePanel);
		addEmployeePanel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 27, 63, 23);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblNombre);

		JLabel lblApellidos = new JLabel("1º Apellido");
		lblApellidos.setBounds(10, 78, 80, 23);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblApellidos);

		textFieldName = new JTextField();
		textFieldName.setBounds(100, 30, 143, 20);
		addEmployeePanel.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname1 = new JTextField();
		textFieldSurname1.setBounds(100, 81, 143, 20);
		addEmployeePanel.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setBounds(10, 170, 63, 23);
		lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblNifnie);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(100, 173, 143, 20);
		textFieldDNI.setColumns(10);
		addEmployeePanel.add(textFieldDNI);

		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria");
		lblCuentaBancaria.setBounds(10, 215, 120, 23);
		lblCuentaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblCuentaBancaria);

		textFieldBankAccount = new JTextField();
		textFieldBankAccount.setBounds(132, 218, 143, 20);
		textFieldBankAccount.setColumns(10);
		addEmployeePanel.add(textFieldBankAccount);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(390, 27, 63, 23);
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblPuesto);

		comboBoxJob = new JComboBox<Object>();
		comboBoxJob.setBounds(450, 29, 133, 23);
		comboBoxJob.setBackground(Color.WHITE);
		comboBoxJob.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Administrador", "Medico", "Enfermero", "Secretario" }));
		comboBoxJob.setOpaque(false);
		addEmployeePanel.add(comboBoxJob);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(390, 78, 63, 23);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(450, 81, 133, 20);
		textFieldEmail.setColumns(10);
		addEmployeePanel.add(textFieldEmail);

		lblUser = new JLabel("");
		lblUser.setBorder(new TitledBorder("Usuario"));
		lblUser.setBounds(390, 123, 193, 41);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblUser);

		lblPassword = new JLabel("");
		lblPassword.setBorder(
				new TitledBorder(null, "Contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblPassword.setBounds(390, 178, 193, 41);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblPassword);

		JButton btnRegister = new JButton("Registrar");
		btnRegister.setBounds(390, 260, 212, 47);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		addEmployeePanel.add(btnRegister);
		btnRegister.addActionListener(listener);

		JLabel lblApellido = new JLabel("2º Apellido");
		lblApellido.setBounds(10, 123, 80, 23);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePanel.add(lblApellido);

		textFieldSurname2 = new JTextField();
		textFieldSurname2.setBounds(100, 126, 143, 20);
		textFieldSurname2.setColumns(10);
		addEmployeePanel.add(textFieldSurname2);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(390, 241, 212, 14);
		addEmployeePanel.add(lblError);

		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(43, 245, 252, 194);
		addEmployeePanel.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		
		JButton btnNewButton_2 = new JButton("Cerrar sesion");
		btnNewButton_2.setBounds(842, 473, 117, 23);
		getContentPane().add(btnNewButton_2);

	}
}
