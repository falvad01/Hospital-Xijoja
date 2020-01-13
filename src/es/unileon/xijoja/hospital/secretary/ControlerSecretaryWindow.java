package es.unileon.xijoja.hospital.secretary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;

import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.login.ControlerLoginWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;

public class ControlerSecretaryWindow implements ActionListener {
	
	private SecretaryWindow secretarywindow;
	private Logs log;
	private PacientesDAO dao;
	private PersonalDAO daoPersonal;
	private ArrayList<String[]> arrayNurse, arrayMedic;
	String[] getPatientData = null;
	
	public ControlerSecretaryWindow(SecretaryWindow window) {
		this.dao = new PacientesDAO();
		this.daoPersonal= new PersonalDAO();
		this.secretarywindow = window;
		log = new Logs();

	}
	
	
//TODO comprobar codigo inutil
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Añadir")) {

			boolean add = true;
			if ((secretarywindow.textFieldName.getText().equals("")) || (secretarywindow.textFieldSurname1.getText().equals(""))
					|| (secretarywindow.textFieldSurname2.getText().equals("")) || (secretarywindow.textFieldNIFNIE.getText().equals(""))
					|| (secretarywindow.textFieldRoom.getText().equals(""))) {

				add = false;
				secretarywindow.lblError.setText("Hay campos vacios");
				log.InfoLog("Error, no se pudo introducir el paciente, hay campos vacios");

			} else if(secretarywindow.jcbMedic.getSelectedItem()==null||secretarywindow.jcbNurse.getSelectedItem()==null) {
				add = false;
				secretarywindow.lblError.setText("No hay medicos/enfermeros disponibles");
				log.InfoLog("Error, no se pudo introducir el paciente, no hay medicos/enfermeros disponibles");

				
			}else if (dao.checkIfRoomIsBusy(Integer.parseInt(secretarywindow.textFieldRoom.getText()))) {
				add = false;
				secretarywindow.lblError.setText("Esa habitacion no estï¿½ disponible, proxima: "+ dao.firstRoomFree());
				log.InfoLog("Error, no se pudo introducir el paciente, habitaciï¿½n ocupada");

			}else{
				secretarywindow.lblError.setText("");
			}
				if (add) {// Si da error no se aï¿½ade el empleado
					System.out.println("Correcto");
	
				//	int id = dao.getLastID()+1;//siguiente id
					int id = dao.firstIdFree();
					
					Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual
					int idMedic=0,idNurse=0;
					try {
						secretarywindow.jcbMedic.getSelectedIndex();
						
						for (int i = 0; i < arrayMedic.size(); i++) {
							if (secretarywindow.jcbMedic.getSelectedItem().toString().equals(arrayMedic.get(i)[1])) {
								idMedic=Integer.parseInt(arrayMedic.get(i)[0]);;
							}
						}
						for (int i = 0; i < arrayNurse.size(); i++) {
							if (secretarywindow.jcbNurse.getSelectedItem().toString().equals(arrayNurse.get(i)[1])) {
								idNurse=Integer.parseInt(arrayNurse.get(i)[0]);
							}
						}
						
	
						dao.addPatient(id, secretarywindow.textFieldName.getText(), secretarywindow.textFieldSurname1.getText(),
								secretarywindow.textFieldSurname2.getText(), secretarywindow.textFieldNIFNIE.getText(), date,
								Integer.parseInt(secretarywindow.textFieldRoom.getText()),secretarywindow.textFielddisease.getText(),idMedic,idNurse);// LLamamos a la
																					// funcion del DAO
																					// que inserta el
																					// paciente
						log.InfoLog("Aï¿½adido el paciente con id: "+id);

					} catch (SQLException e1) {
	
						e1.printStackTrace();
					}
				}
			
		} else if (arg0.getActionCommand().equals("Cerrar sesion")) {

				secretarywindow.setVisible(false);
				//TODO arreglar que se borren los campos al cerrar sesion
				LoginWindow newlogin = LoginWindow.getInstance();
				newlogin.resetJField();
		
				
				
				
				
		} else if (arg0.getActionCommand().equals("Buscar Paciente")) {
				secretarywindow.addPatientPane.setVisible(false);
				secretarywindow.getPatientPane.setVisible(true);
				
		} else if (arg0.getActionCommand().equals("Añadir Paciente")) {
				secretarywindow.getPatientPane.setVisible(false);
				secretarywindow.addPatientPane.setVisible(true);
				
				
		} else if (arg0.getActionCommand().equals("Buscar")) {
				
			if ((secretarywindow.textFieldSearchDNIGetPatient.getText().toString().equals(""))){
				secretarywindow.lblErrorGetPatient.setText("                                          Error en el formulario");
				log.InfoLog("Error al buscar el paciente");

				
			}else {
				//comprueba si se introduce un dni o numero de habitacion;
				boolean isDniOrRoom = isDni(secretarywindow.textFieldSearchDNIGetPatient.getText().toString());
				
				if (!dao.checkPatientExist(secretarywindow.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom)) {
					secretarywindow.lblErrorGetPatient.setText("Error en el formulario");
					log.InfoLog("Error, no se encuentra el paciente indicado");
				}else {
					
					getPatientData = dao.getPatient(secretarywindow.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom);
					secretarywindow.labelFieldNameGetPatient.setText(getPatientData[1]);
					secretarywindow.labelFieldSurname1GetPatient.setText(getPatientData[2]);
					secretarywindow.labelFieldSurname2GetPatient.setText(getPatientData[3]);
					secretarywindow.labelFieldDNIGetPatient.setText(getPatientData[4]);
					secretarywindow.labelFieldRoomGetPatient.setText(getPatientData[6]);
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
