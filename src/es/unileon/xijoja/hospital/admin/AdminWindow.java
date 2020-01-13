package es.unileon.xijoja.hospital.admin;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.List;

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

//TODO problema con los ID al eliminar trabajadores

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	private Logs log = new Logs();
	private ControlerAdmin listener;
	private String user;
	protected String password;

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	protected JScrollPane seeEmployeesPanel;
	protected JPanel addEmployeePanel;
	protected JPanel addPatientsPanel;
	protected JScrollPane seePatientPane;
	protected JPanel deletePatientsPanel;
	protected JPanel seeWarehousePanel;
	protected JPanel editPatientPanel;

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
	protected JLabel lblErrorEditPatientRomm;

	protected JTextField NombreP;
	protected JTextField surname1AddPatients;
	protected JTextField surname2AddPatients;
	protected JTextField DNI;
	protected JTextField roomAddPatients;
	protected JTextField Medicamentos;
	protected JLabel lberror;
	protected JLabel lblError2;
	protected JComboBox jcbNurse;
	protected JComboBox jcbMedic;
	protected JComboBox jcbMedicine;
	protected JTextField textEnfermedad;
	protected JTextField textFieldDNIToDeletePatient;
	protected JTextField textFieldNameToDeleteEmployee;
	protected JTextField textFieldFirstSurnameToDeleteEmployee;
	protected JTextField textFieldSecondSurnameToDeleteEmployee;
	protected JTextField textFieldErrorDeletePatient;
	protected JLabel lblErrorEditPatient;
	protected JTextField textFieldDNISearchEditPatient;
	protected JTextField textFieldNameEditPatient;
	protected JTextField textFieldSurname1EditPatient;
	protected JTextField textFieldSurname2EditPatient;
	protected JTextField textFieldDNIEditPatient;
	protected JTextField textFieldIllnesEditPatient;
	protected JTextField textFieldRommEditPacient;

	protected JComboBox comboBoxMedicines;
	protected JLabel lblNumberMedicine;
	protected JTextField textFieldNewMedicine;
	protected JTextField textFieldNewMedicineAmount;

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

		getContentPane().setBackground(Color.LIGHT_GRAY);
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
		btnNewButton.setBounds(28, 33, 234, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(listener);

		btnVerPlantilla = new JButton("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 79, 234, 23);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(listener);

		JButton btnEditEmployee = new JButton("Editar trabajador");
		btnEditEmployee.setBackground(Color.WHITE);
		btnEditEmployee.setOpaque(false);
		btnEditEmployee.setBounds(28, 56, 234, 23);
		getContentPane().add(btnEditEmployee);
		btnEditEmployee.addActionListener(listener);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(274, 0, 7, 500);
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
		btnBorrarEmpleado.setBounds(28, 102, 234, 23);
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

		JButton btnSeeWarehause = new JButton("Ver Almacen");
		btnSeeWarehause.setBackground(Color.WHITE);
		btnSeeWarehause.addActionListener(listener);
		btnSeeWarehause.setBounds(28, 220, 234, 23);
		getContentPane().add(btnSeeWarehause);
		JButton btnCloseSesion = new JButton("Cerrar sesion");
		btnCloseSesion.setBounds(145, 462, 117, 23);
		getContentPane().add(btnCloseSesion);

		JButton btnEnterPacient = new JButton("Ingresar paciente");
		btnEnterPacient.setBackground(Color.WHITE);
		btnEnterPacient.setBounds(28, 125, 234, 23);
		getContentPane().add(btnEnterPacient);
		btnEnterPacient.addActionListener(listener);

		JButton btnSeePatients = new JButton("Ver pacientes");
		btnSeePatients.setBackground(Color.WHITE);
		btnSeePatients.addActionListener(listener);
		btnSeePatients.setBounds(28, 148, 234, 23);
		getContentPane().add(btnSeePatients);

		JButton btnDeletePatient = new JButton("Borrar paciente");
		btnDeletePatient.setBackground(Color.WHITE);
		btnDeletePatient.addActionListener(listener);
		btnDeletePatient.setBounds(28, 172, 234, 23);
		getContentPane().add(btnDeletePatient);

		JButton btnEditPatient = new JButton("Editar Paciente");
		btnEditPatient.addActionListener(listener);
		btnEditPatient.setBackground(Color.WHITE);
		btnEditPatient.setBounds(28, 196, 234, 23);
		getContentPane().add(btnEditPatient);
		btnCloseSesion.addActionListener(listener);

	}

	private void initComponentsPanels() throws SQLException {

//-----------------------------------------------PANEL VER EMPLEADOS-----------------------------------------//

		seeEmployeesPanel = new JScrollPane();
		seeEmployeesPanel.setBounds(284, 11, 625, 420);
		seeEmployeesPanel.setForeground(Color.WHITE);
		seeEmployeesPanel.setBackground(Color.WHITE);
		seeEmployeesPanel.setPreferredSize(new Dimension(630, 420));
		seeEmployeesPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		seeEmployeesPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		seeEmployeesPanel.setVisible(false);

		// ------------------------------PANEL BORRAR
		// EMPLEADOS-------------------------//
		deletePatientsPanel = new JPanel();
		deletePatientsPanel.setVisible(false);

		// --------------------------------------PANEL VER
		// ALMACEN---------------------------------------//
		seeWarehousePanel = new JPanel();
		seeWarehousePanel.setVisible(false);
		seeWarehousePanel.setBackground(Color.WHITE);
		getContentPane().add(seeWarehousePanel);
		seeWarehousePanel.setBounds(274, 0, 695, 496);
		seeWarehousePanel.setLayout(null);

		JButton btnPlus1 = new JButton(">");
		btnPlus1.setBackground(Color.WHITE);
		btnPlus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num = Integer.parseInt(lblNumberMedicine.getText());
				num++;
				lblNumberMedicine.setText(String.valueOf(num));
			}
		});
		btnPlus1.setBounds(378, 26, 41, 14);
		seeWarehousePanel.add(btnPlus1);

		JButton btnMinus1 = new JButton("<");
		btnMinus1.setBackground(Color.WHITE);
		btnMinus1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(lblNumberMedicine.getText());
				if (Integer.parseInt(lblNumberMedicine.getText()) > 0) {
					num--;
					lblNumberMedicine.setText(String.valueOf(num));
				}

			}
		});
		btnMinus1.setBounds(338, 26, 41, 14);
		seeWarehousePanel.add(btnMinus1);

		JButton btnNewButton_4 = new JButton("Añadir o retirar");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addActionListener(listener);
		btnNewButton_4.setBounds(225, 83, 133, 34);
		seeWarehousePanel.add(btnNewButton_4);

		JButton btnPlus5 = new JButton(">>");
		btnPlus5.setBackground(Color.WHITE);
		btnPlus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int num = Integer.parseInt(lblNumberMedicine.getText());
				num = num + 10;
				lblNumberMedicine.setText(String.valueOf(num));

			}
		});
		btnPlus5.setBounds(418, 26, 49, 14);
		seeWarehousePanel.add(btnPlus5);

		JButton btnMinus5 = new JButton("<<");
		btnMinus5.setBackground(Color.WHITE);
		btnMinus5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.parseInt(lblNumberMedicine.getText());
				if (Integer.parseInt(lblNumberMedicine.getText()) > 9) {
					num = num - 10;
					lblNumberMedicine.setText(String.valueOf(num));
				}
			}
		});
		btnMinus5.setBounds(292, 26, 49, 14);
		seeWarehousePanel.add(btnMinus5);

		comboBoxMedicines = new JComboBox();
		comboBoxMedicines.setBounds(21, 23, 182, 20);
		comboBoxMedicines.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBoxMedicines.getSelectedItem() != null) {
					listener.changeNumMedicines(comboBoxMedicines.getSelectedItem().toString());// Cmabiamos la cantidad
																								// mostrada
				}

			}
		});
		seeWarehousePanel.add(comboBoxMedicines);

		lblNumberMedicine = new JLabel("New label");
		lblNumberMedicine.setBounds(213, 26, 69, 14);
		seeWarehousePanel.add(lblNumberMedicine);
		
		JLabel lblNewLabel_4 = new JLabel("INTRODUCIR UN NUEVO MEDICAMENTO");
		lblNewLabel_4.setBounds(21, 180, 207, 14);
		seeWarehousePanel.add(lblNewLabel_4);
		
		textFieldNewMedicine = new JTextField();
		textFieldNewMedicine.setBounds(82, 222, 86, 20);
		seeWarehousePanel.add(textFieldNewMedicine);
		textFieldNewMedicine.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Nombre");
		lblNewLabel_7.setBounds(21, 225, 46, 14);
		seeWarehousePanel.add(lblNewLabel_7);
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(21, 255, 46, 14);
		seeWarehousePanel.add(lblCantidad);
		
		textFieldNewMedicineAmount = new JTextField();
		textFieldNewMedicineAmount.setColumns(10);
		textFieldNewMedicineAmount.setBounds(82, 253, 86, 20);
		seeWarehousePanel.add(textFieldNewMedicineAmount);
		
		JButton btnNewButton_2 = new JButton("Comprar nuevo");
		btnNewButton_2.addActionListener(listener);
		btnNewButton_2.setBounds(225, 289, 154, 23);
		seeWarehousePanel.add(btnNewButton_2);

		// ---------------------------------------------PANEL BORRAR
		// EMPLEADOS--------------------------------------//
		deletePanel = new JPanel();
		deletePanel.setBackground(Color.WHITE);
		deletePanel.setBounds(274, 0, 695, 496);
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
		editEmployeesPanel.setBounds(274, 0, 695, 496);
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
		lblApellido_1.setBounds(32, 127, 101, 16);
		deletePatientsPanel.setBackground(Color.WHITE);
		deletePatientsPanel.setBounds(274, 0, 695, 496);
		getContentPane().add(deletePatientsPanel);
		deletePatientsPanel.setLayout(null);

		JLabel lblNewLabel_123 = new JLabel("DNI");
		lblNewLabel_123.setBounds(39, 69, 46, 20);
		deletePatientsPanel.add(lblNewLabel_123);

		JLabel lblNewLabel_22 = new JLabel("Nombre");
		lblNewLabel_22.setBounds(39, 119, 46, 14);
		deletePatientsPanel.add(lblNewLabel_22);

		JLabel lblApellido_32 = new JLabel("1º Apellido");
		lblApellido_32.setBounds(39, 169, 60, 14);
		deletePatientsPanel.add(lblApellido_32);

		JLabel lblapellido2 = new JLabel("2ºApellido");
		lblapellido2.setBounds(39, 218, 60, 14);
		deletePatientsPanel.add(lblapellido2);

		JLabel lblNewLabel_32 = new JLabel("Introducir los siguientes datos para borrar");
		lblNewLabel_32.setBounds(39, 23, 275, 20);
		deletePatientsPanel.add(lblNewLabel_32);

		JButton btnDelete2 = new JButton("Eliminar");
		btnDelete2.addActionListener(listener);
		btnDelete2.setBounds(285, 244, 138, 20);
		btnDelete2.setOpaque(false);
		btnDelete2.setBackground(new Color(255, 255, 255));
		deletePatientsPanel.add(btnDelete2);

		textFieldDNIToDeletePatient = new JTextField();
		textFieldDNIToDeletePatient.setBounds(111, 69, 100, 20);
		deletePatientsPanel.add(textFieldDNIToDeletePatient);
		textFieldDNIToDeletePatient.setColumns(10);

		textFieldNameToDeleteEmployee = new JTextField();
		textFieldNameToDeleteEmployee.setBounds(111, 116, 100, 20);
		deletePatientsPanel.add(textFieldNameToDeleteEmployee);
		textFieldNameToDeleteEmployee.setColumns(10);

		textFieldFirstSurnameToDeleteEmployee = new JTextField();
		textFieldFirstSurnameToDeleteEmployee.setBounds(109, 166, 100, 20);
		deletePatientsPanel.add(textFieldFirstSurnameToDeleteEmployee);
		textFieldFirstSurnameToDeleteEmployee.setColumns(10);

		textFieldSecondSurnameToDeleteEmployee = new JTextField();
		textFieldSecondSurnameToDeleteEmployee.setColumns(10);
		textFieldSecondSurnameToDeleteEmployee.setBounds(109, 215, 100, 20);
		deletePatientsPanel.add(textFieldSecondSurnameToDeleteEmployee);

		textFieldErrorDeletePatient = new JTextField();
		textFieldErrorDeletePatient.setCaretColor(Color.RED);
		textFieldErrorDeletePatient.setBounds(436, 244, 154, 20);
		deletePatientsPanel.add(textFieldErrorDeletePatient);
		textFieldErrorDeletePatient.setColumns(10);
		getContentPane().add(seeEmployeesPanel);

// --------------------------------------PANEL EDITAR PACIENTE-------------------------------//
		editPatientPanel = new JPanel();
		editPatientPanel.setBackground(Color.WHITE);
		editPatientPanel.setVisible(false);
		editPatientPanel.setBounds(274, 0, 695, 496);
		getContentPane().add(editPatientPanel);
		editPatientPanel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Introduce DNI");
		lblNewLabel_5.setBounds(10, 11, 121, 14);
		editPatientPanel.add(lblNewLabel_5);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 34, 610, 14);
		editPatientPanel.add(separator_2);

		textFieldDNISearchEditPatient = new JTextField();
		textFieldDNISearchEditPatient.setBounds(99, 8, 128, 20);
		editPatientPanel.add(textFieldDNISearchEditPatient);
		textFieldDNISearchEditPatient.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Nombre");
		lblNewLabel_6.setBounds(10, 67, 46, 14);
		editPatientPanel.add(lblNewLabel_6);

		JLabel lblApellido_4 = new JLabel("1º Apellido");
		lblApellido_4.setBounds(10, 103, 74, 14);
		editPatientPanel.add(lblApellido_4);

		JLabel lblApellido_5 = new JLabel("2º Apellido");
		lblApellido_5.setBounds(10, 139, 74, 14);
		editPatientPanel.add(lblApellido_5);

		JLabel lblNifnie_1 = new JLabel("NIF/NIE");
		lblNifnie_1.setBounds(10, 176, 46, 14);
		editPatientPanel.add(lblNifnie_1);

		JLabel lblEnfermedad_1 = new JLabel("Enfermedad");
		lblEnfermedad_1.setBounds(10, 211, 74, 14);
		editPatientPanel.add(lblEnfermedad_1);

		JLabel lblHabitacion = new JLabel("Habitacion");
		lblHabitacion.setBounds(10, 247, 74, 14);
		editPatientPanel.add(lblHabitacion);

		textFieldNameEditPatient = new JTextField();
		textFieldNameEditPatient.setBounds(79, 64, 86, 20);
		editPatientPanel.add(textFieldNameEditPatient);
		textFieldNameEditPatient.setColumns(10);

		textFieldSurname1EditPatient = new JTextField();
		textFieldSurname1EditPatient.setColumns(10);
		textFieldSurname1EditPatient.setBounds(79, 100, 86, 20);
		editPatientPanel.add(textFieldSurname1EditPatient);

		textFieldSurname2EditPatient = new JTextField();
		textFieldSurname2EditPatient.setColumns(10);
		textFieldSurname2EditPatient.setBounds(79, 136, 86, 20);
		editPatientPanel.add(textFieldSurname2EditPatient);

		textFieldDNIEditPatient = new JTextField();
		textFieldDNIEditPatient.setColumns(10);
		textFieldDNIEditPatient.setBounds(79, 173, 86, 20);
		editPatientPanel.add(textFieldDNIEditPatient);

		textFieldIllnesEditPatient = new JTextField();
		textFieldIllnesEditPatient.setColumns(10);
		textFieldIllnesEditPatient.setBounds(79, 208, 86, 20);
		editPatientPanel.add(textFieldIllnesEditPatient);

		textFieldRommEditPacient = new JTextField();
		textFieldRommEditPacient.setColumns(10);
		textFieldRommEditPacient.setBounds(79, 244, 86, 20);
		editPatientPanel.add(textFieldRommEditPacient);

		JButton btnNewButton_6 = new JButton("Buscar paciente");
		btnNewButton_6.addActionListener(listener);
		btnNewButton_6.setBounds(247, 7, 128, 23);
		editPatientPanel.add(btnNewButton_6);

		JButton btnNewButton_7 = new JButton("Guardar paciente");
		btnNewButton_7.addActionListener(listener);
		btnNewButton_7.setBounds(306, 272, 153, 23);
		editPatientPanel.add(btnNewButton_7);

		lblErrorEditPatient = new JLabel("");
		lblErrorEditPatient.setForeground(Color.RED);
		lblErrorEditPatient.setBackground(Color.RED);
		lblErrorEditPatient.setBounds(385, 11, 200, 14);
		editPatientPanel.add(lblErrorEditPatient);

		lblErrorEditPatientRomm = new JLabel("");
		lblErrorEditPatientRomm.setForeground(Color.RED);
		lblErrorEditPatientRomm.setBackground(Color.RED);
		lblErrorEditPatientRomm.setBounds(10, 281, 286, 14);
		editPatientPanel.add(lblErrorEditPatientRomm);

		// ----------------------------------PANEL AÑADIR
		// PACIENTES--------------------------//
		addPatientsPanel = new JPanel();
		addPatientsPanel.setVisible(false);
		addPatientsPanel.setBounds(274, 0, 695, 496);
		getContentPane().add(addPatientsPanel);
		addPatientsPanel.setLayout(null);
		addPatientsPanel.setForeground(Color.WHITE);
		addPatientsPanel.setBackground(Color.WHITE);

		JLabel iconLabel2 = new JLabel("New label");
		iconLabel2.setBounds(355, 127, 250, 222);
		addPatientsPanel.add(iconLabel2);
		iconLabel2.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		getContentPane().add(addPatientsPanel);

		JButton btnModificarEstadoPaciente = new JButton("Ingresar");
		btnModificarEstadoPaciente.setOpaque(false);
		btnModificarEstadoPaciente.setBackground(new Color(255, 255, 255));
		btnModificarEstadoPaciente.setBounds(407, 381, 198, 41);
		addPatientsPanel.add(btnModificarEstadoPaciente);
		btnModificarEstadoPaciente.addActionListener(listener);

		JLabel label_1 = new JLabel("Nombre Paciente");
		label_1.setBounds(32, 47, 101, 16);
		addPatientsPanel.add(label_1);

		NombreP = new JTextField();
		NombreP.setColumns(10);
		NombreP.setBounds(145, 44, 116, 22);
		addPatientsPanel.add(NombreP);

		JLabel lblApellido2 = new JLabel("Apellido 1");
		lblApellido2.setBounds(32, 88, 101, 16);
		addPatientsPanel.add(lblApellido2);

		JLabel lblApellido_12 = new JLabel("Apellido 2");
		addPatientsPanel.add(lblApellido_12);

		surname1AddPatients = new JTextField();
		surname1AddPatients.setColumns(10);
		surname1AddPatients.setBounds(145, 85, 116, 22);
		addPatientsPanel.add(surname1AddPatients);

		surname2AddPatients = new JTextField();
		surname2AddPatients.setColumns(10);
		surname2AddPatients.setBounds(145, 124, 116, 22);
		addPatientsPanel.add(surname2AddPatients);

		JLabel lblNifnie2 = new JLabel("NIFNIE");
		lblNifnie2.setBounds(32, 168, 56, 16);
		addPatientsPanel.add(lblNifnie2);

		DNI = new JTextField();
		DNI.setBounds(145, 165, 116, 22);
		addPatientsPanel.add(DNI);
		DNI.setColumns(10);

		JLabel lblHabitacin = new JLabel("Habitación");
		lblHabitacin.setBounds(32, 209, 89, 16);
		addPatientsPanel.add(lblHabitacin);

		roomAddPatients = new JTextField();
		roomAddPatients.setBounds(145, 206, 116, 22);
		addPatientsPanel.add(roomAddPatients);
		roomAddPatients.setColumns(10);
		lberror = new JLabel("");
		lberror.setForeground(Color.RED);
		lberror.setBounds(50, 241, 260, 150);
		addPatientsPanel.add(lberror);

		jcbNurse = new JComboBox();
		listener.filJComboBox(jcbNurse, false);
		jcbNurse.setBounds(392, 86, 213, 20);
		addPatientsPanel.add(jcbNurse);

		jcbMedic = new JComboBox();
		listener.filJComboBox(jcbMedic, true);
		jcbMedic.setBounds(392, 45, 213, 20);
		addPatientsPanel.add(jcbMedic);

		JLabel lblMedico_1 = new JLabel("Medico");
		lblMedico_1.setBounds(307, 47, 56, 16);
		addPatientsPanel.add(lblMedico_1);

		JLabel lblEnfermera = new JLabel("Enfermera");
		lblEnfermera.setBounds(307, 88, 73, 16);
		addPatientsPanel.add(lblEnfermera);

		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setBounds(32, 254, 89, 16);
		addPatientsPanel.add(lblEnfermedad);

		textEnfermedad = new JTextField();
		textEnfermedad.setColumns(10);
		textEnfermedad.setBounds(145, 251, 116, 22);
		addPatientsPanel.add(textEnfermedad);
		getContentPane().add(seeEmployeesPanel);

// ------------------------------------------PANEL AÑADIR EMPLEADOS-----------------------------------------//

		addEmployeePanel = new JPanel();
		addEmployeePanel.setForeground(Color.WHITE);
		addEmployeePanel.setBackground(Color.WHITE);
		addEmployeePanel.setBounds(274, 0, 695, 496);
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

// ---------------------------------------PANEL VER PACIENTES-----------------------------------//
		seePatientPane = new JScrollPane();
		seePatientPane.setBounds(284, 11, 625, 420);
		seePatientPane.setForeground(Color.WHITE);
		seePatientPane.setBackground(Color.WHITE);
		seePatientPane.setPreferredSize(new Dimension(630, 420));
		seePatientPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		seePatientPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		seePatientPane.setVisible(false);
		getContentPane().add(seePatientPane);
	}
}
