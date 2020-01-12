package es.unileon.xijoja.hospital.secretary;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;

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
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("etc/rexlia.ttf")));
		} catch (IOException | FontFormatException e) {
			// Handle exception
		}
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
		btnNewButton.setText("A�adir Paciente");
		btnNewButton.setOpaque(false);
		btnNewButton.setBounds(28, 33, 234, 41);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(listener);

		JButton btnEditEmployee = new RoundedJButton(15);
		btnEditEmployee.setText("Buscar Paciente");
		btnEditEmployee.setOpaque(false);
		btnEditEmployee.setBounds(28, 85, 234, 41);
		getContentPane().add(btnEditEmployee);
		btnEditEmployee.addActionListener(listener);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(274, 0, 7, 496);
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

		JButton btnCloseSesion = new RoundedJButton(15);
		btnCloseSesion.setText("Cerrar sesion");
		btnCloseSesion.setBounds(60, 465, 180, 23);
		getContentPane().add(btnCloseSesion);
		btnCloseSesion.addActionListener(listener);

		JButton button = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/--ndice.png")));
		button.setBackground(Color.LIGHT_GRAY);
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

		////////////////////////////////////////////////////////////// A�adir pacientes

		addPatientPane = new JPanel();
		addPatientPane.setForeground(Color.WHITE);
		addPatientPane.setBackground(Color.WHITE);
		addPatientPane.setBounds(274, 0, 695, 496);
		getContentPane().add(addPatientPane);
		addPatientPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(86, 27, 63, 23);
		lblNombre.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("1� Apellido");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setBounds(69, 78, 80, 23);
		lblApellidos.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblApellidos);

		textFieldName = new JTextField();
		textFieldName.setBounds(159, 30, 143, 20);
		addPatientPane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname1 = new JTextField();
		textFieldSurname1.setBounds(159, 81, 143, 20);
		addPatientPane.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNifnie.setBounds(86, 170, 63, 23);
		lblNifnie.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblNifnie);

		textFieldNIFNIE = new JTextField();
		textFieldNIFNIE.setBounds(159, 173, 143, 20);
		textFieldNIFNIE.setColumns(10);
		addPatientPane.add(textFieldNIFNIE);

		JLabel lblRoom2 = new JLabel("Habitacion");
		lblRoom2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoom2.setBounds(356, 27, 93, 23);
		lblRoom2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblRoom2);

		
		JButton btnRegister = new RoundedJButton(15);
		btnRegister.setText("A�adir");
		btnRegister.setBounds(320, 260, 212, 47);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		btnRegister.addActionListener(listener);
		addPatientPane.add(btnRegister);
		
		textFieldRoom = new JTextField();
		textFieldRoom.setBounds(459, 30, 103, 20);
		textFieldRoom.setColumns(10);
		addPatientPane.add(textFieldRoom);
		

		JLabel lblApellido = new JLabel("2� Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido.setBounds(69, 123, 80, 23);
		lblApellido.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblApellido);

		textFieldSurname2 = new JTextField();
		textFieldSurname2.setBounds(159, 126, 143, 20);
		textFieldSurname2.setColumns(10);
		addPatientPane.add(textFieldSurname2);

		JLabel lblnurse = new JLabel("Enfermero");
		lblnurse.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnurse.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblnurse.setBounds(369, 78, 80, 23);
		addPatientPane.add(lblnurse);

		jcbNurse = new JComboBox();
		listener.filJComboBox(jcbNurse, false);
		jcbNurse.setBounds(459, 81, 213, 20);
		addPatientPane.add(jcbNurse);

		JLabel lblMedic = new JLabel("M�dico");
		lblMedic.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMedic.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblMedic.setBounds(369, 123, 80, 23);
		addPatientPane.add(lblMedic);

		jcbMedic = new JComboBox();
		listener.filJComboBox(jcbMedic, true);
		jcbMedic.setBounds(459, 128, 213, 20);
		addPatientPane.add(jcbMedic);

		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnfermedad.setBounds(356, 170, 93, 23);
		lblEnfermedad.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblEnfermedad);

		textFielddisease = new JTextField();
		textFielddisease.setBounds(459, 172, 103, 20);
		textFielddisease.setColumns(10);
		addPatientPane.add(textFielddisease);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 7));
		lblError.setBounds(320, 241, 352, 14);
		addPatientPane.add(lblError);

		// ------------------PANEL BUSCAR POR HABITACION O POR DNI--------------
		getPatientPane = new JPanel();
		getPatientPane.setBounds(274, 0, 695, 496);
		getContentPane().add(getPatientPane);
		getPatientPane.setLayout(null);
		getPatientPane.setForeground(Color.WHITE);
		getPatientPane.setBackground(Color.WHITE);
		getPatientPane.setVisible(false);

		JLabel label = new JLabel("Nombre");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label.setBounds(10, 74, 122, 23);
		getPatientPane.add(label);

		JLabel lblApellido_1 = new JLabel("1� Apellido");
		lblApellido_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_1.setBounds(10, 125, 122, 23);
		getPatientPane.add(lblApellido_1);

		textFieldNameGetPatient = new JTextField();
		textFieldNameGetPatient.setEnabled(false);
		textFieldNameGetPatient.setColumns(10);
		textFieldNameGetPatient.setBounds(142, 77, 143, 20);
		getPatientPane.add(textFieldNameGetPatient);

		textFieldSurname1GetPatient = new JTextField();
		textFieldSurname1GetPatient.setEnabled(false);
		textFieldSurname1GetPatient.setColumns(10);
		textFieldSurname1GetPatient.setBounds(142, 128, 143, 20);
		getPatientPane.add(textFieldSurname1GetPatient);

		JLabel label_2 = new JLabel("NIF/NIE");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_2.setBounds(10, 217, 122, 23);
		getPatientPane.add(label_2);

		textFieldDNIGetPatient = new JTextField();
		textFieldDNIGetPatient.setEnabled(false);
		textFieldDNIGetPatient.setColumns(10);
		textFieldDNIGetPatient.setBounds(142, 220, 143, 20);
		getPatientPane.add(textFieldDNIGetPatient);

		JLabel lblApellido_2 = new JLabel("2� Apellido");
		lblApellido_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_2.setBounds(10, 170, 122, 23);
		getPatientPane.add(lblApellido_2);

		textFieldSurname2GetPatient = new JTextField();
		textFieldSurname2GetPatient.setEnabled(false);
		textFieldSurname2GetPatient.setColumns(10);
		textFieldSurname2GetPatient.setBounds(142, 173, 143, 20);
		getPatientPane.add(textFieldSurname2GetPatient);

		JLabel lblRoom = new JLabel("Habitacion");
		lblRoom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoom.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblRoom.setBounds(332, 74, 128, 23);
		getPatientPane.add(lblRoom);

		textFieldRoomGetPatient = new JTextField();
		textFieldRoomGetPatient.setEnabled(false);
		textFieldRoomGetPatient.setColumns(10);
		textFieldRoomGetPatient.setBounds(470, 77, 143, 20);
		getPatientPane.add(textFieldRoomGetPatient);

		JLabel label_9 = new JLabel("");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_9.setBounds(390, 241, 212, 14);
		getPatientPane.add(label_9);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 52, 610, 33);
		getPatientPane.add(separator2);

		JLabel lblNewLabel = new JLabel("Introduce el DNI o habitaci�n: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel.setBounds(10, 11, 275, 23);
		getPatientPane.add(lblNewLabel);

		textFieldSearchDNIGetPatient = new JTextField();
		textFieldSearchDNIGetPatient.setBounds(295, 14, 133, 20);
		getPatientPane.add(textFieldSearchDNIGetPatient);
		textFieldSearchDNIGetPatient.setColumns(10);

		JButton btnNewButton_1 = new RoundedJButton(15);
		btnNewButton_1.setText("Buscar");
		btnNewButton_1.addActionListener(listener);

		btnNewButton_1.setBounds(443, 11, 89, 23);
		getPatientPane.add(btnNewButton_1);

		lblErrorGetPatient = new JLabel("");
		lblErrorGetPatient.setForeground(Color.RED);
		lblErrorGetPatient.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 10));
		lblErrorGetPatient.setBounds(170, 35, 255, 14);

		getPatientPane.add(lblErrorGetPatient);

		

	}

	public class RoundedJButton extends JButton implements FocusListener {

		private Shape shape;

		public RoundedJButton(int size) {

			super.addFocusListener(this);
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

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
