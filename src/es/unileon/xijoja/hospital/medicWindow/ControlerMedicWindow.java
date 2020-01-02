package es.unileon.xijoja.hospital.medicWindow;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;


public class ControlerMedicWindow implements ActionListener {

	private MedicWindow window;
	private Logs log;
	private PacientesDAO dao;
	//TODO poner aqui el dao en privado
	
	public ControlerMedicWindow(MedicWindow window) {

		this.window = window;
		this.dao = new PacientesDAO();
		log = new Logs();
		//TODO inicializar aqui el dao
	}
	

	public void actionPerformed(ActionEvent arg0) {
		// TODO aqui van las acciones al pulsar botones
		if ((arg0.getActionCommand().equals("Ver Pacientes"))) {//falta por probar con el dao

			window.seePacientsPanel.setVisible(true);
			
			
			ArrayList<String[]> insert = null;

			String[] titles = null;

			String[][] matrixToInsert = null;

			titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "FechaBaja", "Habitación",
					"Enfermedad", "Medicamentos" }; // Titulos de la tabla de
																	// los pacientes
			//insert = dao.getAllPacients();// ArrayList de Arrays//no funciona
			
			

			matrixToInsert = new String[insert.size() + 1][11];
			window.seePacientsPanel.setPreferredSize(new Dimension(624, 20 + 20 * insert.size()));
			window.seePacientsPanel.setBounds(284, 11, 624, 20 + 20 * insert.size());

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

			JTable PacientsTable = new JTable();
			PacientsTable.setBounds(20, 20, 600, 20 + 20 * insert.size());

			PacientsTable.setVisible(true);
			window.seePacientsPanel.add(PacientsTable);
			PacientsTable.setAutoscrolls(true);

			
			DefaultTableModel tableModel = new DefaultTableModel(matrixToInsert, titles);
			PacientsTable.setModel(tableModel);
			
			

	} else if (arg0.getActionCommand().equals("Ingresar Paciente")) {
		
		
		
		
		
		
	} else if (arg0.getActionCommand().equals("Dar alta Paciente")) {
		
		
		
		
		
		
	}else if (arg0.getActionCommand().equals("Asignar Medicamento Paciente")) {
		
		
		
		
		
		
	}else if (arg0.getActionCommand().equals("Informe de la semana")) {
		
		
		
		
		
		
	}
		

    }
}
