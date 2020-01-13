package es.unileon.xijoja.hospital.nurse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
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
import es.unileon.xijoja.hospital.secretary.SecretaryWindow.RoundedJButton;

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
	protected JLabel labelFieldMedicine;
	protected JLabel labelFieldUnits;
	
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
		try {
		     GraphicsEnvironment ge =   GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("etc/rexlia.ttf")));
		} catch (IOException|FontFormatException e) {
		     //Handle exception
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

		btnVerPlantilla = new RoundedJButton(15);
		btnVerPlantilla.setText("Ver Pacientes");
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 85, 234, 41);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(listener);

		JButton btnEditEmployee = new RoundedJButton(15);
		btnEditEmployee.setText("Usar Medicamento");
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
		
		JButton btnCloseSesion = new RoundedJButton(15);
		btnCloseSesion.setText("Cerrar sesion");
		btnCloseSesion.setBounds(60, 465, 180, 23);
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
				label.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				label.setBounds(10, 74, 190, 23);
				getPatientPane.add(label);

				JLabel lblApellido_1 = new JLabel("Tratamiento restante: ");
				lblApellido_1.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblApellido_1.setBounds(10, 125, 190, 23);
				getPatientPane.add(lblApellido_1);

				labelFieldMedicine = new RoundedJLabel();
				labelFieldMedicine.setFont(new Font("Calibri", Font.BOLD, 15));
				labelFieldMedicine.setEnabled(false);
				labelFieldMedicine.setHorizontalAlignment(SwingConstants.CENTER);
				labelFieldMedicine.setBounds(205, 77, 163, 20);
				getPatientPane.add(labelFieldMedicine);

				labelFieldUnits = new RoundedJLabel();
				labelFieldUnits.setEnabled(false);
				labelFieldUnits.setBounds(205, 128, 163, 20);
				labelFieldUnits.setHorizontalAlignment(SwingConstants.CENTER);

				getPatientPane.add(labelFieldUnits);
				
				JLabel lblSelectUnits = new JLabel("Cuantas unidades a tratar: ");
				lblSelectUnits.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
				lblSelectUnits.setBounds(10, 179, 190, 23);
				getPatientPane.add(lblSelectUnits);
				
				jcbNUtits = new JComboBox();
				jcbNUtits.setBounds(205, 179, 50, 20);
				jcbNUtits.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 12));

				getPatientPane.add(jcbNUtits);

				
				 btnUseMedicine = new RoundedJButton(15);
				btnUseMedicine.setText("Tratamiento");
				btnUseMedicine.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 15));
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
				lblNewLabel.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 12));
				lblNewLabel.setBounds(15, 11, 200, 23);
				getPatientPane.add(lblNewLabel);
				
				jcbPatient = new JComboBox();
				listener.filJComboBox(jcbPatient);
				jcbPatient.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 12));

				jcbPatient.setBounds(175, 12, 343, 20);
				getPatientPane.add(jcbPatient);

				JButton btnNewButton_1 = new RoundedJButton(15);		
				btnNewButton_1.setText("Buscar");
				btnNewButton_1.setForeground(Color.BLACK);
				btnNewButton_1.setBackground(Color.WHITE);
				btnNewButton_1.addActionListener(listener);

				btnNewButton_1.setBounds(528, 11, 89, 23);
				getPatientPane.add(btnNewButton_1);

				lblErrorGetPatient = new JLabel("");
				lblErrorGetPatient.setForeground(Color.RED);
				lblErrorGetPatient.setBounds(378, 240, 273, 14);
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
