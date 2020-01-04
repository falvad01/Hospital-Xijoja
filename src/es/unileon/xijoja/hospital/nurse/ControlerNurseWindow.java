package es.unileon.xijoja.hospital.nurse;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.login.ControlerLoginWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;
import es.unileon.xijoja.hospital.secretary.SecretaryWindow;

public class ControlerNurseWindow implements ActionListener {


	private NurseWindow nurseWindow;
	private Logs log;
	private PacientesDAO dao;
	private PersonalDAO daoPersonal;
	private int id, idPatient;;
	private ArrayList<String[]> listPatients;
	String[] getPatientData = null;
	
	public ControlerNurseWindow(NurseWindow window) {
		this.dao = new PacientesDAO();
		this.daoPersonal= new PersonalDAO();
		this.nurseWindow = window;
		log = new Logs();
		this. id= daoPersonal.getIdByUserAndPass(nurseWindow.user,nurseWindow.password);

	}
	
	

	public void actionPerformed(ActionEvent arg0) {

		if  (arg0.getActionCommand().equals("Cerrar sesion")) {

				nurseWindow.setVisible(false);
				//TODO arreglar que se borren los campos al cerrar sesion
				try {
					LoginWindow newlogin = new LoginWindow();
					ControlerLoginWindow controlerLogin = new ControlerLoginWindow(newlogin);
					controlerLogin.resetJField();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		} else if (arg0.getActionCommand().equals("Usar Medicamento")) {
				nurseWindow.seePatientPane.setVisible(false);
				nurseWindow.getPatientPane.setVisible(true);
				
		} else if (arg0.getActionCommand().equals("Ver Pacientes") || (arg0.getActionCommand().equals("Recargar"))) {

				nurseWindow.seePatientPane.setVisible(true);
				nurseWindow.getPatientPane.setVisible(false);
				nurseWindow.btnVerPlantilla.setText("Recargar");
				//nurseWindow.lblError.setText("");

				ArrayList<String[]> insert = null;

				String[] titles = null;

				String[][] matrixToInsert = null;
				
System.out.println("id es: "+id+nurseWindow.user+nurseWindow.password);
				titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "Fecha", "Habitaci√≥n",
						"Enfermedad", "Producto", "Medico", "Unidades medicamento", "Enfermero " }; // Titulos de la tabla de
																		// los empleados
				insert = dao.getAllPatients(id);// ArrayList de Arrays
			
				matrixToInsert = new String[insert.size() + 1][12];
				nurseWindow.seePatientPane.setPreferredSize(new Dimension(624, 20 + 20 * insert.size()));
				nurseWindow.seePatientPane.setBounds(284, 11, 624, 20 + 20 * insert.size());
				
				for (int i = 0; i < insert.size()+1; i++) { // rellenamos la matriz que meteremos en la tabla a partir
					// del ArrayList de arrays devuelto del DAO
					for (int j = 0; j < 12; j++) {
						if (i == 0) {
							

							matrixToInsert[i][j] = titles[j];

						} else {
							matrixToInsert[i][j] = insert.get(i-1)[j];					}
					}
	}
				
				JTable PatientsTable = new JTable();
				PatientsTable.setBounds(5, 5, 600, 20 + 20 * insert.size());

				PatientsTable.setVisible(true);
				nurseWindow.seePatientPane.add(PatientsTable);
				PatientsTable.setAutoscrolls(true);
				

				
				DefaultTableModel tableModel = new DefaultTableModel(matrixToInsert, titles);
				PatientsTable.setModel(tableModel);
				
				
				
		} else if (arg0.getActionCommand().equals("Buscar")) {
			
				
			if ((nurseWindow.jcbPatient.getSelectedItem()==null)){
				nurseWindow.lblErrorGetPatient.setText("No tiene pacientes asignados");
				log.InfoLog("Error, no se selecionÛ un paciente");

				
			}else {
				
				
				for (int i = 0; i < listPatients.size(); i++) {
					if (nurseWindow.jcbPatient.getSelectedItem().toString().equals(listPatients.get(i)[1])) {
						idPatient=Integer.parseInt(listPatients.get(i)[0]);
					}
				}
			
					filJComboBoxUnits();
					getPatientData = dao.getPatient(idPatient);
					nurseWindow.textFieldMedicine.setText(getPatientData[8]);
					nurseWindow.textFieldUnits.setText(getPatientData[10]);
					log.InfoLog("Devuelto el paciente con id: "+getPatientData[0]);

			
			}
		}
	}
	
	public void filJComboBox(JComboBox edit) {

		listPatients =dao.getPatientsByNurseOrMedic(false,id);// ArrayList de Arrays;

		String[] data = new String[2];
		if (listPatients==null) {
			
		}else {
			for (int i = 0; i < listPatients.size(); i++) {
				data= listPatients.get(i);
				edit.addItem(data[1]);
				
				
			}
		}
		
		
	}
	public void filJComboBoxUnits() {
		nurseWindow.jcbNUtits.removeAll();
//TODO que se llame a esto cuando se selecione un paciente
		int units =dao.getMedicineUnits(idPatient);// ArrayList de Arrays;

			for (int i = 0; i <= units; i++) {
				
				nurseWindow.jcbNUtits.addItem(i);
				
				
			}
		
		
		
	}
	
	public boolean isDni (String dniOrRoom) {
		// return true si es dni
	
		char lastChar = dniOrRoom.charAt(dniOrRoom.length()-1);
		if (Character.isLetter(lastChar)) {
			return true;
		}else {
			return false;
		}
		
	}

}
