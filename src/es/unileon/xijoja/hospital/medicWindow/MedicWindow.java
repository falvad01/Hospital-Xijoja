package es.unileon.xijoja.hospital.medicWindow;

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

import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;

@SuppressWarnings("serial")
public class MedicWindow extends JFrame {

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	Toolkit screen;
	private Logs log = new Logs();
	protected JPanel seePacientsPanel;
	protected JPanel addPatientsPanel;
	private PacientesDAO dao;
	private ControlerMedicWindow listener;
	protected JTextField NombreP;
	protected JTextField Apellido1;
	protected JTextField Apellido2;
	protected JTextField DNI;
	protected JTextField Habitacion;
	protected JTextField Medicamentos;
	protected JLabel lberror;
	protected JComboBox jcbNurse;
	protected JComboBox jcbMedic;


	// TODO las variables que se quieran ser usadas en el controlador tienen que
	// estar en protected NO en private

	public MedicWindow() {

		log.InfoLog("Iniciada la sesion del medico");
		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Medico");
		listener = new ControlerMedicWindow(this);
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

		JButton crossButton = new JButton(new ImageIcon(MedicWindow.class.getResource("/resources/cross.png")));
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

		JButton minButton = new JButton(new ImageIcon(MedicWindow.class.getResource("/resources/min.png")));
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
		
		JButton btnAsignarMedicamentoPaciente = new JButton("Asignar Medicamento Paciente");
		btnAsignarMedicamentoPaciente.setOpaque(false);
		btnAsignarMedicamentoPaciente.setBackground(new Color(255, 255, 255));
		btnAsignarMedicamentoPaciente.setBounds(10, 144, 229, 52);
		getContentPane().add(btnAsignarMedicamentoPaciente);
		
		JButton btnVerPacientes = new JButton("Ver Pacientes");
		btnVerPacientes.setOpaque(false);
		btnVerPacientes.setBackground(new Color(255, 255, 255));
		btnVerPacientes.setBounds(10, 209, 229, 41);
		getContentPane().add(btnVerPacientes);
		btnVerPacientes.addActionListener(listener);
		
		JButton btnInformeDeLa = new JButton("Informe de la semana");
		btnInformeDeLa.setOpaque(false);
		btnInformeDeLa.setBackground(new Color(255, 255, 255));
		btnInformeDeLa.setBounds(10, 263, 229, 41);
		getContentPane().add(btnInformeDeLa);
		btnInformeDeLa.addActionListener(listener);
		
		JButton btnDarAltaPaciente = new JButton("Dar alta Paciente");
		btnDarAltaPaciente.setOpaque(false);
		btnDarAltaPaciente.setBackground(new Color(255, 255, 255));
		btnDarAltaPaciente.setBounds(10, 86, 229, 45);
		getContentPane().add(btnDarAltaPaciente);
		btnAsignarMedicamentoPaciente.addActionListener(listener);
		
		JButton btnIngresarPaciente = new JButton("Ingresar Paciente");
		btnIngresarPaciente.setOpaque(false);
		btnIngresarPaciente.setBackground(new Color(255, 255, 255));
		btnIngresarPaciente.setBounds(10, 21, 229, 52);
		getContentPane().add(btnIngresarPaciente);
		btnIngresarPaciente.addActionListener(listener);

	}

	
	private void initComponentsPanels() throws SQLException {
		// TODO el lister de los botones se llama listenner(YA ESTA DECLARADO E
		// INICIALIZADO)
		
		//panel ver pacientes
		seePacientsPanel = new JPanel();
		seePacientsPanel.setLayout(null);
		seePacientsPanel.setForeground(Color.WHITE);
		seePacientsPanel.setBackground(Color.WHITE);
		seePacientsPanel.setBounds(284, 11, 624, 450);
		seePacientsPanel.setPreferredSize(new Dimension(630, 700));
		seePacientsPanel.setVisible(false);
		//panel ingresar paciente
		
		addPatientsPanel = new JPanel();
		addPatientsPanel.setBounds(278, 11, 630, 450);
		getContentPane().add(addPatientsPanel);
		addPatientsPanel.setLayout(null);
		addPatientsPanel.setForeground(Color.WHITE);
		addPatientsPanel.setBackground(Color.WHITE);
		addPatientsPanel.setVisible(true);
		
		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(290, 128, 271, 222);
		addPatientsPanel.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		getContentPane().add(addPatientsPanel);
		
		JButton btnModificarEstadoPaciente = new JButton("Registrar");
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
		
		JLabel lblApellido = new JLabel("Apellido 1");
		lblApellido.setBounds(32, 88, 101, 16);
		addPatientsPanel.add(lblApellido);
		
		JLabel lblApellido_1 = new JLabel("Apellido 2");
		lblApellido_1.setBounds(32, 127, 101, 16);
		addPatientsPanel.add(lblApellido_1);
		
		Apellido1 = new JTextField();
		Apellido1.setColumns(10);
		Apellido1.setBounds(145, 85, 116, 22);
		addPatientsPanel.add(Apellido1);
		
		Apellido2 = new JTextField();
		Apellido2.setColumns(10);
		Apellido2.setBounds(145, 124, 116, 22);
		addPatientsPanel.add(Apellido2);
		
		JLabel lblNifnie = new JLabel("NIFNIE");
		lblNifnie.setBounds(32, 168, 56, 16);
		addPatientsPanel.add(lblNifnie);
		
		DNI = new JTextField();
		DNI.setBounds(145, 165, 116, 22);
		addPatientsPanel.add(DNI);
		DNI.setColumns(10);
		
		JLabel lblHabitacin = new JLabel("Habitación");
		lblHabitacin.setBounds(32, 209, 89, 16);
		addPatientsPanel.add(lblHabitacin);
		
		Habitacion = new JTextField();
		Habitacion.setBounds(145, 206, 116, 22);
		addPatientsPanel.add(Habitacion);
		Habitacion.setColumns(10);
		lberror = new JLabel("");
		lberror.setForeground(Color.RED);
		lberror.setBounds(390, 241, 212, 14);
		addPatientsPanel.add(lberror);
		
		jcbNurse = new JComboBox();
		listener.filJComboBox(jcbNurse,false);
		jcbNurse.setBounds(392, 45, 213, 20);
		addPatientsPanel.add(jcbNurse);
		
		jcbMedic = new JComboBox();
		listener.filJComboBox(jcbMedic,true);
		jcbMedic.setBounds(392, 86, 213, 20);
		addPatientsPanel.add(jcbMedic);
		
		JLabel lblMedico = new JLabel("Medico");
		lblMedico.setBounds(307, 47, 56, 16);
		addPatientsPanel.add(lblMedico);
		
		JLabel lblEnfermera = new JLabel("Enfermera");
		lblEnfermera.setBounds(307, 88, 73, 16);
		addPatientsPanel.add(lblEnfermera);
		getContentPane().add(seePacientsPanel);
		/*
		JLabel lblMedicamentos = new JLabel("Medicamentos");
		lblMedicamentos.setBounds(32, 298, 116, 16);
		addPatientsPanel.add(lblMedicamentos);
		
		Medicamentos = new JTextField();
		Medicamentos.setBounds(145, 295, 116, 22);
		addPatientsPanel.add(Medicamentos);
		Medicamentos.setColumns(10);
		
		
		*/
		
		
		//
		
		
		
	}
}
