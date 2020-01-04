package es.unileon.xijoja.hospital.nurse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;
import es.unileon.xijoja.hospital.medicWindow.ControlerMedicWindow;
import es.unileon.xijoja.hospital.medicWindow.MedicWindow;
import es.unileon.xijoja.hospital.secretary.ControlerSecretaryWindow;

public class NurseWindow extends JFrame {
	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	private PacientesDAO dao;
	protected JPanel seePatientPane;
	protected JPanel getPatientPane;
	private ControlerNurseWindow listener;
	protected String user;
	protected String password;

	protected JComboBox jcbPatient;
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
	protected JLabel lblErrorGetPatient;
	protected JButton button;
	protected JButton btnVerPlantilla;

	protected JTextField textFieldSurname2GetPatient;

	protected JTextField textFieldSearch;

	public NurseWindow(String user, String password) {
		this.user = user;
		this.password = password;

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Secretario");

		this.listener = new ControlerNurseWindow(this);
		
		try {
			dao = new PacientesDAO();
			initComponents();
			initComponentsPanels();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	/*
	 * TODO comprobar que se lanza desde loginwindow
	 * 
	 * 
	 * 
	 */
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

		btnVerPlantilla = new JButton("Ver Pacientes");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 85, 234, 41);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(listener);

		JButton btnEditEmployee = new JButton("Usar Medicamento");
		btnEditEmployee.setBackground(Color.WHITE);
		btnEditEmployee.setOpaque(false);
		btnEditEmployee.setBounds(28, 33, 234, 41);
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

		
	}

	private void initComponentsPanels() throws SQLException {
		seePatientPane = new JPanel();
		seePatientPane.setLayout(null);
		seePatientPane.setForeground(Color.WHITE);
		seePatientPane.setBackground(Color.WHITE);
		seePatientPane.setBounds(284, 11, 624, 450);
		seePatientPane.setPreferredSize(new Dimension(630, 700));
		seePatientPane.setVisible(false);
		getContentPane().add(seePatientPane);


		
		//------------------PANEL BUSCAR POR HABITACION O POR DNI--------------
				getPatientPane = new JPanel();
				getPatientPane.setBounds(284, 11, 624, 450);
				getContentPane().add(getPatientPane);
				getPatientPane.setLayout(null);
				getPatientPane.setForeground(Color.WHITE);
				getPatientPane.setBackground(Color.WHITE);
				getPatientPane.setVisible(true);

				JLabel label = new JLabel("Nombre");
				label.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label.setBounds(10, 74, 63, 23);
				getPatientPane.add(label);

				JLabel lblApellido_1 = new JLabel("1º Apellido");
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

			

				JLabel lblApellido_2 = new JLabel("2º Apellido");
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

				JLabel lblNewLabel = new JLabel("Seleciona el paciente: ");
				lblNewLabel.setBounds(10, 11, 200, 23);
				getPatientPane.add(lblNewLabel);
				
				jcbPatient = new JComboBox();
				listener.filJComboBox(jcbPatient);
				jcbPatient.setBounds(150, 12, 303, 20);
				getPatientPane.add(jcbPatient);

				JButton btnNewButton_1 = new JButton("Buscar");
				btnNewButton_1.setForeground(Color.BLACK);
				btnNewButton_1.setBackground(Color.WHITE);
				btnNewButton_1.addActionListener(listener);

				btnNewButton_1.setBounds(463, 11, 89, 23);
				getPatientPane.add(btnNewButton_1);

				lblErrorGetPatient = new JLabel("");
				lblErrorGetPatient.setForeground(Color.RED);
				lblErrorGetPatient.setBounds(358, 15, 143, 14);
				getPatientPane.add(lblErrorGetPatient);

	//	getContentPane().add(getPatientPane);

	}

	
}
