package es.unileon.xijoja.hospital.medicWindow;

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
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import es.unileon.xijoja.hospital.InfoWindow;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PatientsDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow.HintTextField;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow.RoundedJButton;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow.RoundedJLabel;

import javax.swing.JTextPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class MedicWindow extends JFrame {

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	Toolkit screen;
	private Logs log = new Logs();
	protected JScrollPane seePacientsPanel;
	protected JPanel addPatientsPanel;
	protected JPanel deletePatientsPanel;
	protected JPanel addMedicine;
	protected JPanel getPatientPane;
	protected JPanel week;
	
	private PatientsDAO dao;
	private ControlerMedicWindow listener;
	protected JTextField NombreP;
	protected JTextField Apellido1;
	protected JTextField Apellido2;
	protected JTextField DNI;
	protected JTextField Habitacion;
	protected JTextField Medicamentos;
	protected JLabel lberror;
	protected JLabel lblError2;
	protected JComboBox jcbNurse;
	protected JComboBox jcbMedic;
	protected JComboBox jcbMedicine;
	protected JComboBox jcbMedicineadd;
	protected RoundedJLabel textFieldNameGetPatient;
	protected RoundedJLabel textFieldSurname1GetPatient;
	protected RoundedJLabel textFieldDNIGetPatient;
	protected JTextField textFieldSearchDNIGetPatient;
	protected RoundedJLabel textFieldRoomGetPatient;
	protected JTextField textFieldDNIToDelete;
	protected JTextField textFieldNameToDelete;
	protected JTextField textFieldFirstDeleteToDelete;
	protected JTextField textFieldSecondDeleteToDelete;
	protected JLabel lblErrorDelete;
	protected JLabel lblErrorGetPatient;
	protected RoundedJLabel textFieldSurname2GetPatient;
	protected JTextField textFieldSearch;
	protected JTextField textFieldDNI;
	protected JTextField textField;
    protected JTextField textField_1;
    protected JTextField units;
    protected JTextField DNIM;
    protected JTextField textEnfermedad;
    protected JButton button;
    protected JTextField textU;
    protected JTextField eliminados;
    protected JTextField adds;
    protected JTextField meds;

		public MedicWindow() { 	


		log.InfoLog("Iniciada la sesion del medico");
		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Medico");
		listener = new ControlerMedicWindow(this);
		try {
			dao = new PatientsDAO();
			initComponents();
			initComponentsPanels();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		

	}

	private void initComponents() throws SQLException {

		getContentPane().setBackground(Color.LIGHT_GRAY);
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
		
		JButton btnAsignarMedicamentoPaciente = new RoundedJButton(15);
		btnAsignarMedicamentoPaciente.setText("Asignar Medicamentos");
		btnAsignarMedicamentoPaciente.setOpaque(false);
		btnAsignarMedicamentoPaciente.setBounds(28, 130, 234, 45);
		getContentPane().add(btnAsignarMedicamentoPaciente);
		btnAsignarMedicamentoPaciente.addActionListener(listener);
		
		JButton btnVerPacientes = new RoundedJButton(15);
		btnVerPacientes.setText("Ver Pacientes");
		btnVerPacientes.setOpaque(false);
		btnVerPacientes.setBounds(28, 186, 234, 45);
		getContentPane().add(btnVerPacientes);
		btnVerPacientes.addActionListener(listener);
		
		JButton btnInformeDeLa = new RoundedJButton(15);
		btnInformeDeLa.setText("Informe de la semana");
		btnInformeDeLa.setOpaque(false);
		btnInformeDeLa.setBounds(28, 240, 234, 45);
		getContentPane().add(btnInformeDeLa);
		btnInformeDeLa.addActionListener(listener);
		
		JButton btnDarAltaPaciente = new RoundedJButton(15);
		btnDarAltaPaciente.setText("Dar alta Paciente");
		btnDarAltaPaciente.setOpaque(false);
		btnDarAltaPaciente.setBounds(28, 76, 234, 45);
		getContentPane().add(btnDarAltaPaciente);
		btnDarAltaPaciente.addActionListener(listener);
		
		JButton btnIngresarPaciente = new RoundedJButton(15);
		btnIngresarPaciente.setText("Ingresar Paciente");
		btnIngresarPaciente.setOpaque(false);
		btnIngresarPaciente.setBounds(28, 22, 234, 45);
		getContentPane().add(btnIngresarPaciente);
		btnIngresarPaciente.addActionListener(listener);
		
		JButton btnCerrarSesin = new RoundedJButton(15);
		btnCerrarSesin.setText("Cerrar Sesión");
		btnCerrarSesin.setBounds(60, 465, 180, 23);
		btnCerrarSesin.setOpaque(false);
		getContentPane().add(btnCerrarSesin);
		btnCerrarSesin.addActionListener(listener);
		
		JButton btnNewButton = new RoundedJButton(15);
		btnNewButton.setText("Buscar Paciente");
		btnNewButton.setBounds(28, 295, 234, 45);
		btnNewButton.setOpaque(false);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(listener);
		
		JButton button = new JButton(new ImageIcon(LoginWindow.class.getResource("/resources/--ndice.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InfoWindow info = new InfoWindow("medico");
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
	
		
		//panel ver pacientes

		seePacientsPanel = new JScrollPane();
		seePacientsPanel.setBounds(278, 0, 691, 496);
		seePacientsPanel.setForeground(Color.WHITE);
		seePacientsPanel.setBackground(Color.WHITE);
		seePacientsPanel.setPreferredSize(new Dimension(630, 420));
		seePacientsPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		seePacientsPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		seePacientsPanel.setVisible(false);
		getContentPane().add(seePacientsPanel);
		
		//panel ingresar paciente
		
		
		addPatientsPanel = new JPanel();
		addPatientsPanel.setBounds(278, 0, 691, 496);
		getContentPane().add(addPatientsPanel);
		addPatientsPanel.setLayout(null);
		addPatientsPanel.setForeground(Color.WHITE);
		addPatientsPanel.setBackground(Color.WHITE);
		addPatientsPanel.setVisible(true);
		
		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(355, 127, 250, 222);
		addPatientsPanel.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		getContentPane().add(addPatientsPanel);
		
		JButton btnModificarEstadoPaciente = new RoundedJButton(15);
		btnModificarEstadoPaciente.setText("Registrar");
		btnModificarEstadoPaciente.setOpaque(false);
		btnModificarEstadoPaciente.setBounds(407, 381, 198, 41);
		addPatientsPanel.add(btnModificarEstadoPaciente);
		btnModificarEstadoPaciente.addActionListener(listener);
		
				
				JLabel label_1 = new JLabel("Nombre");
				label_1.setHorizontalAlignment(SwingConstants.TRAILING);
				label_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				label_1.setBounds(0, 46, 128, 16);
				addPatientsPanel.add(label_1);
				
				NombreP = new HintTextField("Nombre");
				NombreP.setColumns(10);
				NombreP.setBounds(145, 44, 116, 22);
				addPatientsPanel.add(NombreP);
				
				JLabel lblApellido = new JLabel("Apellido 1");
				lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
				lblApellido.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblApellido.setBounds(0, 87, 128, 16);
				addPatientsPanel.add(lblApellido);
				
				JLabel lblApellido_1 = new JLabel("Apellido 2");
				lblApellido_1.setHorizontalAlignment(SwingConstants.TRAILING);
				lblApellido_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblApellido_1.setBounds(0, 127, 128, 16);
				addPatientsPanel.add(lblApellido_1);
				
				Apellido1 = new HintTextField("Apellido1");
				Apellido1.setColumns(10);
				Apellido1.setBounds(145, 85, 116, 22);
				addPatientsPanel.add(Apellido1);
				
				Apellido2 = new HintTextField("Apellido2");
				Apellido2.setColumns(10);
				Apellido2.setBounds(145, 124, 116, 22);
				addPatientsPanel.add(Apellido2);
				
				jcbMedicineadd = new JComboBox();
				jcbMedicineadd.setBounds(145, 295, 116, 20);
				addPatientsPanel.add(jcbMedicineadd);
				listener.filJComboBoxMedicines(jcbMedicineadd);
				
				JLabel lblNifnie = new JLabel("NIFNIE");
				lblNifnie.setHorizontalAlignment(SwingConstants.TRAILING);
				lblNifnie.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblNifnie.setBounds(0, 169, 128, 16);
				addPatientsPanel.add(lblNifnie);
				
				DNI = new HintTextField("introduce DNI");
				DNI.setBounds(145, 165, 116, 22);
				addPatientsPanel.add(DNI);
				DNI.setColumns(10);
				
				JLabel lblHabitacin = new JLabel("Habitacion");
				lblHabitacin.setHorizontalAlignment(SwingConstants.TRAILING);
				lblHabitacin.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblHabitacin.setBounds(0, 212, 128, 16);
				addPatientsPanel.add(lblHabitacin);
				
				Habitacion = new HintTextField("Habitacion");
				Habitacion.setBounds(145, 206, 116, 22);
				addPatientsPanel.add(Habitacion);
				Habitacion.setColumns(10);
				
				lberror = new JLabel("");
				lberror.setForeground(Color.RED);
				lberror.setBounds(50, 361, 260, 46);
				addPatientsPanel.add(lberror);
				
				jcbNurse = new JComboBox();
				listener.filJComboBox(jcbNurse,false);
				jcbNurse.setBounds(392, 86, 213, 20);
				addPatientsPanel.add(jcbNurse);
				
				jcbMedic = new JComboBox();
				listener.filJComboBox(jcbMedic,true);
				jcbMedic.setBounds(392, 45, 213, 20);
				addPatientsPanel.add(jcbMedic);
				
				JLabel lblMedico = new JLabel("Medico");
				lblMedico.setHorizontalAlignment(SwingConstants.TRAILING);
				lblMedico.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblMedico.setBounds(279, 46, 101, 16);
				addPatientsPanel.add(lblMedico);
				
				JLabel lblEnfermera = new JLabel("Enfermera");
				lblEnfermera.setHorizontalAlignment(SwingConstants.TRAILING);
				lblEnfermera.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblEnfermera.setBounds(249, 87, 131, 16);
				addPatientsPanel.add(lblEnfermera);
				
				JLabel lblEnfermedad = new JLabel("Enfermedad");
				lblEnfermedad.setHorizontalAlignment(SwingConstants.TRAILING);
				lblEnfermedad.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblEnfermedad.setBounds(0, 256, 128, 16);
				addPatientsPanel.add(lblEnfermedad);
				
				textEnfermedad = new HintTextField("Enfermedad");
				textEnfermedad.setColumns(10);
				textEnfermedad.setBounds(145, 251, 116, 22);
				addPatientsPanel.add(textEnfermedad);
				jcbNurse.setBounds(392, 86, 213, 20);
				addPatientsPanel.add(jcbNurse);
				
				JLabel lblMedicina = new JLabel("Medicina");
				lblMedicina.setHorizontalAlignment(SwingConstants.TRAILING);
				lblMedicina.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblMedicina.setBounds(0, 296, 128, 16);
				addPatientsPanel.add(lblMedicina);
				
				textU = new HintTextField("Unidades");
				textU.setColumns(10);
				textU.setBounds(145, 328, 116, 22);
				addPatientsPanel.add(textU);
				
				JLabel lblUnidades_1 = new JLabel("Unidades");
				lblUnidades_1.setHorizontalAlignment(SwingConstants.TRAILING);
				lblUnidades_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblUnidades_1.setBounds(0, 333, 128, 16);
				addPatientsPanel.add(lblUnidades_1);
		
		
		//panel resumen semana
				week = new JPanel();
				week.setLayout(null);
				week.setForeground(Color.WHITE);
				week.setBackground(Color.WHITE);
				week.setBounds(278, 0, 691, 496);
				week.setPreferredSize(new Dimension(630, 700));
				getContentPane().add(week);
				
				
				JLabel lblPacientesDadosDe = new JLabel("Pacientes dados de alta:");
				lblPacientesDadosDe.setHorizontalAlignment(SwingConstants.TRAILING);
				lblPacientesDadosDe.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblPacientesDadosDe.setBounds(0, 56, 307, 39);
				week.add(lblPacientesDadosDe);
				
					JLabel lblPacientesNuevosEsta = new JLabel("Pacientes nuevos:");
					lblPacientesNuevosEsta.setHorizontalAlignment(SwingConstants.TRAILING);
					lblPacientesNuevosEsta.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
					lblPacientesNuevosEsta.setBounds(0, 141, 307, 32);
					week.add(lblPacientesNuevosEsta);
					
					JLabel lblTotalMedicamentosUsados = new JLabel("Total medicamentos usados:");
					lblTotalMedicamentosUsados.setHorizontalAlignment(SwingConstants.TRAILING);
					lblTotalMedicamentosUsados.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
					lblTotalMedicamentosUsados.setBounds(0, 219, 307, 32);
					week.add(lblTotalMedicamentosUsados);
			
				JLabel iconLabel1 = new JLabel("New label");
				iconLabel1.setBounds(39, 249, 258, 201);
				week.add(iconLabel1);
				iconLabel1.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
				getContentPane().add(week);
				
				JLabel iconLabel2 = new JLabel("New label");
				iconLabel2.setBounds(360, 56, 258, 310);
				week.add(iconLabel2);
				iconLabel2.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/fondo.jpg")));
				getContentPane().add(week);
		
		
	
		//panel asignar medicamento
		
		addMedicine=new JPanel();
		addMedicine.setBackground(Color.WHITE);
		addMedicine.setBounds(278, 0, 691, 496);
		getContentPane().add(addMedicine);
		addMedicine.setLayout(null);
		addMedicine.setVisible(false);
		
		JLabel medicina = new JLabel("DNI");
		medicina.setHorizontalAlignment(SwingConstants.TRAILING);
		medicina.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		medicina.setBounds(0, 69, 110, 20);
		addMedicine.add(medicina);
		
		DNIM = new HintTextField("DNI");
		DNIM.setBounds(125, 69, 138, 20);
		addMedicine.add(DNIM);
		DNIM.setColumns(10);
		
		JLabel lblNewLabel3 = new JLabel("Introducir el DNI y medicamento");
		lblNewLabel3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel3.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel3.setBounds(52, 23, 293, 20);
		addMedicine.add(lblNewLabel3);
		
		lblError2 = new JLabel("");
		lblError2.setForeground(Color.RED);
		lblError2.setBounds(275, 90, 337, 80);
		addMedicine.add(lblError2);
		
		JButton btnAsignar = new RoundedJButton(15);
		btnAsignar.setText("Asignar");
		btnAsignar.setBounds(385, 341, 169, 59);
		btnAsignar.setOpaque(false);
		btnAsignar.setBackground(Color.WHITE);
		addMedicine.add(btnAsignar);
		
		units = new HintTextField("Unidades");
		units.setColumns(10);
		units.setBounds(125, 116, 138, 20);
		addMedicine.add(units);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setHorizontalAlignment(SwingConstants.TRAILING);
		lblUnidades.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblUnidades.setBounds(0, 118, 110, 16);
		addMedicine.add(lblUnidades);
		btnAsignar.addActionListener(listener);
		
		
		JLabel label_3 = new JLabel("Medicina");
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_3.setBounds(0, 169, 110, 16);
		addMedicine.add(label_3);
		
		jcbMedicine = new JComboBox();
		jcbMedicine.setBounds(125, 168, 138, 20);
		addMedicine.add(jcbMedicine);
		listener.filJComboBoxMedicines(jcbMedicine);
		getContentPane().add(seePacientsPanel);

		
		
		//dar de alta pacientes
		
		deletePatientsPanel = new JPanel();
		deletePatientsPanel.setBackground(Color.WHITE);
		deletePatientsPanel.setBounds(278, 0, 691, 496);
		getContentPane().add(deletePatientsPanel);
		deletePatientsPanel.setLayout(null);
		deletePatientsPanel.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_1.setBounds(0, 69, 115, 20);
		deletePatientsPanel.add(lblNewLabel_1);

		textFieldDNIToDelete = new HintTextField("DNI");
		textFieldDNIToDelete.setBounds(125, 69, 138, 20);
		deletePatientsPanel.add(textFieldDNIToDelete);
		textFieldDNIToDelete.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_2.setBounds(0, 119, 115, 14);
		deletePatientsPanel.add(lblNewLabel_2);

		
		textFieldNameToDelete = new HintTextField("Nombre");
		textFieldNameToDelete.setColumns(10);
		textFieldNameToDelete.setBounds(125, 119, 138, 20);
		deletePatientsPanel.add(textFieldNameToDelete);

		textFieldFirstDeleteToDelete = new HintTextField("Apellido1");
		textFieldFirstDeleteToDelete.setColumns(10);
		textFieldFirstDeleteToDelete.setBounds(125, 166, 138, 20);
		deletePatientsPanel.add(textFieldFirstDeleteToDelete);

		textFieldSecondDeleteToDelete = new HintTextField("Apellido2");
		textFieldSecondDeleteToDelete.setColumns(10);
		textFieldSecondDeleteToDelete.setBounds(125, 215, 138, 20);
		deletePatientsPanel.add(textFieldSecondDeleteToDelete);

		JLabel lblApellido_3 = new JLabel("Apellido 1");
		lblApellido_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_3.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_3.setBounds(0, 168, 115, 14);
		deletePatientsPanel.add(lblApellido_3);

		JLabel lblapellido = new JLabel("Apellido 2");
		lblapellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblapellido.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblapellido.setBounds(0, 220, 115, 14);
		deletePatientsPanel.add(lblapellido);

		JLabel lblNewLabel_3 = new JLabel("Introducir los siguientes datos");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel_3.setBounds(52, 23, 293, 20);
		deletePatientsPanel.add(lblNewLabel_3);

		JButton btnDelete = new RoundedJButton(15);
		btnDelete.setText("Borrar");
		btnDelete.addActionListener(listener);
		btnDelete.setBounds(285, 244, 138, 20);
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setOpaque(false);
		deletePatientsPanel.add(btnDelete);

		 lblErrorDelete = new JLabel("");
		lblErrorDelete.setForeground(Color.RED);
		lblErrorDelete.setBounds(433, 247, 165, 14);
		deletePatientsPanel.add(lblErrorDelete);
		
		
		
		//panel buscar
		getPatientPane = new JPanel();
		getPatientPane.setBounds(278, 0, 691, 496);
		getContentPane().add(getPatientPane);
		getPatientPane.setLayout(null);
		getPatientPane.setForeground(Color.WHITE);
		getPatientPane.setBackground(Color.WHITE);
		getPatientPane.setVisible(false);
		
	    JLabel label = new JLabel("Nombre");
	    label.setHorizontalAlignment(SwingConstants.TRAILING);
	    label.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
	    label.setBounds(0, 74, 85, 23);
	    getPatientPane.add(label);
	    
	    lblErrorGetPatient = new JLabel("");
	    lblErrorGetPatient.setForeground(Color.RED);
	    lblErrorGetPatient.setBounds(10, 11, 200, 23);
	    getPatientPane.add(lblErrorGetPatient);
	    
	    		
	    JLabel lblApellido1 = new JLabel("Apellido 1");
	    lblApellido1.setHorizontalAlignment(SwingConstants.TRAILING);
	    lblApellido1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
	    lblApellido1.setBounds(0, 124, 91, 23);
	    getPatientPane.add(lblApellido1);
	    
		textFieldNameGetPatient = new RoundedJLabel();
		textFieldNameGetPatient.setEnabled(false);
		textFieldNameGetPatient.setBounds(100, 77, 143, 20);
		getPatientPane.add(textFieldNameGetPatient);
		
		textFieldSurname1GetPatient = new RoundedJLabel();
		textFieldSurname1GetPatient.setEnabled(false);
		textFieldSurname1GetPatient.setBounds(100, 128, 143, 20);
		getPatientPane.add(textFieldSurname1GetPatient);
		
		JLabel label_2 = new JLabel("NIF/NIE");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_2.setBounds(0, 217, 85, 23);
		getPatientPane.add(label_2);
		
		textFieldDNIGetPatient = new RoundedJLabel();
		textFieldDNIGetPatient.setEnabled(false);
		textFieldDNIGetPatient.setBounds(100, 220, 143, 20);
		getPatientPane.add(textFieldDNIGetPatient);
		
			
		
	    JLabel lblApellido_2 = new JLabel("Apellido 2");
	    lblApellido_2.setHorizontalAlignment(SwingConstants.TRAILING);
	    lblApellido_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));	  
	    lblApellido_2.setBounds(0, 171, 91, 23);
	    getPatientPane.add(lblApellido_2);
	    
		textFieldSurname2GetPatient = new RoundedJLabel();
		textFieldSurname2GetPatient.setEnabled(false);
		textFieldSurname2GetPatient.setBounds(100, 173, 143, 20);
		getPatientPane.add(textFieldSurname2GetPatient);
		
		
		JLabel lblRoom = new JLabel("Habitacion");
		lblRoom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoom.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));	
		lblRoom.setBounds(358, 74, 97, 23);
		getPatientPane.add(lblRoom);
		
		textFieldRoomGetPatient = new RoundedJLabel();
		textFieldRoomGetPatient.setEnabled(false);
		textFieldRoomGetPatient.setBounds(470, 77, 143, 20);
		getPatientPane.add(textFieldRoomGetPatient);
		
		
		JLabel label_9 = new JLabel("");
		label_9.setForeground(Color.RED);
		label_9.setBounds(374, 74, 97, 23);
		getPatientPane.add(label_9);
		
				
		
		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 52, 610, 33);
		getPatientPane.add(separator2);
		
		JLabel lblNewLabel = new JLabel("Introduce el DNI: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel.setBounds(0, 11, 200, 23);
		getPatientPane.add(lblNewLabel);
		
        textFieldSearchDNIGetPatient = new HintTextField("DNI o Habitaci�n");
		textFieldSearchDNIGetPatient.setHorizontalAlignment(SwingConstants.CENTER);
        textFieldSearchDNIGetPatient.setBounds(210, 12, 133, 20);
        getPatientPane.add(textFieldSearchDNIGetPatient);
        textFieldSearchDNIGetPatient.setColumns(10);
        
        JButton btnNewButton_1 = new RoundedJButton(15);
        btnNewButton_1.setText("Buscar");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.addActionListener(listener);
		
		btnNewButton_1.setBounds(353, 11, 89, 23);
		getPatientPane.add(btnNewButton_1);
		
		lblErrorGetPatient = new JLabel("");
		lblErrorGetPatient.setForeground(Color.RED);
		lblErrorGetPatient.setBounds(358, 15, 255, 14);
		getPatientPane.add(lblErrorGetPatient);
				
		
	}


public class RoundedJButton extends JButton  {
	//Boton redondeado
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
		//JText Field con bordes redondeados, y nombre de fondo, pasar como arguemnto el texto de "pista"
	  private final String hint;
	  private boolean showingHint;
	  private Shape shape;


	  public HintTextField(final String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    super.addFocusListener(this);
        setOpaque(false); // As suggested by @AVD in comment.
        setBackground(new Color(228,230,230));
		setForeground(new Color(150,150,150));
		setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 11));

    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(Color.BLACK);
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 10, 10);
         }
         return shape.contains(x, y);
    }

	  @Override
	  public void focusGained(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText("");
	      setBorder(BorderFactory.createLineBorder(Color.black,1));
	      setForeground(Color.BLACK);
	      showingHint = false;
	    }
	  }
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      setForeground(new Color(150,150,150));
	      showingHint = true;
	    }
	  }

	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }
	}

public class RoundedJLabel extends JLabel {
//LABEL COLOR GRIS
	  private Shape shape;
	  public RoundedJLabel() {
        setOpaque(false); // As suggested by @AVD in comment.
        setBackground(new Color(228,230,230));
		setForeground(Color.BLACK);
		setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 11));

    }
    protected void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
         super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
         g.setColor(getBackground());
         g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 10, 10);
    }
    public boolean contains(int x, int y) {
         if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 10, 10);
         }
         return shape.contains(x, y);
    }

}


}


