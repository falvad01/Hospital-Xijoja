package es.unileon.xijoja.hospital.medicWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MedicWindow extends JFrame {

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	Toolkit screen;
	private Logs log = new Logs();
	protected JPanel icon;
	protected JPanel seePacientsPanel;
	private PacientesDAO dao;
	private ControlerMedicWindow listener;
	protected JTextField textField_3;
	protected JTextField textField_4;
	protected JTextField textField_5;
	protected JTextField textField_6;
	protected JTextField textField_7;
	protected JTextField textField_8;
	protected JTextField textField_9;

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
		btnIngresarPaciente.setBounds(10, 21, 229, 52);
		btnIngresarPaciente.setOpaque(false);
		btnIngresarPaciente.setBackground(new Color(255, 255, 255));
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
		getContentPane().add(seePacientsPanel);
		//panel ingresar paciente
		
		icon = new JPanel();
		icon.setBounds(278, 11, 630, 450);
		getContentPane().add(icon);
		icon.setLayout(null);
		icon.setForeground(Color.WHITE);
		icon.setBackground(Color.WHITE);
		icon.setVisible(true);
		
		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(323, 47, 271, 222);
		icon.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		getContentPane().add(icon);
		
		JButton btnModificarEstadoPaciente = new JButton("Registrar");
		btnModificarEstadoPaciente.setOpaque(false);
		btnModificarEstadoPaciente.setBackground(new Color(255, 255, 255));
		btnModificarEstadoPaciente.setBounds(407, 381, 198, 41);
		icon.add(btnModificarEstadoPaciente);
		
		JLabel label_1 = new JLabel("Nombre Paciente");
		label_1.setBounds(32, 47, 101, 16);
		icon.add(label_1);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(145, 44, 116, 22);
		icon.add(textField_3);
		
		JLabel lblApellido = new JLabel("Apellido 1");
		lblApellido.setBounds(32, 88, 101, 16);
		icon.add(lblApellido);
		
		JLabel lblApellido_1 = new JLabel("Apellido 2");
		lblApellido_1.setBounds(32, 127, 101, 16);
		icon.add(lblApellido_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(145, 85, 116, 22);
		icon.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(145, 124, 116, 22);
		icon.add(textField_5);
		
		JLabel lblNifnie = new JLabel("NIFNIE");
		lblNifnie.setBounds(32, 168, 56, 16);
		icon.add(lblNifnie);
		
		textField_6 = new JTextField();
		textField_6.setBounds(145, 165, 116, 22);
		icon.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblHabitacin = new JLabel("Habitación");
		lblHabitacin.setBounds(32, 209, 89, 16);
		icon.add(lblHabitacin);
		
		textField_7 = new JTextField();
		textField_7.setBounds(145, 206, 116, 22);
		icon.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setBounds(32, 253, 89, 16);
		icon.add(lblEnfermedad);
		
		textField_8 = new JTextField();
		textField_8.setBounds(145, 250, 116, 22);
		icon.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblMedicamentos = new JLabel("Medicamentos");
		lblMedicamentos.setBounds(32, 298, 116, 16);
		icon.add(lblMedicamentos);
		
		textField_9 = new JTextField();
		textField_9.setBounds(145, 295, 116, 22);
		icon.add(textField_9);
		textField_9.setColumns(10);
		
		
		//
		
		
		
	}
}
