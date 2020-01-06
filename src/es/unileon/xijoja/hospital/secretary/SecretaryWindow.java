package es.unileon.xijoja.hospital.secretary;

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
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.unileon.xijoja.hospital.InfoWindow;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;



@SuppressWarnings("serial")
public class SecretaryWindow extends JFrame {

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	private PacientesDAO dao;
	protected JPanel addPatientPane;
	protected JPanel getPatientPane;
	private ControlerSecretaryWindow listener;

	protected JComboBox jcbNurse;
	protected JComboBox jcbMedic;
	protected JTextField textFieldName;
	protected JTextField textFieldSurname1;
	protected JTextField textFieldSurname2;
	protected JTextField textFieldNIFNIE;
	protected JTextField textFieldRoom;
	protected JLabel lblError;
	protected JTextField textFieldNameGetPatient;
	protected JTextField textFieldSurname1GetPatient;
	protected JTextField textFieldDNIGetPatient;
	protected JTextField textFieldSearchDNIGetPatient;
	protected JTextField textFieldRoomGetPatient;
	protected JTextField textFielddisease;
	protected JLabel lblErrorGetPatient;
	protected JButton button;

	protected JTextField textFieldSurname2GetPatient;

	protected JTextField textFieldSearch;

	public SecretaryWindow() {

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Secretario");

		this.listener = new ControlerSecretaryWindow(this);
		
		try {
			dao = new PacientesDAO();
			initComponents();
			initComponentsPanels();

		} catch (SQLException e) {

			e.printStackTrace();
		}

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

		JButton btnNewButton = new JButton("A�adir Paciente");
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(28, 33, 234, 41);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(listener);

		JButton btnEditEmployee = new JButton("Buscar Paciente");
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
		
		JButton btnCloseSesion = new JButton("Cerrar sesion");
		btnCloseSesion.setBounds(842, 473, 117, 23);
		getContentPane().add(btnCloseSesion);
		btnCloseSesion.addActionListener(listener);
		
		JButton button = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/--ndice.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoWindow info = new InfoWindow("secretary");
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

		
		
		//------------------PANEL BUSCAR POR HABITACION O POR DNI--------------
				getPatientPane = new JPanel();
				getPatientPane.setBounds(284, 11, 624, 450);
				getContentPane().add(getPatientPane);
				getPatientPane.setLayout(null);
				getPatientPane.setForeground(Color.WHITE);
				getPatientPane.setBackground(Color.WHITE);
				getPatientPane.setVisible(false);

				JLabel label = new JLabel("Nombre");
				label.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label.setBounds(10, 74, 63, 23);
				getPatientPane.add(label);

				JLabel lblApellido_1 = new JLabel("1� Apellido");
				lblApellido_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblApellido_1.setBounds(10, 125, 80, 23);
				getPatientPane.add(lblApellido_1);

				textFieldNameGetPatient = new JTextField();
				textFieldNameGetPatient.setEnabled(false);
				textFieldNameGetPatient.setColumns(10);
				textFieldNameGetPatient.setBounds(100, 77, 143, 20);
				getPatientPane.add(textFieldNameGetPatient);

				textFieldSurname1GetPatient = new JTextField();
				textFieldSurname1GetPatient.setEnabled(false);
				textFieldSurname1GetPatient.setColumns(10);
				textFieldSurname1GetPatient.setBounds(100, 128, 143, 20);
				getPatientPane.add(textFieldSurname1GetPatient);

				JLabel label_2 = new JLabel("NIF/NIE");
				label_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label_2.setBounds(10, 217, 63, 23);
				getPatientPane.add(label_2);

				textFieldDNIGetPatient = new JTextField();
				textFieldDNIGetPatient.setEnabled(false);
				textFieldDNIGetPatient.setColumns(10);
				textFieldDNIGetPatient.setBounds(100, 220, 143, 20);
				getPatientPane.add(textFieldDNIGetPatient);

			

				JLabel lblApellido_2 = new JLabel("2� Apellido");
				lblApellido_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblApellido_2.setBounds(10, 170, 80, 23);
				getPatientPane.add(lblApellido_2);

				textFieldSurname2GetPatient = new JTextField();
				textFieldSurname2GetPatient.setEnabled(false);
				textFieldSurname2GetPatient.setColumns(10);
				textFieldSurname2GetPatient.setBounds(100, 173, 143, 20);
				getPatientPane.add(textFieldSurname2GetPatient);
				
				
				JLabel lblRoom = new JLabel("Habitacion");
				lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblRoom.setBounds(390, 74, 80, 23);
				getPatientPane.add(lblRoom);

				textFieldRoomGetPatient = new JTextField();
				textFieldRoomGetPatient.setEnabled(false);
				textFieldRoomGetPatient.setColumns(10);
				textFieldRoomGetPatient.setBounds(470, 77, 143, 20);
				getPatientPane.add(textFieldRoomGetPatient);
				
				
				JLabel label_9 = new JLabel("");
				label_9.setForeground(Color.RED);
				label_9.setBounds(390, 241, 212, 14);
				getPatientPane.add(label_9);

				

				JSeparator separator2 = new JSeparator();
				separator2.setBounds(10, 52, 610, 33);
				getPatientPane.add(separator2);

				JLabel lblNewLabel = new JLabel("Introduce el DNI o habitaci�n: ");
				lblNewLabel.setBounds(10, 11, 200, 23);
				getPatientPane.add(lblNewLabel);

				textFieldSearchDNIGetPatient = new JTextField();
				textFieldSearchDNIGetPatient.setBounds(210, 12, 133, 20);
				getPatientPane.add(textFieldSearchDNIGetPatient);
				textFieldSearchDNIGetPatient.setColumns(10);

				JButton btnNewButton_1 = new JButton("Buscar");
				btnNewButton_1.setForeground(Color.BLACK);
				btnNewButton_1.setBackground(Color.WHITE);
				btnNewButton_1.addActionListener(listener);

				btnNewButton_1.setBounds(353, 11, 89, 23);
				getPatientPane.add(btnNewButton_1);

				lblErrorGetPatient = new JLabel("");
				lblErrorGetPatient.setForeground(Color.RED);
				lblErrorGetPatient.setBounds(358, 15, 255, 14);
				getPatientPane.add(lblErrorGetPatient);

		//////////////////////////////////////////////////////////////A�adir pacientes

		addPatientPane = new JPanel();
		addPatientPane.setForeground(Color.WHITE);
		addPatientPane.setBackground(Color.WHITE);
		addPatientPane.setBounds(284, 11, 630, 450);
		getContentPane().add(addPatientPane);
		addPatientPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 27, 63, 23);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addPatientPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("1� Apellido");
		lblApellidos.setBounds(10, 78, 80, 23);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addPatientPane.add(lblApellidos);

		textFieldName = new JTextField();
		textFieldName.setBounds(100, 30, 143, 20);
		addPatientPane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname1 = new JTextField();
		textFieldSurname1.setBounds(100, 81, 143, 20);
		addPatientPane.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setBounds(10, 170, 63, 23);
		lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addPatientPane.add(lblNifnie);

		textFieldNIFNIE = new JTextField();
		textFieldNIFNIE.setBounds(100, 173, 143, 20);
		textFieldNIFNIE.setColumns(10);
		addPatientPane.add(textFieldNIFNIE);

		JLabel lblRoom2 = new JLabel("Habitacion");
		lblRoom2.setBounds(320, 27, 93, 23);
		lblRoom2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addPatientPane.add(lblRoom2);

		textFieldRoom = new JTextField();
		textFieldRoom.setBounds(400, 30, 103, 20);
		textFieldRoom.setColumns(10);
		addPatientPane.add(textFieldRoom);

		JButton btnRegister = new JButton("A�adir");
		btnRegister.setBounds(320, 260, 212, 47);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		addPatientPane.add(btnRegister);
		btnRegister.addActionListener(listener);

		JLabel lblApellido = new JLabel("2� Apellido");
		lblApellido.setBounds(10, 123, 80, 23);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addPatientPane.add(lblApellido);

		textFieldSurname2 = new JTextField();
		textFieldSurname2.setBounds(100, 126, 143, 20);
		textFieldSurname2.setColumns(10);
		addPatientPane.add(textFieldSurname2);
		
		JLabel lblnurse = new JLabel("Enfermero");
		lblnurse.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnurse.setBounds(320, 78, 80, 23);
		addPatientPane.add(lblnurse);

		jcbNurse = new JComboBox();
		listener.filJComboBox(jcbNurse,false);
		jcbNurse.setBounds(400, 81, 213, 20);
		addPatientPane.add(jcbNurse);
		
		JLabel lblMedic = new JLabel("M�dico");
		lblMedic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMedic.setBounds(320, 125, 80, 23);
		addPatientPane.add(lblMedic);

		jcbMedic = new JComboBox();
		listener.filJComboBox(jcbMedic,true);
		jcbMedic.setBounds(400, 128, 213, 20);
		addPatientPane.add(jcbMedic);

		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setBounds(320, 169, 93, 23);
		lblEnfermedad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addPatientPane.add(lblEnfermedad);

		textFielddisease = new JTextField();
		textFielddisease.setBounds(400, 172, 103, 20);
		textFielddisease.setColumns(10);
		addPatientPane.add(textFielddisease);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(320, 241, 352, 14);
		addPatientPane.add(lblError);

		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(43, 245, 252, 194);
		addPatientPane.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));

	}

	
}
