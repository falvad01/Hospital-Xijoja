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
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.admin.AdminWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;

@SuppressWarnings("serial")
public class SecretaryWindow extends JFrame {

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	private PacientesDAO dao;
	protected Color colorBorder;
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
	protected JLabel labelFieldNameGetPatient;
	protected JLabel labelFieldSurname1GetPatient;
	protected JLabel labelFieldDNIGetPatient;
	protected JTextField textFieldSearchDNIGetPatient;
	protected JLabel labelFieldRoomGetPatient;
	protected JTextField textFielddisease;
	protected JLabel lblErrorGetPatient;
	protected JButton button;

	protected JLabel labelFieldSurname2GetPatient;

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
		btnNewButton.setText("Añadir Paciente");
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

		////////////////////////////////////////////////////////////// Aï¿½adir pacientes

		addPatientPane = new JPanel();
		addPatientPane.setForeground(Color.WHITE);
		addPatientPane.setBackground(Color.WHITE);
		addPatientPane.setBounds(274, 0, 695, 496);
		getContentPane().add(addPatientPane);
		addPatientPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombre.setBounds(28, 27, 121, 23);
		lblNombre.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblNombre);

		JLabel lblApellidos = new JLabel("1º Apellido");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setBounds(28, 78, 121, 23);
		lblApellidos.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblApellidos);

		textFieldName = new HintTextField("Introduce el Nombre");
		textFieldName.setBounds(159, 30, 143, 25);
		addPatientPane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname1 = new HintTextField("Introduce el Apellido");
		textFieldSurname1.setBounds(159, 81, 143, 25);
		addPatientPane.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNifnie.setBounds(28, 170, 121, 23);
		lblNifnie.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblNifnie);

		textFieldNIFNIE = new HintTextField("Introduce el NIF/NIE");
		textFieldNIFNIE.setBounds(159, 173, 143, 25);
		textFieldNIFNIE.setColumns(10);
		addPatientPane.add(textFieldNIFNIE);

		JLabel lblRoom2 = new JLabel("Habitacion");
		lblRoom2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoom2.setBounds(335, 27, 114, 23);
		lblRoom2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblRoom2);

		
		JButton btnRegister = new RoundedJButton(15);
		btnRegister.setText("Añadir");
		btnRegister.setBounds(320, 260, 212, 47);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		btnRegister.addActionListener(listener);
		addPatientPane.add(btnRegister);
		
		textFieldRoom = new HintTextField(" Nº de habitación");
		textFieldRoom.setBounds(459, 30, 143, 25);
		textFieldRoom.setColumns(10);
		addPatientPane.add(textFieldRoom);
		

		JLabel lblApellido = new JLabel("2º Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido.setBounds(28, 123, 121, 23);
		lblApellido.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblApellido);

		textFieldSurname2 = new HintTextField("Introduce el Apellido");
		textFieldSurname2.setBounds(159, 126, 143, 25);
		addPatientPane.add(textFieldSurname2);

		JLabel lblnurse = new JLabel("Enfermero");
		lblnurse.setHorizontalAlignment(SwingConstants.TRAILING);
		lblnurse.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblnurse.setBounds(335, 78, 114, 23);
		addPatientPane.add(lblnurse);

		jcbNurse = new JComboBox();
		listener.filJComboBox(jcbNurse, false);
		jcbNurse.setBounds(459, 81, 213, 20);
		jcbNurse.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 10));
		jcbNurse.setBackground(new Color(228,230,230));

		addPatientPane.add(jcbNurse);

		JLabel lblMedic = new JLabel("Médico");
		lblMedic.setHorizontalAlignment(SwingConstants.TRAILING);
		lblMedic.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblMedic.setBounds(335, 123, 114, 23);
		addPatientPane.add(lblMedic);

		jcbMedic = new JComboBox();
		listener.filJComboBox(jcbMedic, true);
		jcbMedic.setBounds(459, 128, 213, 25);
		jcbMedic.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 10));
		jcbMedic.setBackground(new Color(228,230,230));


		addPatientPane.add(jcbMedic);

		JLabel lblEnfermedad = new JLabel("Enfermedad");
		lblEnfermedad.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnfermedad.setBounds(335, 170, 114, 23);
		lblEnfermedad.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		addPatientPane.add(lblEnfermedad);

		textFielddisease = new HintTextField("Enfermedad");
		textFielddisease.setBounds(459, 172, 143, 25);
		textFielddisease.setColumns(10);
		addPatientPane.add(textFielddisease);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 10));
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

		JLabel label = new JLabel("Nombre:");
		label.setHorizontalAlignment(SwingConstants.TRAILING);
		label.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label.setBounds(10, 74, 122, 23);
		getPatientPane.add(label);

		JLabel lblApellido_1 = new JLabel("1º Apellido:");
		lblApellido_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_1.setBounds(10, 125, 122, 23);
		getPatientPane.add(lblApellido_1);

		labelFieldNameGetPatient = new RoundedJLabel();
		labelFieldNameGetPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelFieldNameGetPatient.setBounds(142, 77, 143, 20);
		getPatientPane.add(labelFieldNameGetPatient);

		labelFieldSurname1GetPatient = new RoundedJLabel();
		labelFieldSurname1GetPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelFieldSurname1GetPatient.setBounds(142, 128, 143, 20);
		getPatientPane.add(labelFieldSurname1GetPatient);

		JLabel label_2 = new JLabel("NIF/NIE:");
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		label_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_2.setBounds(10, 217, 122, 23);
		getPatientPane.add(label_2);

		labelFieldDNIGetPatient =new RoundedJLabel();
		labelFieldDNIGetPatient.setBounds(142, 220, 143, 20);
		labelFieldDNIGetPatient.setHorizontalAlignment(SwingConstants.CENTER);
		getPatientPane.add(labelFieldDNIGetPatient);

		JLabel lblApellido_2 = new JLabel("2º Apellido:");
		lblApellido_2.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellido_2.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblApellido_2.setBounds(10, 170, 122, 23);
		getPatientPane.add(lblApellido_2);

		labelFieldSurname2GetPatient = new RoundedJLabel();
		labelFieldSurname2GetPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelFieldSurname2GetPatient.setBounds(142, 173, 143, 20);
		getPatientPane.add(labelFieldSurname2GetPatient);

		JLabel lblRoom = new JLabel("Habitación:");
		lblRoom.setHorizontalAlignment(SwingConstants.TRAILING);
		lblRoom.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblRoom.setBounds(332, 74, 128, 23);
		getPatientPane.add(lblRoom);

		labelFieldRoomGetPatient = new RoundedJLabel();
		labelFieldRoomGetPatient.setHorizontalAlignment(SwingConstants.CENTER);
		labelFieldRoomGetPatient.setBounds(470, 77, 143, 20);
		getPatientPane.add(labelFieldRoomGetPatient);

		JLabel label_9 = new JLabel("");
		label_9.setForeground(Color.RED);
		label_9.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		label_9.setBounds(390, 241, 212, 14);
		getPatientPane.add(label_9);

		JSeparator separator2 = new JSeparator();
		separator2.setBounds(10, 52, 610, 33);
		getPatientPane.add(separator2);

		JLabel lblNewLabel = new JLabel("Introduce el DNI o habitación: ");
		lblNewLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
		lblNewLabel.setBounds(10, 11, 275, 23);
		getPatientPane.add(lblNewLabel);

		textFieldSearchDNIGetPatient = new HintTextField("DNI o Habitación");
		textFieldSearchDNIGetPatient.setHorizontalAlignment(SwingConstants.CENTER);
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
