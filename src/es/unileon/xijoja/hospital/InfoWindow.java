package es.unileon.xijoja.hospital;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InfoWindow extends JFrame {
	// TODO redactar informacion

	Toolkit screen;

	JPanel generalPanel;
	JPanel medicPanel;
	JPanel nursePanel;
	JPanel secretaryPanel;
	JPanel adminPanel;

	public InfoWindow(String panel) {

		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setBounds(1024 / 4, 768 / 6, 750, 474);
		setTitle("Informacion");
		initComponents();
		if (panel == "general") {
			generalPanel.setVisible(true);
			adminPanel.setVisible(false);
			medicPanel.setVisible(false);
			nursePanel.setVisible(false);
			secretaryPanel.setVisible(false);
		} else if (panel == "admin") {
			generalPanel.setVisible(false);
			adminPanel.setVisible(true);
			medicPanel.setVisible(false);
			nursePanel.setVisible(false);
			secretaryPanel.setVisible(false);
		} else if (panel == "medic") {
			generalPanel.setVisible(false);
			adminPanel.setVisible(false);
			medicPanel.setVisible(true);
			nursePanel.setVisible(false);
			secretaryPanel.setVisible(false);
		} else if (panel == "nurse") {
			generalPanel.setVisible(false);
			adminPanel.setVisible(false);
			medicPanel.setVisible(false);
			nursePanel.setVisible(true);
			secretaryPanel.setVisible(false);
		} else if (panel == "secretary") {
			generalPanel.setVisible(false);
			adminPanel.setVisible(false);
			medicPanel.setVisible(false);
			nursePanel.setVisible(false);
			secretaryPanel.setVisible(true);
		}

		
	}

	private void initComponents() {

		getContentPane().setLayout(null);

		JLabel lblAyuda = new JLabel("AYUDA");
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 47));
		lblAyuda.setBounds(10, 11, 180, 40);
		getContentPane().add(lblAyuda);

		JButton btnNewButton = new JButton("General");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalPanel.setVisible(true);
				adminPanel.setVisible(false);
				medicPanel.setVisible(false);
				nursePanel.setVisible(false);
				secretaryPanel.setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 79, 180, 57);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Administrador");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalPanel.setVisible(false);
				adminPanel.setVisible(true);
				medicPanel.setVisible(false);
				nursePanel.setVisible(false);
				secretaryPanel.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(10, 147, 180, 57);
		getContentPane().add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Medico");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalPanel.setVisible(false);
				adminPanel.setVisible(false);
				medicPanel.setVisible(true);
				nursePanel.setVisible(false);
				secretaryPanel.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(10, 217, 180, 57);
		getContentPane().add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Enfermero");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalPanel.setVisible(false);
				adminPanel.setVisible(false);
				medicPanel.setVisible(false);
				nursePanel.setVisible(true);
				secretaryPanel.setVisible(false);

			}
		});
		btnNewButton_3.setBounds(10, 285, 180, 57);
		getContentPane().add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Secretario");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generalPanel.setVisible(false);
				adminPanel.setVisible(false);
				medicPanel.setVisible(false);
				nursePanel.setVisible(false);
				secretaryPanel.setVisible(true);
			}
		});
		btnNewButton_4.setBounds(10, 356, 180, 57);
		getContentPane().add(btnNewButton_4);
				

		adminPanel = new JPanel();
		adminPanel.setBounds(200, 79, 476, 335);
		getContentPane().add(adminPanel);
		adminPanel.setLayout(null);
		
				JLabel lblNewLabel_1 = new JLabel("Ayuda Administrador");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 37));
				lblNewLabel_1.setBounds(0, 0, 407, 45);
				adminPanel.add(lblNewLabel_1);
				
				JTextArea txtrLaVentanaDe_1 = new JTextArea("La ventana de admnistrador cuenta con 9 menús diferenciados en la parte izquierda \r\nque realizan las siguientes tareas sobre los empleados y pacientes.\r\n\r\n-Añadir: añadir pacientes o empleados al hospital rellenando el formulario que se le presenta\r\n\r\n-Ver: ver la plantilla de empleados o los pacientes ingresados, asÃ­ como sus caracteristicas\r\n\r\n-Borrar: Despedir empleados o dar el alta a pacientes(siempre con el aviso del medico) o \t     sacarlos del sistema tras su defunciÃ³n.\r\n\r\n-Editar: Editar cualquier paciente o empelado devido a un error en el formulario de registro o en combios en su estado\r\n\r\nTambiene tiene potestad de editar el almacen, comprar mercancia y retirar \r\nmercancia en mal estado");
				txtrLaVentanaDe_1.setLineWrap(true);
				txtrLaVentanaDe_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				txtrLaVentanaDe_1.setBackground(Color.LIGHT_GRAY);
				txtrLaVentanaDe_1.setBounds(10, 44, 466, 267);
				adminPanel.add(txtrLaVentanaDe_1);
		
				generalPanel = new JPanel();
				generalPanel.setBounds(200, 79, 476, 335);
				getContentPane().add(generalPanel);
				generalPanel.setLayout(null);
				
						JLabel lblNewLabel = new JLabel("Ayuda general");
						lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
						lblNewLabel.setBounds(0, 0, 304, 44);
						generalPanel.add(lblNewLabel);
						
						JTextArea txtrLaVentanaDe = new JTextArea("Bienvenido al hospital Xijoja, para comenzar su trabajo introduzca \r\nsu usuario y contraseña que se ha enviado a su correo electronico \r\ntras su contratación, espero que su lugar de trabajo le resulte \r\ncomodo y apreciable.\nLos botones de abajo le permitiran resetear la base de datos o guardar\r una copia de seguridad de la misma \n\n Para cualquier duda o sugerencia sobre el\r\n funcioanmiento de la aplicación mandenos un correo a \r\nhospitalxijoja@gmail.com. ");
						txtrLaVentanaDe.setLineWrap(true);
						txtrLaVentanaDe.setFont(new Font("Tahoma", Font.PLAIN, 15));
						txtrLaVentanaDe.setBackground(Color.LIGHT_GRAY);
						txtrLaVentanaDe.setBounds(10, 55, 456, 269);
						generalPanel.add(txtrLaVentanaDe);

		secretaryPanel = new JPanel();
		secretaryPanel.setBounds(200, 79, 476, 335);
		secretaryPanel.setBackground(Color.LIGHT_GRAY);

		getContentPane().add(secretaryPanel);

		JLabel lblAyudaSecretario = new JLabel("Ayuda Secretario");
		lblAyudaSecretario.setFont(new Font("Tahoma", Font.BOLD, 37));
		secretaryPanel.add(lblAyudaSecretario);
		
		JTextArea lblAyudaSecretarioAmplio = new JTextArea("Ayuda Secretario");
		lblAyudaSecretarioAmplio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAyudaSecretarioAmplio.setBounds(0,5,300,200);
		lblAyudaSecretarioAmplio.setBackground(Color.LIGHT_GRAY);
		String ayuda = 
				"La ventana de secretario cuenta con 2 menús diferenciados en la parte izquierda \n"
				+ " y son los siguientes: \n"
				+ "\n   -Añadir Paciente: Se introduce un paciente a la base de datos.\n"
				+ "\n   -Buscar Paciente: Buscando tanto por DNI como por el numero de la habitacion nos "
				+ "\n    devuelve los datos del paciente buscado.";
		lblAyudaSecretarioAmplio.setText(ayuda);
		lblAyudaSecretarioAmplio.setBounds(5,60,476,275);

		secretaryPanel.add(lblAyudaSecretarioAmplio);
		

		nursePanel = new JPanel();
		nursePanel.setBounds(200, 79, 476, 335);
		getContentPane().add(nursePanel);
		nursePanel.setLayout(null);
		nursePanel.setBackground(Color.LIGHT_GRAY);

		JLabel lblAyudaEnfermero = new JLabel("Ayuda Enfermero");
		lblAyudaEnfermero.setBounds(77, 5, 321, 45);
		lblAyudaEnfermero.setFont(new Font("Tahoma", Font.BOLD, 37));
		nursePanel.add(lblAyudaEnfermero);
		
		JTextArea lblAyudaEnfermeroAmplio = new JTextArea("Ayuda");
		lblAyudaEnfermeroAmplio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAyudaEnfermeroAmplio.setBackground(Color.lightGray);
		 String ayudaEnfermero = 
				"   La ventana de enfermero cuenta con 2 menús diferenciados en la parte izquierda \n"
				+ "   y son los siguientes: \n"
				+ "\n   -Usar Medicamento: Se empieza introduciendo un paciente, el cual tiene que"
				+ "\n\testar previamente asignado a ese enfermero. Le damos al boton de buscar"
				+ "\n\ty nos aparece la informacion del tratamiento asignado, el nombre y la cantidad"
				+ "\n\tselecionamos la cantidad de medicamento que se le ha tratado y le damos"
				+ "\n\tal botón de 'Tratamiento', seguidamente se borrarán las unidades de la "
				+ "\n\tbase de datos y se actualizan los datos en pantalla.\n"
				+ "\n   -Ver Pacientes: Se muestra una lista de todos los pacientes asigandos al"
				+ "\n\tenfermero actual.";
		 lblAyudaEnfermeroAmplio.setText(ayudaEnfermero);
		 lblAyudaEnfermeroAmplio.setBounds(5,60,476,275);
		 nursePanel.add(lblAyudaEnfermeroAmplio);
		 
		 
		 
		medicPanel = new JPanel();
		medicPanel.setBounds(200, 79, 476, 335);
		getContentPane().add(medicPanel);
		medicPanel.setLayout(null);

		JLabel lblAyudaMedico = new JLabel("Ayuda Medico");
		lblAyudaMedico.setBounds(10, 0, 258, 45);
		lblAyudaMedico.setFont(new Font("Tahoma", Font.BOLD, 37));
		medicPanel.add(lblAyudaMedico);
		
		JTextArea lblAyudaMedicoAmplio = new JTextArea("Ayuda");
		lblAyudaMedicoAmplio.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAyudaMedicoAmplio.setBackground(Color.lightGray);
		 String ayudaMedico = 
				"   La ventana de medico cuenta con 6 menús diferenciados en la parte izquierda \n"
				+ "   y son los siguientes: \n"
				+ "\n   -Asignar Medicamento: Se introduce el DNI, medicamento y cantidad del mismo"
				+ "\n después lo actualizará en la base de datos cuando se pulse el boton asignar, "
				+ "\navisará de cuantos productos de un medicamento quedan"
				+ "\n  -Ingresar Paciente: Aquí se rellenarán todos los campos para añadir un"
				+ "\n paciente nuevo a la base de datos"
				+ "\n  -Dar alta paciente: Aquí­ introducciendo DNI, Nombre y los 2 apellidos "
				+ "\nse le dará el alta a un paciente  "
				+ "\n y será eliminado de la base de datos"
				+ "\n\tbase de datos y se actualizan los datos en pantalla.\n"
				+ "\n   -Ver Pacientes: Se muestra una lista de todos los pacientes asigandos al"
				+ "\n\tenfermero actual."
				+ "\n   -informe semana: Se muestra un resumen de los pacientes dados"
				+ "\n de alta en la semana"
		 		+ "\n   -Buscar paciente: Se muestra una ventana en la cual podrás "
		 		+ "\nbuscar por nombre o habitación un paciente en específico";
		 lblAyudaMedicoAmplio.setText(ayudaMedico);
		 lblAyudaMedicoAmplio.setBounds(5,60,476,275);
		 medicPanel.add(lblAyudaMedicoAmplio);

	}
}
