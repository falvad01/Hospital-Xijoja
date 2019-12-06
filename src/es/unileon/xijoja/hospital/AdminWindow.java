package es.unileon.xijoja.hospital;

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
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JProgressBar;

//TODO establecer limites de empleados y pacientes

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	Logs archivo = new Logs(); // Instancia de la clase para utilizar sus metodos

	private JPanel seeEmployeesPanel;
	private JPanel addEmployeePane;

	Toolkit screen;

	private JTextField textFieldName;
	private JTextField textFieldSurname1;
	private JTextField textFieldSurname2;
	private JTextField textFieldNIFNIE;
	private JTextField textFieldBankAccount;
	private JTextField textFieldEmail;

	private JLabel lblUser;
	private JLabel lblPassword;
	private JLabel lblError;

	private PersonalDAO dao;

	JComboBox<Object> comboBoxJob;

	private JButton btnVerPlantilla;

	private JProgressBar totalProgressBar;
	private JProgressBar nursePogressBar;
	private JProgressBar SecreProgressBar;
	private JProgressBar mediProgressBar;
	private JProgressBar AdminprogressBar;
	
	private int numAdmin;
	private int numDoc;
	private int numNurse;
	private int numSecre;

	public AdminWindow() {

		screen = Toolkit.getDefaultToolkit();

		setBounds(1024 / 4, 768 / 10, 969, 496);

		setUndecorated(true);

		setTitle("Administrador");

		try {
			dao = new PersonalDAO();
			initComponents();
			initComponentsAddEmployeePanel();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void initComponents() {

		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);

		listener list = new listener();

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

		JButton btnNewButton = new JButton("Añadir trabajador");
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setBounds(28, 33, 234, 57);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(list);

		btnVerPlantilla = new JButton("Ver plantilla");
		btnVerPlantilla.setBackground(Color.WHITE);
		btnVerPlantilla.setOpaque(false);
		btnVerPlantilla.setBounds(28, 113, 234, 57);
		getContentPane().add(btnVerPlantilla);
		btnVerPlantilla.addActionListener(list);

		JButton btnFireEmployee = new JButton("Despedir trabajador");
		btnFireEmployee.setBackground(Color.WHITE);
		btnFireEmployee.setOpaque(false);
		btnFireEmployee.setBounds(28, 191, 234, 57);
		getContentPane().add(btnFireEmployee);
		btnFireEmployee.addActionListener(list);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(274, 11, 24, 474);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 21, 61, 41);
		getContentPane().add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(20, 11, 117, 41);
		getContentPane().add(separator_2);

		totalProgressBar = new JProgressBar();
		totalProgressBar.setForeground(Color.GREEN);
		totalProgressBar.setMaximum(30);
		totalProgressBar.setBounds(116, 376, 146, 14);
		getContentPane().add(totalProgressBar);
		totalProgressBar.setValue(dao.getLastID());

		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(28, 376, 46, 14);
		getContentPane().add(lblTotal);

	}

	private void initComponentsAddEmployeePanel() throws SQLException {

		listener list = new listener();

		seeEmployeesPanel = new JPanel();

		seeEmployeesPanel.setLayout(null);
		seeEmployeesPanel.setForeground(Color.WHITE);
		seeEmployeesPanel.setBackground(Color.WHITE);
		seeEmployeesPanel.setBounds(284, 11, 624, 450);
		seeEmployeesPanel.setPreferredSize(new Dimension(630, 700));
		seeEmployeesPanel.setVisible(false);

		addEmployeePane = new JPanel();
		addEmployeePane.setForeground(Color.WHITE);
		addEmployeePane.setBackground(Color.WHITE);
		addEmployeePane.setBounds(284, 11, 630, 450);
		getContentPane().add(addEmployeePane);
		addEmployeePane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 27, 63, 23);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblNombre);

		JLabel lblApellidos = new JLabel("1º Apellido");
		lblApellidos.setBounds(10, 78, 80, 23);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblApellidos);

		textFieldName = new JTextField();
		textFieldName.setBounds(100, 30, 143, 20);
		addEmployeePane.add(textFieldName);
		textFieldName.setColumns(10);

		textFieldSurname1 = new JTextField();
		textFieldSurname1.setBounds(100, 81, 143, 20);
		addEmployeePane.add(textFieldSurname1);
		textFieldSurname1.setColumns(10);

		JLabel lblNifnie = new JLabel("NIF/NIE");
		lblNifnie.setBounds(10, 170, 63, 23);
		lblNifnie.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblNifnie);

		textFieldNIFNIE = new JTextField();
		textFieldNIFNIE.setBounds(100, 173, 143, 20);
		textFieldNIFNIE.setColumns(10);
		addEmployeePane.add(textFieldNIFNIE);

		JLabel lblCuentaBancaria = new JLabel("Cuenta bancaria");
		lblCuentaBancaria.setBounds(10, 215, 120, 23);
		lblCuentaBancaria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblCuentaBancaria);

		textFieldBankAccount = new JTextField();
		textFieldBankAccount.setBounds(132, 218, 143, 20);
		textFieldBankAccount.setColumns(10);
		addEmployeePane.add(textFieldBankAccount);

		JLabel lblPuesto = new JLabel("Puesto");
		lblPuesto.setBounds(390, 27, 63, 23);
		lblPuesto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblPuesto);

		comboBoxJob = new JComboBox<Object>();
		comboBoxJob.setBounds(450, 29, 133, 23);
		comboBoxJob.setBackground(Color.WHITE);
		comboBoxJob.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Administrador\t", "Medico", "Enfermero", "Secretario" }));
		comboBoxJob.setOpaque(false);
		addEmployeePane.add(comboBoxJob);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(390, 78, 63, 23);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblEmail);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(450, 81, 133, 20);
		textFieldEmail.setColumns(10);
		addEmployeePane.add(textFieldEmail);

		lblUser = new JLabel("");
		lblUser.setBorder(new TitledBorder("Usuario"));
		lblUser.setBounds(390, 123, 193, 41);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblUser);

		lblPassword = new JLabel("");
		lblPassword.setBorder(new TitledBorder("Constraseña"));
		lblPassword.setBounds(390, 178, 193, 41);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblPassword);

		JButton btnRegister = new JButton("Registrar");
		btnRegister.setBounds(390, 260, 212, 47);
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setOpaque(false);
		addEmployeePane.add(btnRegister);
		btnRegister.addActionListener(list);

		JLabel lblApellido = new JLabel("2º Apellido");
		lblApellido.setBounds(10, 123, 80, 23);
		lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addEmployeePane.add(lblApellido);

		textFieldSurname2 = new JTextField();
		textFieldSurname2.setBounds(100, 126, 143, 20);
		textFieldSurname2.setColumns(10);
		addEmployeePane.add(textFieldSurname2);

		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setBounds(390, 241, 212, 14);
		addEmployeePane.add(lblError);

		JLabel iconLabel = new JLabel("New label");
		iconLabel.setBounds(43, 245, 252, 194);
		addEmployeePane.add(iconLabel);
		iconLabel.setIcon(new ImageIcon(AdminWindow.class.getResource("/resources/iconAdmin.png")));
		getContentPane().add(seeEmployeesPanel);

		SecreProgressBar = new JProgressBar();
		SecreProgressBar.setForeground(Color.GREEN);
		SecreProgressBar.setValue(1);
		SecreProgressBar.setMaximum(5);
		SecreProgressBar.setBounds(116, 342, 146, 14);
		getContentPane().add(SecreProgressBar);

		nursePogressBar = new JProgressBar();
		nursePogressBar.setForeground(Color.GREEN);
		nursePogressBar.setValue(1);
		nursePogressBar.setMaximum(13);
		nursePogressBar.setBounds(116, 321, 146, 14);
		getContentPane().add(nursePogressBar);

		mediProgressBar = new JProgressBar();
		mediProgressBar.setForeground(Color.GREEN);
		mediProgressBar.setValue(1);
		mediProgressBar.setMaximum(10);
		mediProgressBar.setBounds(116, 299, 146, 14);
		getContentPane().add(mediProgressBar);

		AdminprogressBar = new JProgressBar();
		AdminprogressBar.setForeground(Color.GREEN);
		AdminprogressBar.setValue(1);
		AdminprogressBar.setMaximum(2);
		AdminprogressBar.setBounds(116, 276, 146, 14);
		getContentPane().add(AdminprogressBar);

		JLabel lblAdministrador = new JLabel("Administrador");
		lblAdministrador.setBounds(28, 276, 78, 14);
		getContentPane().add(lblAdministrador);

		JLabel lblMedico = new JLabel("Medico");
		lblMedico.setBounds(28, 299, 46, 14);
		getContentPane().add(lblMedico);

		JLabel lblEnfermero = new JLabel("Enfermero");
		lblEnfermero.setBounds(28, 321, 61, 14);
		getContentPane().add(lblEnfermero);

		JLabel lblSecretario = new JLabel("Secretario");
		lblSecretario.setBounds(28, 342, 61, 14);
		getContentPane().add(lblSecretario);

	}

	/**
	 * Metodo para generar una contraseña aleatoria
	 * 
	 * @return
	 */
	private String randomPassword() {

		String alphabet = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder password = new StringBuilder();

		int i = 0;
		while (i < 7) {
			int rand = (int) ((Math.random() * ((61 - 0) + 1)));

			password.append(alphabet.charAt(rand));

			i++;
		}

		return password.toString();
	}
	
	private void setNumberEmployees() throws SQLException {
		
		String [] jobs = dao.getJobsEmployees();
		
		for (int i = 0; i < jobs.length; i++) {
			
			if(jobs[i].equals("Admnistrador")) {
				numAdmin++;
			}else if(jobs[i].equals("Medico")) {
				numDoc++;
			}else if(jobs[i].equals("Enfermero")){
				numNurse++;
			}else if(jobs[i].equals("Secretario")){
				numSecre++;
			}//TODO programando por aqui
			
			
		}
		
		
		
	}

	private void genUserAndPass() {

		StringBuilder sbName = new StringBuilder();// Formamos el nombre de usuario

		sbName.append(textFieldName.getText().charAt(0));// Primera letra del nombre

		sbName.append(textFieldSurname1.getText().charAt(0));
		sbName.append(textFieldSurname1.getText().charAt(1));// Dos primeras letras del primer apellido
		sbName.append(textFieldSurname2.getText().charAt(0));
		sbName.append(textFieldSurname2.getText().charAt(1));// Dos primeras letras del segundo apellido

		String[] names = null;

		try {
			names = dao.getNamesEmployees();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		int numberOfUser = 0;
		for (int i = 1; i < names.length; i++) {

			char[] nameBUffer = names[i].toCharArray();
			char[] secondBuffer = new char[5];

			for (int j = 0; j < 5; j++) {
				secondBuffer[j] = nameBUffer[j];// Quitamos los numero del nombre de usuario
			}

			if (String.valueOf(secondBuffer).equals(sbName.toString().toLowerCase())) {

				numberOfUser++;// Contamos los ususarios con el mismo nombre y aniadimos un numero para que no
								// se repita

			}

		}

		sbName.append(numberOfUser);// Aniadimos el numero

		lblUser.setText(sbName.toString().toLowerCase());
		lblPassword.setText(randomPassword());// Generamos la contraseña aleatoriamente
	}

	/**
	 * 
	 * @author xijoja
	 *
	 */
	public class listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (arg0.getActionCommand().equals("Registrar")) {////////////////////////////////// REGISTRAR

				boolean add = true;

				if ((textFieldName.getText().equals("")) || (textFieldSurname1.getText().equals(""))
						|| (textFieldSurname2.getText().equals("")) || (textFieldNIFNIE.getText().equals(""))
						|| (textFieldBankAccount.getText().equals("")) || (textFieldEmail.getText().equals(""))) {// Comprobamos
					// si algum
					// campo esta
					// vacio

					add = false;
					lblError.setText("Hay campos vacios");
				} else {
					lblError.setText("");
				}

				if (add) {// Si da error no se añade el empleado
					System.out.println("Correcto");
					genUserAndPass();
					int id = dao.getLastID();

					Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual

					try {

						dao.addEmployee(id, textFieldName.getText(), textFieldSurname1.getText(),
								textFieldSurname2.getText(), textFieldNIFNIE.getText(), date,
								textFieldBankAccount.getText(), comboBoxJob.getSelectedItem().toString(),
								lblPassword.getText(), lblUser.getText(), textFieldEmail.getText());// LLamamos a la
																									// funcion del DAO
																									// que inserta el
																									// empleado
					} catch (SQLException e1) {

						e1.printStackTrace();
					}

					totalProgressBar.setValue(dao.getLastID());
				}

			} else if (arg0.getActionCommand().equals("Añadir trabajador")) {///////////////////////////////// ADD

				seeEmployeesPanel.setVisible(false);
				addEmployeePane.setVisible(true);
				btnVerPlantilla.setText("Ver plantilla");

			} else if ((arg0.getActionCommand().equals("Ver plantilla"))
					|| (arg0.getActionCommand().equals("Recargar"))) {

				seeEmployeesPanel.setVisible(true);
				addEmployeePane.setVisible(false);
				btnVerPlantilla.setText("Recargar");

				ArrayList<String[]> insert = null;

				String[] titles = null;

				String[][] matrixToInsert = null;

				try {
					titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "Fecha",
							"Cuenta Bancaria", "Puesto", "Contraseña", "Usuario", "Email" }; // Titulos de la tabla de
																								// los empleados
					insert = dao.getAllEmployees();// ArrayList de Arrays
					System.out.println("Size " + insert.size());
					matrixToInsert = new String[insert.size() + 1][11];

					for (int i = 0; i < insert.size(); i++) { // rellenamos la matriz que meteremos en la tabla a partir
																// del ArrayList de arrays devuelto del DAO
						for (int j = 0; j < 11; j++) {
							if (i == 0) {

								matrixToInsert[i][j] = titles[j];

							} else {
								matrixToInsert[i][j] = insert.get(i)[j];
							}
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				}

				JTable employeesTable = new JTable();
				employeesTable.setBounds(20, 20, 600, 456);
				seeEmployeesPanel.add(employeesTable);
				System.out.println("VIVA DROTIUM");
				DefaultTableModel tableModel = new DefaultTableModel(matrixToInsert, titles);
				employeesTable.setModel(tableModel);

			} else if (arg0.getActionCommand().equals("Despedir trabajador")) {

			}
		}
	}
}