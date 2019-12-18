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

	private int numAdmin;
	private int numDoc;
	private int numNurse;
	private int numSecre;

	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblError;

	private JButton btnVerPlantilla;

	private JLabel lblAdministrador;
	private JLabel lblMedico;
	private JLabel lblEnfermero;
	private JLabel lblSecretario;
	private JLabel lblTotal;

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

	private ControlerAdmin list;

	public AdminWindow(String user, String password) {
		log.InfoLog("Iniciada la sesion del administrador");
		this.user = user;
		this.password = password;
		list = new ControlerAdmin();
		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Administrador");

		try {

			initComponents();
			initComponentsPanels();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void initComponents() throws SQLException {

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		list.setNumberEmployees();
		ControlerAdmin list = new ControlerAdmin();

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

		JTextField textFieldName = new JTextField();
		textFieldName.setBounds(100, 30, 143, 20);
		addEmployeePane.add(textFieldName);
		textFieldName.setColumns(10);

		JTextField textFieldSurname1 = new JTextField();
		textFieldSurname1.setBounds(100, 81, 143, 20);
		addEmployeePane.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setBounds(10, 170, 63, 23);
		lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblNifnie);

		JTextField textFieldNIFNIE = new JTextField();
		textFieldNIFNIE.setBounds(100, 173, 143, 20);
		textFieldNIFNIE.setColumns(10);
		addEmployeePane.add(textFieldNIFNIE);

		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria");
		lblCuentaBancaria.setBounds(10, 215, 120, 23);
		lblCuentaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblCuentaBancaria);

		JTextField textFieldBankAccount = new JTextField();
		textFieldBankAccount.setBounds(132, 218, 143, 20);
		textFieldBankAccount.setColumns(10);
		addEmployeePane.add(textFieldBankAccount);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(390, 27, 63, 23);
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblPuesto);

		JComboBox<Object> comboBoxJob = new JComboBox<Object>();
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

		JTextField textFieldEmail = new JTextField();
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

		JTextField textFieldSurname2 = new JTextField();
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
}

/**
 *
 * @author xijoja TODO añadir en otraclase coontrolador controlador que conozca
 *         el ventana
 */
