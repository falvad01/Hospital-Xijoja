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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import es.unileon.xijoja.hospital.InfoWindow;
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
	protected JScrollPane seePatientPane;
	protected JPanel getPatientPane;
	private ControlerNurseWindow listener;
	protected String user;
	protected String password;

	protected JComboBox jcbPatient;
	protected JComboBox jcbNUtits;
	
	protected JLabel lblError;
	protected JTextField textFieldMedicine;
	protected JTextField textFieldUnits;
	
	protected JLabel lblErrorGetPatient;
	protected JButton button;
	protected JButton btnVerPlantilla;
	protected JButton btnUseMedicine ;


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

	private void initComponents() throws SQLException {

		getContentPane().setBackground(Color.lightGray);
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
		
		JButton btnCloseSesion = new JButton("Cerrar sesion");
		btnCloseSesion.setBounds(842, 473, 117, 23);
		getContentPane().add(btnCloseSesion);
		btnCloseSesion.addActionListener(listener);
		
		JButton button = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/--ndice.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoWindow info = new InfoWindow("nurse");
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
		
		//-------------------------------PANEL DE LA TABLA-------------------
		


		seePatientPane = new JScrollPane();
		seePatientPane.setBounds(274, 0, 695, 496);
		seePatientPane.setForeground(Color.WHITE);
		seePatientPane.setBackground(Color.WHITE);
		seePatientPane.setPreferredSize(new Dimension(630, 420));
        seePatientPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        seePatientPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		seePatientPane.setVisible(false);
		getContentPane().add(seePatientPane);
	

       

		
		//------------------PANEL BUSCAR POR HABITACION O POR DNI--------------
				getPatientPane = new JPanel();
				getPatientPane.setBounds(274, 0, 695, 496);
				getContentPane().add(getPatientPane);
				getPatientPane.setLayout(null);
				getPatientPane.setForeground(Color.WHITE);
				getPatientPane.setBackground(Color.WHITE);
				getPatientPane.setVisible(true);

				JLabel label = new JLabel("Medicamento");
				label.setFont(new Font("Tahoma", Font.PLAIN, 15));
				label.setBounds(10, 74, 190, 23);
				getPatientPane.add(label);

				JLabel lblApellido_1 = new JLabel("Tratamiento restante: ");
				lblApellido_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblApellido_1.setBounds(10, 125, 190, 23);
				getPatientPane.add(lblApellido_1);

				textFieldMedicine = new JTextField();
				textFieldMedicine.setEnabled(false);
				textFieldMedicine.setColumns(10);
				textFieldMedicine.setBounds(205, 77, 163, 20);
				getPatientPane.add(textFieldMedicine);

				textFieldUnits = new JTextField();
				textFieldUnits.setEnabled(false);
				textFieldUnits.setColumns(10);
				textFieldUnits.setBounds(205, 128, 163, 20);
				getPatientPane.add(textFieldUnits);
				
				JLabel lblSelectUnits = new JLabel("Cuantas unidades a tratar: ");
				lblSelectUnits.setFont(new Font("Tahoma", Font.PLAIN, 15));
				lblSelectUnits.setBounds(10, 179, 190, 23);
				getPatientPane.add(lblSelectUnits);
				
				jcbNUtits = new JComboBox();
				jcbNUtits.setBounds(205, 179, 50, 20);
				getPatientPane.add(jcbNUtits);

				
				 btnUseMedicine = new JButton("Tratamiento");
				btnUseMedicine.setForeground(Color.BLACK);
				btnUseMedicine.setBackground(Color.WHITE);
				btnUseMedicine.setHorizontalAlignment(SwingConstants.CENTER);
				btnUseMedicine.setEnabled(false);
				btnUseMedicine.addActionListener(listener);

				btnUseMedicine.setBounds(200, 230, 170, 40);
				getPatientPane.add(btnUseMedicine);
				
		
				
				
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
				jcbPatient.setBounds(150, 12, 343, 20);
				getPatientPane.add(jcbPatient);

				JButton btnNewButton_1 = new JButton("Buscar");
				btnNewButton_1.setForeground(Color.BLACK);
				btnNewButton_1.setBackground(Color.WHITE);
				btnNewButton_1.addActionListener(listener);

				btnNewButton_1.setBounds(503, 11, 89, 23);
				getPatientPane.add(btnNewButton_1);

				lblErrorGetPatient = new JLabel("");
				lblErrorGetPatient.setForeground(Color.RED);
				lblErrorGetPatient.setBounds(378, 240, 273, 14);
				getPatientPane.add(lblErrorGetPatient);


	}

	
}
