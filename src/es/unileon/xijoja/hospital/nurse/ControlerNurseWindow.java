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
	private ArrayList<String[]> arrayNurse, arrayMedic;
	String[] getPatientData = null;
	
	public ControlerNurseWindow(NurseWindow window) {
		this.dao = new PacientesDAO();
		this.daoPersonal= new PersonalDAO();
		this.nurseWindow = window;
		log = new Logs();

	}
	
	

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Añadir")) {

			boolean add = true;
			if ((nurseWindow.textFieldName.getText().equals("")) || (nurseWindow.textFieldSurname1.getText().equals(""))
					|| (nurseWindow.textFieldSurname2.getText().equals("")) || (nurseWindow.textFieldNIFNIE.getText().equals(""))
					|| (nurseWindow.textFieldRoom.getText().equals(""))) {

				add = false;
				nurseWindow.lblError.setText("Hay campos vacios");
				log.InfoLog("Error, no se pudo introducir el paciente, hay campos vacios");

			} else if(nurseWindow.jcbMedic.getSelectedItem()==null||nurseWindow.jcbNurse.getSelectedItem()==null) {
				add = false;
				nurseWindow.lblError.setText("No hay medicos/enfermeros disponibles");
				log.InfoLog("Error, no se pudo introducir el paciente, no hay medicos/enfermeros disponibles");

				
			}else if (dao.checkIfRoomIsBusy(Integer.parseInt(nurseWindow.textFieldRoom.getText()))) {
				add = false;
				nurseWindow.lblError.setText("Esa habitacion no está disponible, proxima: "+ dao.firstRoomFree());
				log.InfoLog("Error, no se pudo introducir el paciente, habitación ocupada");

			}else{
				nurseWindow.lblError.setText("");
			}
				if (add) {// Si da error no se aï¿½ade el empleado
					System.out.println("Correcto");
	
					int id = dao.getLastID()+1;//siguiente id
					
					Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual
					int idMedic=0,idNurse=0;
					try {
						nurseWindow.jcbMedic.getSelectedIndex();
						
						for (int i = 0; i < arrayMedic.size(); i++) {
							if (nurseWindow.jcbMedic.getSelectedItem().toString().equals(arrayMedic.get(i)[1])) {
								idMedic=Integer.parseInt(arrayMedic.get(i)[0]);;
							}
						}
						for (int i = 0; i < arrayNurse.size(); i++) {
							if (nurseWindow.jcbNurse.getSelectedItem().toString().equals(arrayNurse.get(i)[1])) {
								idNurse=Integer.parseInt(arrayNurse.get(i)[0]);
							}
						}
						
	
						dao.addPatient(id, nurseWindow.textFieldName.getText(), nurseWindow.textFieldSurname1.getText(),
								nurseWindow.textFieldSurname2.getText(), nurseWindow.textFieldNIFNIE.getText(), date,
								Integer.parseInt(nurseWindow.textFieldRoom.getText()),idMedic,idNurse);// LLamamos a la
																					// funcion del DAO
																					// que inserta el
																					// paciente
						log.InfoLog("Añadido el paciente con id: "+id);

					} catch (SQLException e1) {
	
						e1.printStackTrace();
					}
				}
			
		} else if (arg0.getActionCommand().equals("Cerrar sesion")) {

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
				int id= daoPersonal.getIdByUserAndPass(nurseWindow.user,nurseWindow.password);

				titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "Fecha", "HabitaciÃ³n",
						"Enfermedad", "Producto", "Medico", "Unidades medicamento", "Enfermero " }; // Titulos de la tabla de
																		// los empleados
				insert = dao.getAllPatients();// ArrayList de Arrays
			
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
				
			if ((nurseWindow.textFieldSearchDNIGetPatient.getText().toString().equals(""))){
				nurseWindow.lblErrorGetPatient.setText("Error en el formulario");
				log.InfoLog("Error al buscar el paciente");

				
			}else {
				//comprueba si se introduce un dni o numero de habitacion;
				boolean isDniOrRoom = isDni(nurseWindow.textFieldSearchDNIGetPatient.getText().toString());
				
				if (!dao.checkPatientExist(nurseWindow.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom)) {
					nurseWindow.lblErrorGetPatient.setText("Error en el formulario");
					log.InfoLog("Error, no se encuentra el paciente indicado");
				}else {
					
					getPatientData = dao.getPatient(nurseWindow.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom);
					nurseWindow.textFieldNameGetPatient.setText(getPatientData[1]);
					nurseWindow.textFieldSurname1GetPatient.setText(getPatientData[2]);
					nurseWindow.textFieldSurname2GetPatient.setText(getPatientData[3]);
					nurseWindow.textFieldDNIGetPatient.setText(getPatientData[4]);
					nurseWindow.textFieldRoomGetPatient.setText(getPatientData[6]);
					log.InfoLog("Devuelto el paciente con id: "+getPatientData[0]);

			
				}	
			}
		}
	}
	public void filJComboBox(JComboBox edit, boolean ismedic) {

		ArrayList<String[]> list =  daoPersonal.getNuseAndMedic(ismedic);
		if (ismedic) {
			arrayMedic=list;	
		}else {
			arrayNurse=list;
		}
		
		String[] data = new String[2];
		if (list==null) {
			
		}else {
			for (int i = 0; i < list.size(); i++) {
				data= list.get(i);
				edit.addItem(data[1]);
				
				
			}
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
