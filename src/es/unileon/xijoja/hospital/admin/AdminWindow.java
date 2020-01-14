package es.unileon.xijoja.hospital.admin;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.List;
import java.awt.Shape;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.print.attribute.standard.JobHoldUntil;
import javax.swing.BorderFactory;
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
	protected JTextField textFieldSecondSurnameToDelete;
	protected JLabel lblErrorDelete;

	protected JLabel lblErrorEditPatientRomm;
	protected JLabel lberror;
	protected JTextField NombreP;
	protected JTextField surname1AddPatients;
	protected JTextField surname2AddPatients;
	protected JTextField DNI;
	protected JTextField roomAddPatients;
	protected JTextField medicamentos;
	protected JLabel lblError2;
	protected JComboBox<Object> jcbNurse;
	protected JComboBox<Object> jcbMedic;
	protected JComboBox<Object> jcbMedicine;
	protected JTextField textEnfermedad;
	protected JTextField textFieldDNIToDeletePatient;
	protected JTextField textFieldNameToDeletePatient;
	protected JTextField textFieldFirstSurnameToDeletePatient;
	protected JTextField textFieldSecondSurnameToDeletePatient;
	protected JLabel textFieldErrorDeletePatient;
	protected JLabel lblErrorEditPatient;
	protected JTextField textFieldDNISearchEditPatient;
	protected JTextField textFieldNameEditPatient;
	protected JTextField textFieldSurname1EditPatient;
	protected JTextField textFieldSurname2EditPatient;
	protected JTextField textFieldDNIEditPatient;
	protected JTextField textFieldIllnesEditPatient;
	protected JTextField textFieldRommEditPacient;

	protected JComboBox<Object> comboBoxMedicines;
	protected JLabel lblNumberMedicine;
	protected JTextField textFieldNewMedicine;
	protected JTextField textFieldNewMedicineAmount;

	public AdminWindow(String user, String password) throws SQLException {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("etc/rexlia.ttf")));
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}
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

		JButton btnNewButton = new RoundedJButton(15);
		btnNewButton.setText("Añadir trabajador");
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(28, 33, 234, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(listener);

		btnVerPlantilla = new RoundedJButton(15);
		btnVerPlantilla.setText("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 79, 234, 23);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(listener);

		JButton btnEditEmployee = new RoundedJButton(15);
		btnEditEmployee.setText("Editar trabajador");
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

		JButton btnBorrarEmpleado = new RoundedJButton(15);
		btnBorrarEmpleado.setText("Borrar empleado");
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

		JButton btnSeeWarehause = new RoundedJButton(15);
		btnSeeWarehause.setText("Ver Almacen");
		btnSeeWarehause.setBackground(Color.WHITE);
		btnSeeWarehause.addActionListener(listener);
		btnSeeWarehause.setBounds(28, 220, 234, 23);
		getContentPane().add(btnSeeWarehause);

		JButton btnCloseSesion = new RoundedJButton(15);
		btnCloseSesion.setText("Cerrar sesion");
		btnCloseSesion.setBounds(60, 465, 180, 23);
		getContentPane().add(btnCloseSesion);
		btnCloseSesion.addActionListener(listener);

		JButton btnEnterPacient = new RoundedJButton(15);
		btnEnterPacient.setText("Ingresar paciente");
		btnEnterPacient.setBackground(Color.WHITE);
		btnEnterPacient.setBounds(28, 125, 234, 23);
		getContentPane().add(btnEnterPacient);
		btnEnterPacient.addActionListener(listener);

		JButton btnSeePatients = new RoundedJButton(15);
		btnSeePatients.setText("Ver pacientes");
		btnSeePatients.setBackground(Color.WHITE);
		btnSeePatients.addActionListener(listener);
		btnSeePatients.setBounds(28, 148, 234, 23);
		getContentPane().add(btnSeePatients);

		JButton btnDeletePatient = new RoundedJButton(15);
		btnDeletePatient.setText("Borrar paciente");
		btnDeletePatient.setBackground(Color.WHITE);
		btnDeletePatient.addActionListener(listener);
		btnDeletePatient.setBounds(28, 172, 234, 23);
		getContentPane().add(btnDeletePatient);

		JButton btnEditPatient = new RoundedJButton(15);
		btnEditPatient.setText("Editar Paciente");
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

		// ----------------------------------PANEL AÑADIR
		// PACIENTES--------------------------//
		addPatientsPanel = new JPanel();
		addPatientsPanel.setVisible(false);

		// ------------------------------PANEL BORRAR
		// EMPLEADOS-------------------------//
		deletePatientsPanel = new JPanel();
		deletePatientsPanel.setVisible(false);

		// --------------------------------------PANEL EDITAR
		// PACIENTE-------------------------------//
		editPatientPanel = new JPanel();
		editPatientPanel.setBackground(Color.WHITE);
		editPatientPanel.setVisible(false);

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
		btnMinus1.setBounds(338, 26, 50, 14);
		seeWarehousePanel.add(btnMinus1);
		

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
		lblNewLabel_4.setBounds(21, 180, 582, 14);
		lblNewLabel_4.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		seeWarehousePanel.add(lblNewLabel_4);
		

		JLabel lblNewLabel_7 = new JLabel("Nombre");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_7.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_7.setBounds(21, 228, 69, 14);
		seeWarehousePanel.add(lblNewLabel_7);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblCantidad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCantidad.setBounds(10, 259, 80, 14);
		seeWarehousePanel.add(lblCantidad);
		
	
		editPatientPanel.setBounds(274, 0, 695, 496);
		getContentPane().add(editPatientPanel);
		editPatientPanel.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("Introduce DNI");
		lblNewLabel_5.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 10));
		lblNewLabel_5.setBounds(10, 11, 121, 14);
		editPatientPanel.add(lblNewLabel_5);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 34, 610, 14);
		editPatientPanel.add(separator_2);

		textFieldDNISearchEditPatient = new HintTextFieldNoText();
		textFieldDNISearchEditPatient.setBounds(99, 8, 128, 20);
		editPatientPanel.add(textFieldDNISearchEditPatient);
		textFieldDNISearchEditPatient.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Nombre");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_6.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_6.setBounds(57, 64, 74, 14);
		editPatientPanel.add(lblNewLabel_6);

		JLabel lblApellido_4 = new JLabel("1º Apellido");
		lblApellido_4.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_4.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_4.setBounds(28, 100, 103, 14);
		editPatientPanel.add(lblApellido_4);

		JLabel lblApellido_5 = new JLabel("2º Apellido");
		lblApellido_5.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_5.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_5.setBounds(28, 136, 103, 14);
		editPatientPanel.add(lblApellido_5);

		JLabel lblNifnie_1 = new JLabel("NIF/NIE");
		lblNifnie_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNifnie_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNifnie_1.setBounds(57, 173, 74, 14);
		editPatientPanel.add(lblNifnie_1);

		JLabel lblEnfermedad_1 = new JLabel("Enfermedad");
		lblEnfermedad_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblEnfermedad_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnfermedad_1.setBounds(10, 208, 121, 14);
		editPatientPanel.add(lblEnfermedad_1);

		JLabel lblHabitacion = new JLabel("Habitacion");
		lblHabitacion.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblHabitacion.setHorizontalAlignment(SwingConstants.TRAILING);
		lblHabitacion.setBounds(10, 244, 121, 14);
		editPatientPanel.add(lblHabitacion);

		textFieldNameEditPatient = new HintTextFieldNoText();
		textFieldNameEditPatient.setBounds(141, 61, 86, 20);
		editPatientPanel.add(textFieldNameEditPatient);
		textFieldNameEditPatient.setColumns(10);

		textFieldSurname1EditPatient = new HintTextFieldNoText();
		textFieldSurname1EditPatient.setColumns(10);
		textFieldSurname1EditPatient.setBounds(141, 97, 86, 20);
		editPatientPanel.add(textFieldSurname1EditPatient);

		textFieldSurname2EditPatient = new HintTextFieldNoText();
		textFieldSurname2EditPatient.setColumns(10);
		textFieldSurname2EditPatient.setBounds(141, 133, 86, 20);
		editPatientPanel.add(textFieldSurname2EditPatient);

		textFieldDNIEditPatient = new HintTextFieldNoText();
		textFieldDNIEditPatient.setColumns(10);
		textFieldDNIEditPatient.setBounds(141, 170, 86, 20);
		editPatientPanel.add(textFieldDNIEditPatient);

		textFieldIllnesEditPatient = new HintTextFieldNoText();
		textFieldIllnesEditPatient.setColumns(10);
		textFieldIllnesEditPatient.setBounds(141, 205, 86, 20);
		editPatientPanel.add(textFieldIllnesEditPatient);

		textFieldRommEditPacient = new HintTextFieldNoText();
		textFieldRommEditPacient.setColumns(10);
		textFieldRommEditPacient.setBounds(141, 241, 95, 20);
		editPatientPanel.add(textFieldRommEditPacient);

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

		deletePatientsPanel.setBackground(Color.WHITE);
		deletePatientsPanel.setBounds(274, 0, 695, 496);
		getContentPane().add(deletePatientsPanel);
		deletePatientsPanel.setLayout(null);

		JLabel lblNewLabel_123 = new JLabel("DNI");
		lblNewLabel_123.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_123.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_123.setBounds(10, 69, 89, 20);
		deletePatientsPanel.add(lblNewLabel_123);

		JLabel lblNewLabel_22 = new JLabel("Nombre");
		lblNewLabel_22.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_22.setBounds(10, 119, 89, 14);
		deletePatientsPanel.add(lblNewLabel_22);

		JLabel lblApellido_32 = new JLabel("1º Apellido");
		lblApellido_32.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_32.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_32.setBounds(10, 169, 89, 14);
		deletePatientsPanel.add(lblApellido_32);

		JLabel lblapellido2 = new JLabel("2ºApellido");
		lblapellido2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblapellido2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblapellido2.setBounds(0, 218, 99, 14);
		deletePatientsPanel.add(lblapellido2);

		JLabel lblNewLabel_32 = new JLabel("Introducir los siguientes datos para borrar");
		lblNewLabel_32.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_32.setBounds(39, 23, 371, 20);
		deletePatientsPanel.add(lblNewLabel_32);

		textFieldNameToDeletePatient = new HintTextField("Nombre");
		textFieldNameToDeletePatient.setBounds(111, 116, 100, 20);
		deletePatientsPanel.add(textFieldNameToDeletePatient);
		textFieldNameToDeletePatient.setColumns(10);

		textFieldFirstSurnameToDeletePatient = new HintTextField("1º Apellido");
		textFieldFirstSurnameToDeletePatient.setBounds(109, 166, 100, 20);
		deletePatientsPanel.add(textFieldFirstSurnameToDeletePatient);
		textFieldFirstSurnameToDeletePatient.setColumns(10);

		textFieldSecondSurnameToDeletePatient = new HintTextField("2º Apellido");
		textFieldSecondSurnameToDeletePatient.setColumns(10);
		textFieldSecondSurnameToDeletePatient.setBounds(109, 215, 100, 20);
		deletePatientsPanel.add(textFieldSecondSurnameToDeletePatient);

		textFieldErrorDeletePatient = new JLabel();
		textFieldErrorDeletePatient.setForeground(Color.RED);

		textFieldErrorDeletePatient.setBounds(436, 244, 154, 20);
		deletePatientsPanel.add(textFieldErrorDeletePatient);

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

		JButton btnModificarEstadoPaciente = new RoundedJButton(15);
		btnModificarEstadoPaciente.setText("Ingresar");
		btnModificarEstadoPaciente.setOpaque(false);
		btnModificarEstadoPaciente.setBackground(new Color(255, 255, 255));
		btnModificarEstadoPaciente.setBounds(407, 381, 198, 41);
		addPatientsPanel.add(btnModificarEstadoPaciente);
		btnModificarEstadoPaciente.addActionListener(listener);

		JLabel lblNombre_1 = new JLabel("Nombre ");
		lblNombre_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNombre_1.setBounds(0, 47, 135, 16);
		addPatientsPanel.add(lblNombre_1);

		JLabel lblApellido2 = new JLabel("Apellido 1");
		lblApellido2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido2.setBounds(10, 88, 123, 16);
		addPatientsPanel.add(lblApellido2);

		JLabel lblApellido_12 = new JLabel("Apellido 2");
		addPatientsPanel.add(lblApellido_12);
		lblApellido_12.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));

		JLabel lblNifnie2 = new JLabel("NIFNIE");
		lblNifnie2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNifnie2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNifnie2.setBounds(10, 168, 125, 16);
		addPatientsPanel.add(lblNifnie2);

		JLabel lblRoom = new JLabel("Habitación");
		lblRoom.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblRoom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoom.setBounds(10, 209, 125, 16);
		addPatientsPanel.add(lblRoom);

		jcbNurse = new JComboBox();
		listener.filJComboBox(jcbNurse, false);
		jcbNurse.setBounds(392, 86, 213, 20);
		addPatientsPanel.add(jcbNurse);

		jcbMedic = new JComboBox();
		listener.filJComboBox(jcbMedic, true);
		jcbMedic.setBounds(392, 45, 213, 20);
		addPatientsPanel.add(jcbMedic);

		JLabel lblMedico_1 = new JLabel("Medico");
		lblMedico_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblMedico_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMedico_1.setBounds(278, 47, 93, 16);
		addPatientsPanel.add(lblMedico_1);

		JLabel lblEnfermera = new JLabel("Enfermera");
		lblEnfermera.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblEnfermera.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnfermera.setBounds(278, 88, 104, 16);
		addPatientsPanel.add(lblEnfermera);

		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblEnfermedad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnfermedad.setBounds(10, 254, 125, 16);
		addPatientsPanel.add(lblEnfermedad);

		JLabel lblApellido_6 = new JLabel("Apellido 2");
		lblApellido_6.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_6.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_6.setBounds(10, 128, 123, 16);
		addPatientsPanel.add(lblApellido_6);

		lberror = new JLabel("");

		lberror.setForeground(Color.RED);
		lberror.setBounds(34, 335, 452, 36);
		lberror.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientsPanel.add(lberror);

		// ---------------------------------------------PANEL BORRAR
		// EMPLEADOS--------------------------------------//
		deletePanel = new JPanel();
		deletePanel.setBackground(Color.WHITE);
		deletePanel.setBounds(274, 0, 695, 496);
		getContentPane().add(deletePanel);
		deletePanel.setLayout(null);
		deletePanel.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setBounds(10, 69, 86, 20);
		deletePanel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setBounds(10, 122, 86, 14);
		deletePanel.add(lblNewLabel_2);

		JLabel lblApellido_3 = new JLabel("1º Apellido");
		lblApellido_3.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_3.setBounds(-17, 169, 113, 14);
		deletePanel.add(lblApellido_3);

		JLabel lblapellido = new JLabel("2ºApellido");
		lblapellido.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblapellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblapellido.setBounds(-17, 218, 113, 14);
		deletePanel.add(lblapellido);

		JLabel lblNewLabel_3 = new JLabel("Introducir los siguientes datos para borrar");
		lblNewLabel_3.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_3.setBounds(39, 23, 367, 20);
		deletePanel.add(lblNewLabel_3);

		lblErrorDelete = new JLabel("");
		lblErrorDelete.setHorizontalAlignment(SwingConstants.TRAILING);
		lblErrorDelete.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblErrorDelete.setForeground(Color.RED);
		lblErrorDelete.setBounds(428, 245, 257, 14);
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
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label.setBounds(20, 71, 113, 23);
		editEmployeesPanel.add(label);

		JLabel label_2 = new JLabel("NIF/NIE");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_2.setBounds(30, 214, 103, 23);
		editEmployeesPanel.add(label_2);

		JLabel label_3 = new JLabel("Cuenta bancaria");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 14));
		label_3.setBounds(-20, 262, 153, 23);
		editEmployeesPanel.add(label_3);

		JLabel label_4 = new JLabel("Puesto");
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		label_4.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_4.setBounds(378, 71, 63, 23);
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
		label_5.setHorizontalAlignment(SwingConstants.TRAILING);
		label_5.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_5.setBounds(378, 122, 63, 23);
		editEmployeesPanel.add(label_5);

		labelUserNameEdit = new JLabel("");
		labelUserNameEdit.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		labelUserNameEdit.setBorder(new TitledBorder("Usuario"));
		labelUserNameEdit.setBounds(390, 173, 193, 41);
		editEmployeesPanel.add(labelUserNameEdit);

		JLabel lblApellido_2 = new JLabel("2º Apellido");
		lblApellido_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_2.setBounds(20, 174, 113, 14);
		editEmployeesPanel.add(lblApellido_2);

		JLabel label_9 = new JLabel("");
		label_9.setForeground(Color.RED);
		label_9.setBounds(390, 241, 212, 14);
		label_9.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		editEmployeesPanel.add(label_9);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 52, 610, 33);
		editEmployeesPanel.add(separator);

		JLabel lblNewLabel = new JLabel("Introduce el DNI");
		lblNewLabel.setBounds(10, 11, 109, 23);
		editEmployeesPanel.add(lblNewLabel);

		lblErrorEdit = new JLabel("");
		lblErrorEdit.setForeground(Color.RED);
		lblErrorEdit.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblErrorEdit.setBounds(358, 15, 244, 14);
		editEmployeesPanel.add(lblErrorEdit);

		JLabel lblApellido_1 = new JLabel("1º Apellido");
		lblApellido_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_1.setBounds(20, 123, 113, 20);
		editEmployeesPanel.add(lblApellido_1);

		NombreP = new HintTextField("Nombre");
		NombreP.setColumns(10);
		NombreP.setBounds(145, 44, 116, 22);
		addPatientsPanel.add(NombreP);

		surname1AddPatients = new HintTextField("1º Apellido");
		surname1AddPatients.setColumns(10);
		surname1AddPatients.setBounds(145, 85, 116, 22);
		addPatientsPanel.add(surname1AddPatients);

		surname2AddPatients = new HintTextField("2º Apellido");
		surname2AddPatients.setColumns(10);
		surname2AddPatients.setBounds(145, 124, 116, 22);
		addPatientsPanel.add(surname2AddPatients);

		DNI = new HintTextField("DNI");
		DNI.setBounds(145, 165, 116, 22);
		DNI.setColumns(10);
		addPatientsPanel.add(DNI);

		roomAddPatients = new HintTextField("Habitacion");
		roomAddPatients.setBounds(145, 206, 116, 22);
		roomAddPatients.setColumns(10);
		addPatientsPanel.add(roomAddPatients);

		textEnfermedad = new HintTextField("Enfermedad");
		textEnfermedad.setColumns(10);
		textEnfermedad.setBounds(145, 251, 116, 22);
		addPatientsPanel.add(textEnfermedad);

		textFieldNameEdit = new HintTextFieldNoText();
		textFieldNameEdit.setEnabled(false);
		textFieldNameEdit.setColumns(10);
		textFieldNameEdit.setBounds(143, 74, 143, 20);
		editEmployeesPanel.add(textFieldNameEdit);

		textFieldSurname1Edit = new HintTextFieldNoText();
		textFieldSurname1Edit.setEnabled(false);
		textFieldSurname1Edit.setColumns(10);
		textFieldSurname1Edit.setBounds(143, 125, 143, 20);
		editEmployeesPanel.add(textFieldSurname1Edit);

		textFieldDNIEdit = new HintTextFieldNoText();
		textFieldDNIEdit.setEnabled(false);
		textFieldDNIEdit.setColumns(10);
		textFieldDNIEdit.setBounds(143, 217, 143, 20);
		editEmployeesPanel.add(textFieldDNIEdit);

		textFieldBankEdit = new HintTextFieldNoText();
		textFieldBankEdit.setEnabled(false);
		textFieldBankEdit.setColumns(10);
		textFieldBankEdit.setBounds(143, 265, 143, 20);
		editEmployeesPanel.add(textFieldBankEdit);

		textFieldEmailEdit = new HintTextFieldNoText();
		textFieldEmailEdit.setEnabled(false);
		textFieldEmailEdit.setColumns(10);
		textFieldEmailEdit.setBounds(450, 128, 133, 20);
		editEmployeesPanel.add(textFieldEmailEdit);

		btnSaveEdit = new RoundedJButton(15);
		btnSaveEdit.setText("Guardar");
		btnSaveEdit.addActionListener(listener);
		btnSaveEdit.setOpaque(false);

		btnSaveEdit.setBackground(Color.WHITE);
		btnSaveEdit.setBounds(390, 252, 212, 47);
		editEmployeesPanel.add(btnSaveEdit);

		textFieldSurname2Edit = new HintTextFieldNoText();
		textFieldSurname2Edit.setEnabled(false);
		textFieldSurname2Edit.setColumns(10);
		textFieldSurname2Edit.setBounds(143, 173, 143, 20);
		editEmployeesPanel.add(textFieldSurname2Edit);

		textFieldSearchDNIEdit = new HintTextField("DNI");
		textFieldSearchDNIEdit.setBounds(110, 12, 133, 20);
		textFieldSearchDNIEdit.setColumns(10);
		editEmployeesPanel.add(textFieldSearchDNIEdit);

		JButton btnNewButton_1 = new RoundedJButton(15);
		btnNewButton_1.setText("Buscar");
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(listener);
		btnNewButton_1.setBounds(253, 11, 89, 23);
		editEmployeesPanel.add(btnNewButton_1);

		JButton btnDelete2 = new RoundedJButton(15);
		btnDelete2.setText("Eliminar");
		btnDelete2.addActionListener(listener);
		btnDelete2.setBounds(285, 244, 138, 20);
		btnDelete2.setOpaque(false);
		btnDelete2.setBackground(new Color(255, 255, 255));
		deletePatientsPanel.add(btnDelete2);

		textFieldDNIToDeletePatient = new HintTextField("DNI");
		textFieldDNIToDeletePatient.setBounds(111, 69, 100, 20);
		textFieldDNIToDeletePatient.setColumns(10);
		deletePatientsPanel.add(textFieldDNIToDeletePatient);

		textFieldDNIToDelete = new HintTextField("DNI");
		textFieldDNIToDelete.setBounds(106, 69, 138, 20);
		textFieldDNIToDelete.setColumns(10);
		deletePanel.add(textFieldDNIToDelete);

		textFieldNameToDelete = new HintTextField("Nombre");
		textFieldNameToDelete.setColumns(10);
		textFieldNameToDelete.setBounds(106, 119, 138, 20);
		deletePanel.add(textFieldNameToDelete);

		textFieldFirstDeleteToDelete = new HintTextField("1º Apellido");
		textFieldFirstDeleteToDelete.setColumns(10);
		textFieldFirstDeleteToDelete.setBounds(106, 166, 138, 20);
		deletePanel.add(textFieldFirstDeleteToDelete);

		textFieldSecondSurnameToDelete = new HintTextField("2º Apellido");
		textFieldSecondSurnameToDelete.setColumns(10);
		textFieldSecondSurnameToDelete.setBounds(106, 215, 138, 20);
		deletePanel.add(textFieldSecondSurnameToDelete);

		JButton btnDelete = new RoundedJButton(15);
		btnDelete.setText("Borrar");
		btnDelete.addActionListener(listener);
		btnDelete.setBounds(309, 244, 138, 20);
		deletePanel.add(btnDelete);

		JButton btnNewButton_4 = new RoundedJButton(15);
		btnNewButton_4.setText("Añadir o retirar");
		btnNewButton_4.setBackground(Color.WHITE);
		btnNewButton_4.addActionListener(listener);
		btnNewButton_4.setBounds(225, 83, 150, 34);
		seeWarehousePanel.add(btnNewButton_4);

		textFieldNewMedicine = new HintTextField("Medicamento");
		textFieldNewMedicine.setBounds(100, 225, 110, 20);
		textFieldNewMedicine.setColumns(10);
		seeWarehousePanel.add(textFieldNewMedicine);
		seeWarehousePanel.add(textFieldNewMedicine);

		textFieldNewMedicineAmount = new HintTextField("Cantidad(En Nº)");
		textFieldNewMedicineAmount.setColumns(10);
		textFieldNewMedicineAmount.setBounds(100, 256, 110, 20);
		seeWarehousePanel.add(textFieldNewMedicineAmount);

		JButton btnNewButton_2 = new RoundedJButton(15);
		btnNewButton_2.setText("Comprar nuevo");
		btnNewButton_2.addActionListener(listener);
		btnNewButton_2.setBounds(225, 289, 154, 23);
		seeWarehousePanel.add(btnNewButton_2);
		
		JLabel lblNewLabel_8 = new JLabel("+10");
		lblNewLabel_8.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_8.setBounds(430, 11, 26, 14);
		seeWarehousePanel.add(lblNewLabel_8);
		
		JLabel label_6 = new JLabel("+1");
		label_6.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_6.setBounds(393, 11, 26, 14);
		seeWarehousePanel.add(label_6);
		
		JLabel label_7 = new JLabel("-1");
		label_7.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_7.setBounds(353, 11, 26, 14);
		seeWarehousePanel.add(label_7);
		
		JLabel label_8 = new JLabel("-10");
		label_8.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_8.setBounds(304, 11, 26, 14);
		seeWarehousePanel.add(label_8);

		// ------------------------------------------PANEL AÑADIR
		// EMPLEADOS-----------------------------------------//

		addEmployeePanel = new JPanel();
		addEmployeePanel.setForeground(Color.WHITE);
		addEmployeePanel.setBackground(Color.WHITE);
		addEmployeePanel.setBounds(274, 0, 695, 496);
		getContentPane().add(addEmployeePanel);
		addEmployeePanel.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(71, 27, 80, 23);

		addEmployeePanel.add(lblNombre);

		JLabel lblApellidos = new JLabel("1º Apellido");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setBounds(52, 78, 99, 23);
		lblApellidos.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addEmployeePanel.add(lblApellidos);

		textFieldName = new HintTextField("Nombre"); // TODO TEXTFIELDS
		textFieldName.setBounds(161, 30, 143, 20);
		addEmployeePanel.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname1 = new HintTextField("1º Apellido");

		textFieldSurname1.setBounds(161, 81, 143, 20);
		addEmployeePanel.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNifnie.setBounds(71, 170, 80, 23);
		lblNifnie.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addEmployeePanel.add(lblNifnie);

		textFieldDNI = new HintTextField("DNI");
		textFieldDNI.setBounds(161, 173, 143, 20);
		textFieldDNI.setColumns(10);
		addEmployeePanel.add(textFieldDNI);

		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria");
		lblCuentaBancaria.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCuentaBancaria.setBounds(10, 215, 141, 23);
		lblCuentaBancaria.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 14));
		addEmployeePanel.add(lblCuentaBancaria);

		textFieldBankAccount = new HintTextField("Cuenta bancaria");
		textFieldBankAccount.setBounds(161, 218, 143, 20);
		textFieldBankAccount.setColumns(10);
		addEmployeePanel.add(textFieldBankAccount);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPuesto.setBounds(347, 27, 93, 23);
		lblPuesto.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addEmployeePanel.add(lblPuesto);

		comboBoxJob = new JComboBox<Object>();
		comboBoxJob.setBounds(450, 29, 133, 23);
		comboBoxJob.setBackground(Color.WHITE);
		comboBoxJob.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Administrador", "Medico", "Enfermero", "Secretario" }));
		comboBoxJob.setOpaque(false);
		addEmployeePanel.add(comboBoxJob);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEmail.setBounds(360, 78, 80, 23);
		lblEmail.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addEmployeePanel.add(lblEmail);

		textFieldEmail = new HintTextField("Email");
		textFieldEmail.setBounds(450, 81, 133, 20);
		textFieldEmail.setColumns(10);
		addEmployeePanel.add(textFieldEmail);

		lblUser = new JLabel("");
		lblUser.setBorder(new TitledBorder("Usuario"));
		lblUser.setBounds(390, 123, 193, 41);
		lblUser.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addEmployeePanel.add(lblUser);

		lblPassword = new JLabel("");
		lblPassword.setBorder(
				new TitledBorder(null, "Contrase\u00F1a", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblPassword.setBounds(390, 178, 193, 41);
		lblPassword.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addEmployeePanel.add(lblPassword);

		JButton btnRegister = new RoundedJButton(15);
		btnRegister.setText("Registrar");
		btnRegister.setBounds(390, 260, 212, 47);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		addEmployeePanel.add(btnRegister);
		btnRegister.addActionListener(listener);

		JLabel lblApellido = new JLabel("2º Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido.setBounds(52, 123, 99, 23);
		lblApellido.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addEmployeePanel.add(lblApellido);

		textFieldSurname2 = new HintTextField("2º Apellido");
		textFieldSurname2.setBounds(161, 126, 143, 20);
		textFieldSurname2.setColumns(10);
		addEmployeePanel.add(textFieldSurname2);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(390, 241, 212, 14);
		addEmployeePanel.add(lblError);

		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(41, 291, 252, 194);
		addEmployeePanel.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		getContentPane().add(seeEmployeesPanel);

		JButton btnNewButton_6 = new RoundedJButton(15);
		btnNewButton_6.setText("Buscar paciente");
		btnNewButton_6.addActionListener(listener);
		btnNewButton_6.setBounds(247, 7, 160, 23);
		editPatientPanel.add(btnNewButton_6);

		JButton btnNewButton_7 = new RoundedJButton(15);
		btnNewButton_7.setText("Guardar paciente");
		btnNewButton_7.addActionListener(listener);
		btnNewButton_7.setBounds(306, 272, 160, 23);
		editPatientPanel.add(btnNewButton_7);

		getContentPane().add(seeEmployeesPanel);

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

	public class RoundedJButton extends JButton {
		// Boton redondeado
		private Shape shape;

		public RoundedJButton(int size) {
			setOpaque(false); // As suggested by @AVD in comment.
			setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 13));
			setForeground(Color.BLACK);
			setBackground(Color.GRAY);

		}

		protected void paintComponent(Graphics g) {
			g.setColor(new Color(150, 150, 150));
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(new Color(190, 190, 190));
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			}
			return shape.contains(x, y);
		}

	}

	public class HintTextField extends JTextField implements FocusListener {
		// JText Field con bordes redondeados, y nombre de fondo, pasar como arguemnto
		// el texto de "pista"
		private final String hint;
		private boolean showingHint;
		private Shape shape;

		public HintTextField(final String hint) {
			super(hint);
			this.hint = hint;
			this.showingHint = true;
			super.addFocusListener(this);
			setOpaque(false); // As suggested by @AVD in comment.
			setBackground(new Color(228, 230, 230));
			setForeground(new Color(150, 150, 150));
			setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 11));

		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(Color.BLACK);
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			}
			return shape.contains(x, y);
		}

		@Override
		public void focusGained(FocusEvent e) {
			if (this.getText().isEmpty()) {
				super.setText("");
				setBorder(BorderFactory.createLineBorder(Color.black, 1));
				setForeground(Color.BLACK);
				showingHint = false;
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if (this.getText().isEmpty()) {
				super.setText(hint);
				setForeground(new Color(150, 150, 150));
				showingHint = true;
			}
		}

		@Override
		public String getText() {
			return showingHint ? "" : super.getText();
		}
	}

	public class RoundedJLabel extends JLabel {
		// LABEL COLOR GRIS
		private Shape shape;

		public RoundedJLabel() {
			setOpaque(false); // As suggested by @AVD in comment.
			setBackground(new Color(228, 230, 230));
			setForeground(Color.BLACK);
			setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 11));

		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(getBackground());
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			}
			return shape.contains(x, y);
		}

	}

	public class HintTextFieldNoText extends JTextField {
		// JText Field con bordes redondeados sin nombre de fondo

		private Shape shape;

		public HintTextFieldNoText() {

			setOpaque(false); // As suggested by @AVD in comment.
			setBackground(new Color(228, 230, 230));
			setForeground(new Color(150, 150, 150));
			setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 11));

		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(Color.BLACK);
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			}
			return shape.contains(x, y);
		}

	}
}
