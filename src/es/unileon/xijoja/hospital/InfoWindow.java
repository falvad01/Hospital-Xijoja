package es.unileon.xijoja.hospital;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPanel;
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

		secretaryPanel = new JPanel();
		secretaryPanel.setBounds(200, 79, 476, 335);
		getContentPane().add(secretaryPanel);

		JLabel lblAyudaSecretario = new JLabel("Ayuda Secretario");
		lblAyudaSecretario.setFont(new Font("Tahoma", Font.BOLD, 37));
		secretaryPanel.add(lblAyudaSecretario);

		nursePanel = new JPanel();
		nursePanel.setBounds(200, 79, 476, 335);
		getContentPane().add(nursePanel);
		nursePanel.setLayout(null);

		JLabel lblAyudaEnfermero = new JLabel("Ayuda Enfermero");
		lblAyudaEnfermero.setBounds(77, 5, 321, 45);
		lblAyudaEnfermero.setFont(new Font("Tahoma", Font.BOLD, 37));
		nursePanel.add(lblAyudaEnfermero);

		medicPanel = new JPanel();
		medicPanel.setBounds(200, 79, 476, 335);
		getContentPane().add(medicPanel);
		medicPanel.setLayout(null);

		JLabel lblAyudaEmdico = new JLabel("Ayuda Medico");
		lblAyudaEmdico.setBounds(10, 0, 258, 45);
		lblAyudaEmdico.setFont(new Font("Tahoma", Font.BOLD, 37));
		medicPanel.add(lblAyudaEmdico);

		adminPanel = new JPanel();
		adminPanel.setBounds(200, 79, 476, 335);
		getContentPane().add(adminPanel);
		adminPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Ayuda Administrador");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel_1.setBounds(0, 0, 407, 45);
		adminPanel.add(lblNewLabel_1);

		generalPanel = new JPanel();
		generalPanel.setBounds(200, 79, 476, 335);
		getContentPane().add(generalPanel);
		generalPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Ayuda general");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		lblNewLabel.setBounds(0, 0, 304, 44);
		generalPanel.add(lblNewLabel);

	}
}
