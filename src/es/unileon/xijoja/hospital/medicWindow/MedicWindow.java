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


import es.unileon.xijoja.hospital.InfoWindow;
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
	protected JPanel deletePatientsPanel;
	protected JPanel addMedicine;
	protected JPanel getPatientPane;
	protected JPanel week;
	
	private PacientesDAO dao;
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
	protected JTextField textFieldNameGetPatient;
	protected JTextField textFieldSurname1GetPatient;
	protected JTextField textFieldDNIGetPatient;
	protected JTextField textFieldSearchDNIGetPatient;
	protected JTextField textFieldRoomGetPatient;
	protected JTextField textFieldDNIToDelete;
	protected JTextField textFieldNameToDelete;
	protected JTextField textFieldFirstDeleteToDelete;
	protected JTextField textFieldSecondDeleteToDelete;
	protected JLabel lblErrorDelete;
	protected JLabel lblErrorGetPatient;
	protected JTextField textFieldSurname2GetPatient;
	protected JTextField textFieldSearch;
	protected JTextField textFieldDNI;
	protected JTextField textField;
    protected JTextField textField_1;
    protected JTextField units;
    protected JTextField Medicine;
    protected JTextField DNIM;
    protected JTextField textEnfermedad;
    protected JButton button;


   

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
		btnAsignarMedicamentoPaciente.addActionListener(listener);
		
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
		btnDarAltaPaciente.addActionListener(listener);
		
		JButton btnIngresarPaciente = new JButton("Ingresar Paciente");
		btnIngresarPaciente.setOpaque(false);
		btnIngresarPaciente.setBackground(new Color(255, 255, 255));
		btnIngresarPaciente.setBounds(10, 21, 229, 52);
		getContentPane().add(btnIngresarPaciente);
		btnIngresarPaciente.addActionListener(listener);
		
		JButton btnCerrarSesin = new JButton("Cerrar Sesión");
		btnCerrarSesin.setBounds(10, 416, 229, 41);
		btnCerrarSesin.setOpaque(false);
		btnCerrarSesin.setBackground(new Color(255, 255, 255));
		getContentPane().add(btnCerrarSesin);
		btnCerrarSesin.addActionListener(listener);
		
		JButton btnNewButton = new JButton("Buscar Paciente");
		btnNewButton.setBounds(10, 317, 229, 46);
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
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
		iconLabel.setBounds(355, 127, 250, 222);
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
				lberror.setBounds(50, 241, 260, 150);
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
				lblMedico.setBounds(307, 47, 56, 16);
				addPatientsPanel.add(lblMedico);
				
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
				jcbNurse.setBounds(392, 86, 213, 20);
				addPatientsPanel.add(jcbNurse);
		
		//panel resumen semana
		week = new JPanel();
		week.setLayout(null);
		week.setForeground(Color.WHITE);
		week.setBackground(Color.WHITE);
		week.setBounds(278, 11, 630, 450);
		week.setPreferredSize(new Dimension(630, 700));
		getContentPane().add(week);
		week.setVisible(true);
		getContentPane().add(seePacientsPanel);
		
		
		
	
		//panel asignar medicamento
		
		addMedicine=new JPanel();
		addMedicine.setBackground(Color.WHITE);
		addMedicine.setBounds(274, 0, 649, 467);
		getContentPane().add(addMedicine);
		addMedicine.setLayout(null);
		addMedicine.setVisible(false);
		
		JLabel medicina = new JLabel("DNI");
		medicina.setBounds(39, 69, 46, 20);
		addMedicine.add(medicina);
		
		DNIM = new JTextField();
		DNIM.setBounds(125, 69, 138, 20);
		addMedicine.add(DNIM);
		DNIM.setColumns(10);
		
		JLabel lblNewLabel3 = new JLabel("Introducir el DNI del paciente y el medicamento que desea asignarle");
		lblNewLabel3.setBounds(39, 23, 451, 20);
		addMedicine.add(lblNewLabel3);
		
		JLabel lblMedicamento = new JLabel("Medicamento");
		lblMedicamento.setBounds(39, 127, 91, 16);
		addMedicine.add(lblMedicamento);
		
		Medicine = new JTextField();
		Medicine.setBounds(125, 124, 138, 22);
		addMedicine.add(Medicine);
		Medicine.setColumns(10);
		
		lblError2 = new JLabel("");
		lblError2.setForeground(Color.RED);
		lblError2.setBounds(358, 90, 200, 80);
		addMedicine.add(lblError2);
		
		JButton btnAsignar = new JButton("Asignar");
		btnAsignar.setBounds(385, 341, 169, 59);
		btnAsignar.setOpaque(false);
		btnAsignar.setBackground(new Color(255, 255, 255));
		addMedicine.add(btnAsignar);
		
		units = new JTextField();
		units.setColumns(10);
		units.setBounds(125, 182, 138, 22);
		addMedicine.add(units);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(39, 185, 56, 16);
		addMedicine.add(lblUnidades);
		btnAsignar.addActionListener(listener);
		
		
		JLabel label_3 = new JLabel("Medicamento");
		label_3.setBounds(39, 235, 91, 16);
		addMedicine.add(label_3);
		
		jcbMedicine = new JComboBox();
		listener.filJComboBox2(jcbMedicine);

		

		
		
		/*
		JLabel lblMedicamentos = new JLabel("Medicamentos");
		lblMedicamentos.setBounds(32, 298, 116, 16);
		addPatientsPanel.add(lblMedicamentos);
		
		Medicamentos = new JTextField();
		Medicamentos.setBounds(145, 295, 116, 22);
		addPatientsPanel.add(Medicamentos);
		Medicamentos.setColumns(10);
		
		
		*/
		
		
		//dar de alta pacientes
		
		deletePatientsPanel = new JPanel();
		deletePatientsPanel.setBackground(Color.WHITE);
		deletePatientsPanel.setBounds(274, 0, 649, 467);
		getContentPane().add(deletePatientsPanel);
		deletePatientsPanel.setLayout(null);
		deletePatientsPanel.setVisible(false);

		JLabel lblNewLabel_1 = new JLabel("DNI");
		lblNewLabel_1.setBounds(39, 69, 46, 20);
		deletePatientsPanel.add(lblNewLabel_1);

		textFieldDNIToDelete = new JTextField();
		textFieldDNIToDelete.setBounds(125, 69, 138, 20);
		deletePatientsPanel.add(textFieldDNIToDelete);
		textFieldDNIToDelete.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Nombre");
		lblNewLabel_2.setBounds(39, 119, 46, 14);
		deletePatientsPanel.add(lblNewLabel_2);

		textFieldNameToDelete = new JTextField();
		textFieldNameToDelete.setColumns(10);
		textFieldNameToDelete.setBounds(125, 119, 138, 20);
		deletePatientsPanel.add(textFieldNameToDelete);

		textFieldFirstDeleteToDelete = new JTextField();
		textFieldFirstDeleteToDelete.setColumns(10);
		textFieldFirstDeleteToDelete.setBounds(125, 166, 138, 20);
		deletePatientsPanel.add(textFieldFirstDeleteToDelete);

		textFieldSecondDeleteToDelete = new JTextField();
		textFieldSecondDeleteToDelete.setColumns(10);
		textFieldSecondDeleteToDelete.setBounds(125, 215, 138, 20);
		deletePatientsPanel.add(textFieldSecondDeleteToDelete);

		JLabel lblApellido_3 = new JLabel("1º Apellido");
		lblApellido_3.setBounds(39, 169, 60, 14);
		deletePatientsPanel.add(lblApellido_3);

		JLabel lblapellido = new JLabel("2ºApellido");
		lblapellido.setBounds(39, 218, 60, 14);
		deletePatientsPanel.add(lblapellido);

		JLabel lblNewLabel_3 = new JLabel("Introducir los siguientes datos para borrar");
		lblNewLabel_3.setBounds(39, 23, 275, 20);
		deletePatientsPanel.add(lblNewLabel_3);

		JButton btnDelete = new JButton("Borrar");
		btnDelete.addActionListener(listener);
		btnDelete.setBounds(285, 244, 138, 20);
		btnDelete.setOpaque(false);
		btnDelete.setBackground(new Color(255, 255, 255));
		deletePatientsPanel.add(btnDelete);

		 lblErrorDelete = new JLabel("");
		lblErrorDelete.setForeground(Color.RED);
		lblErrorDelete.setBounds(433, 247, 165, 14);
		deletePatientsPanel.add(lblErrorDelete);
		
		
		
		//panel buscar
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
	    
	    lblErrorGetPatient = new JLabel("");
	    lblErrorGetPatient.setForeground(Color.RED);
	    lblErrorGetPatient.setBounds(10, 11, 200, 23);
	    getPatientPane.add(lblErrorGetPatient);
	    
	    		
	    JLabel lblApellido1 = new JLabel("1� Apellido");
	    lblApellido1.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblApellido1.setBounds(10, 125, 80, 23);
	    getPatientPane.add(lblApellido1);
	    
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
				
		
	}
}
