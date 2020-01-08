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
	protected JPanel addPatientsPanel;
	protected JPanel seePacientsPanel;
	protected JPanel deletePatientsPanel;
	protected JPanel seeWarehousePanel;

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
	protected JTextField textFieldDNIToDeletePacient;
	protected JTextField textFieldNameToDeleteEmployee;
	protected JTextField textFieldFirstSurnameToDeleteEmployee;
	protected JTextField textFieldSecondSurnameToDeleteEmployee;
	protected JTextField textFieldErrorDeletePacient;

	protected JLabel lblNumMorfina;
	protected JLabel lblNumBetadine;
	protected JLabel lblNumParacetalmol;
	protected JLabel lblNumAspirina;

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
		btnNewButton.setBounds(28, 33, 234, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(listener);

		btnVerPlantilla = new JButton("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 80, 234, 23);
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
		btnBorrarEmpleado.setBounds(28, 106, 234, 23);
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

		JButton btnVerAlmacen = new JButton("Ver Almacen");
		btnVerAlmacen.addActionListener(listener);
		btnVerAlmacen.setBounds(28, 231, 234, 23);
		getContentPane().add(btnVerAlmacen);

		JButton btnComprarMedicamentos = new JButton("Comprar medicamentos");
		btnComprarMedicamentos.setBounds(28, 265, 234, 23);
		getContentPane().add(btnComprarMedicamentos);
		JButton btnCloseSesion = new JButton("Cerrar sesion");
		btnCloseSesion.setBounds(842, 473, 117, 23);
		getContentPane().add(btnCloseSesion);

		JButton btnNewButton_2 = new JButton("Ingresar paciente");
		btnNewButton_2.setBounds(28, 130, 234, 23);
		getContentPane().add(btnNewButton_2);
		btnNewButton_2.addActionListener(listener);

		JButton btnVerPacientes = new JButton("Ver pacientes");
		btnVerPacientes.addActionListener(listener);
		btnVerPacientes.setBounds(28, 156, 234, 23);
		getContentPane().add(btnVerPacientes);

		JButton btnBorrarPaciente = new JButton("Borrar paciente");
		btnBorrarPaciente.addActionListener(listener);
		btnBorrarPaciente.setBounds(28, 182, 234, 23);
		getContentPane().add(btnBorrarPaciente);

		JButton btnEditarPaciente = new JButton("Editar Paciente");
		btnEditarPaciente.setBounds(28, 209, 234, 23);
		getContentPane().add(btnEditarPaciente);
		btnCloseSesion.addActionListener(listener);

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
//----------------------------------PANEL AÑADIR PACIENTES--------------------------//
		addPatientsPanel = new JPanel();
		addPatientsPanel.setVisible(false);

		// --------------------------------------PANEL VER
		// ALMACEN---------------------------------------//
		seeWarehousePanel = new JPanel();
		seeWarehousePanel.setVisible(false);
		seeWarehousePanel.setBackground(Color.WHITE);
		getContentPane().add(seeWarehousePanel);
		seeWarehousePanel.setBounds(284, 11, 624, 450);
		seeWarehousePanel.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("Paracetamol");
		lblNewLabel_4.setBounds(16, 51, 78, 14);
		seeWarehousePanel.add(lblNewLabel_4);

		JLabel lblAspirina = new JLabel("Aspirina");
		lblAspirina.setBounds(34, 74, 78, 14);
		seeWarehousePanel.add(lblAspirina);

		JLabel lblBetadine = new JLabel("Betadine");
		lblBetadine.setBounds(34, 99, 78, 14);
		seeWarehousePanel.add(lblBetadine);

		JLabel lblMorfina = new JLabel("Morfina");
		lblMorfina.setBounds(34, 124, 78, 14);
		seeWarehousePanel.add(lblMorfina);

		lblNumParacetalmol = new JLabel("New label");
		lblNumParacetalmol.setBounds(104, 51, 46, 14);
		seeWarehousePanel.add(lblNumParacetalmol);

		lblNumAspirina = new JLabel("New label");
		lblNumAspirina.setBounds(104, 74, 46, 14);
		seeWarehousePanel.add(lblNumAspirina);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(92, 51, 8, 87);
		seeWarehousePanel.add(separator_1);

		lblNumBetadine = new JLabel("New label");
		lblNumBetadine.setBounds(104, 99, 46, 14);
		seeWarehousePanel.add(lblNumBetadine);

		lblNumMorfina = new JLabel("New label");
		lblNumMorfina.setBounds(104, 124, 46, 14);
		seeWarehousePanel.add(lblNumMorfina);

		JButton btnNewButton_3 = new JButton(">");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int num = Integer.valueOf(lblNumParacetalmol.getText());
				num++;
				lblNumParacetalmol.setText(String.valueOf(num));
			}
		});
		btnNewButton_3.setBounds(254, 51, 41, 14);
		seeWarehousePanel.add(btnNewButton_3);

		JButton button = new JButton(">");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.valueOf(lblNumAspirina.getText());
				num++;
				lblNumAspirina.setText(String.valueOf(num));
			}
		});
		button.setBounds(254, 74, 41, 14);
		seeWarehousePanel.add(button);

		JButton button_1 = new JButton(">");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.valueOf(lblNumBetadine.getText());
				num++;
				lblNumBetadine.setText(String.valueOf(num));
			}
		});
		button_1.setBounds(254, 99, 41, 14);
		seeWarehousePanel.add(button_1);

		JButton button_2 = new JButton(">");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.valueOf(lblNumMorfina.getText());
				num++;
				lblNumMorfina.setText(String.valueOf(num));
			}
		});
		button_2.setBounds(254, 124, 41, 14);
		seeWarehousePanel.add(button_2);

		JButton button_3 = new JButton("<");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (Integer.valueOf(lblNumParacetalmol.getText()) <= 0) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumParacetalmol.getText());
					num--;
					lblNumParacetalmol.setText(String.valueOf(num));
				}
				
				
			}
		});
		button_3.setBounds(214, 51, 41, 14);
		seeWarehousePanel.add(button_3);

		JButton button_4 = new JButton("<");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lblNumAspirina.getText()) <= 0) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumAspirina.getText());
					num--;
					lblNumAspirina.setText(String.valueOf(num));
				}
			}
		});
		button_4.setBounds(214, 74, 41, 14);
		seeWarehousePanel.add(button_4);

		JButton button_5 = new JButton("<");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lblNumBetadine.getText()) <= 0) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumBetadine.getText());
					num--;
					lblNumBetadine.setText(String.valueOf(num));
				}
			}
		});
		button_5.setBounds(214, 99, 41, 14);
		seeWarehousePanel.add(button_5);

		JButton button_6 = new JButton("<");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lblNumMorfina.getText()) <= 0) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumMorfina.getText());
					num--;
					lblNumMorfina.setText(String.valueOf(num));
				}
			}
		});
		button_6.setBounds(214, 124, 41, 14);
		seeWarehousePanel.add(button_6);

		JButton btnNewButton_4 = new JButton("Añadir o retirar");
		btnNewButton_4.addActionListener(listener);
		btnNewButton_4.setBounds(262, 172, 133, 34);
		seeWarehousePanel.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton(">>");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.valueOf(lblNumParacetalmol.getText());
				num = num + 5;
				lblNumParacetalmol.setText(String.valueOf(num));
			}
		});
		btnNewButton_5.setBounds(295, 51, 49, 14);
		seeWarehousePanel.add(btnNewButton_5);

		JButton button_7 = new JButton(">>");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.valueOf(lblNumAspirina.getText());
				num = num + 5;
				lblNumAspirina.setText(String.valueOf(num));
			}
		});
		button_7.setBounds(295, 74, 49, 14);
		seeWarehousePanel.add(button_7);

		JButton button_8 = new JButton(">>");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.valueOf(lblNumBetadine.getText());
				num = num + 5;
				lblNumBetadine.setText(String.valueOf(num));
			}
		});
		button_8.setBounds(295, 99, 49, 14);
		seeWarehousePanel.add(button_8);

		JButton button_9 = new JButton(">>");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int num = Integer.valueOf(lblNumMorfina.getText());
				num = num + 5;
				lblNumMorfina.setText(String.valueOf(num));
			}
		});
		button_9.setBounds(295, 124, 49, 14);
		seeWarehousePanel.add(button_9);

		JButton button_10 = new JButton("<<");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lblNumParacetalmol.getText()) < 5) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumParacetalmol.getText());
					num = num - 5;
					lblNumParacetalmol.setText(String.valueOf(num));
				}
			}
		});
		button_10.setBounds(168, 51, 49, 14);
		seeWarehousePanel.add(button_10);

		JButton button_11 = new JButton("<<");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lblNumAspirina.getText()) < 5) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumAspirina.getText());
					num = num - 5;
					lblNumAspirina.setText(String.valueOf(num));
				}
			}
		});
		button_11.setBounds(168, 74, 49, 14);
		seeWarehousePanel.add(button_11);

		JButton button_12 = new JButton("<<");
		button_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lblNumBetadine.getText()) < 5) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumBetadine.getText());
					num = num - 5;
					lblNumBetadine.setText(String.valueOf(num));
				}
			}
		});
		button_12.setBounds(168, 99, 49, 14);
		seeWarehousePanel.add(button_12);

		JButton button_13 = new JButton("<<");
		button_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.valueOf(lblNumMorfina.getText()) < 5) {
					//TODO poner aqui el error en el label
				} else {
					int num = Integer.valueOf(lblNumMorfina.getText());
					num = num - 5;
					lblNumMorfina.setText(String.valueOf(num));
				}
			}
		});
		button_13.setBounds(168, 124, 49, 14);
		seeWarehousePanel.add(button_13);

// ------------------------------PANEL BORRAR EMPLEADOS-------------------------//
		deletePatientsPanel = new JPanel();
		deletePatientsPanel.setVisible(false);
		deletePatientsPanel.setBackground(Color.WHITE);
		deletePatientsPanel.setBounds(274, 0, 649, 467);
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

		textFieldDNIToDeletePacient = new JTextField();
		textFieldDNIToDeletePacient.setBounds(111, 69, 100, 20);
		deletePatientsPanel.add(textFieldDNIToDeletePacient);
		textFieldDNIToDeletePacient.setColumns(10);

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

		textFieldErrorDeletePacient = new JTextField();
		textFieldErrorDeletePacient.setCaretColor(Color.RED);
		textFieldErrorDeletePacient.setBounds(436, 244, 154, 20);
		deletePatientsPanel.add(textFieldErrorDeletePacient);
		textFieldErrorDeletePacient.setColumns(10);
		addPatientsPanel.setBounds(278, 11, 630, 450);
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

// ---------------------------------------------PANEL BORRAR EMPLEADOS--------------------------------------//
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
// --------------------------------------------PANEL EDITAR EMPLEADOS-------------------------------------//
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
		lblApellido_1.setBounds(32, 127, 101, 16);

// ---------------------------------------PANEL VER PACIENTES-----------------------------------//
		seePacientsPanel = new JPanel();
		seePacientsPanel.setLayout(null);
		seePacientsPanel.setForeground(Color.WHITE);
		seePacientsPanel.setBackground(Color.WHITE);
		seePacientsPanel.setBounds(284, 11, 624, 450);
		seePacientsPanel.setPreferredSize(new Dimension(630, 700));
		seePacientsPanel.setVisible(false);
		getContentPane().add(seePacientsPanel);
	}
}
