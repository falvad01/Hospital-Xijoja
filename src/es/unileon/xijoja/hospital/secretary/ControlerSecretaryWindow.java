package es.unileon.xijoja.hospital.secretary;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;

import es.unileon.xijoja.hospital.EliminarDAO;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PatientsDAO;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.login.ControlerLoginWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;

public class ControlerSecretaryWindow implements ActionListener {
	
	private SecretaryWindow secretarywindow;
	private Logs log;
	private PatientsDAO dao;
	private PersonalDAO daoPersonal;
	
	private int count2;
	private EliminarDAO daoE;
	private ArrayList<String[]> arrayNurse, arrayMedic;
	String[] getPatientData = null;
	
	public ControlerSecretaryWindow(SecretaryWindow window) {
		this.dao = new PatientsDAO();
		this.daoPersonal= new PersonalDAO();
		this.secretarywindow = window;
		this.daoE= new EliminarDAO();

		log = new Logs();

	}
	
	
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("A�adir")) {
			secretarywindow.lblError.setForeground(Color.red);

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

				
			} else if (!secretarywindow.textFieldRoom.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
				add = false;

				secretarywindow.lblError.setText("La habitacion debe ser concretada con numero");

			}else if (dao.checkIfRoomIsBusy(Integer.parseInt(secretarywindow.textFieldRoom.getText()))) {
				add = false;
				secretarywindow.lblError.setText("Esa habitacion no est� disponible, proxima: "+ dao.firstRoomFree());
				log.InfoLog("Error, no se pudo introducir el paciente, habitaci�n ocupada");

			}else{
				secretarywindow.lblError.setText("");
			}
				if (add) {// Si da error no se a�ade el 
					System.out.println("Correcto");
	
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
						count2=1;
						daoE.eliminadosB(count2);
						
						log.InfoLog("A�adido el paciente con id: "+id);
						secretarywindow.lblError.setForeground(Color.BLACK);
						secretarywindow.lblError.setText("A�adido el paciente correctamente");

					} catch (SQLException e1) {
	
						e1.printStackTrace();
					}
				}
			
		} else if (arg0.getActionCommand().equals("Cerrar sesion")) {

				secretarywindow.setVisible(false);
				LoginWindow newlogin = LoginWindow.getInstance();
				newlogin.resetJField();
		
				
				
				
				
		} else if (arg0.getActionCommand().equals("Buscar Paciente")) {
				secretarywindow.addPatientPane.setVisible(false);
				secretarywindow.getPatientPane.setVisible(true);
				
		} else if (arg0.getActionCommand().equals("A�adir Paciente")) {
				secretarywindow.getPatientPane.setVisible(false);
				secretarywindow.addPatientPane.setVisible(true);
				
				
		} else if (arg0.getActionCommand().equals("Buscar")) {
			secretarywindow.lblErrorGetPatient.setText("");
			if ((secretarywindow.textFieldSearchDNIGetPatient.getText().toString().equals(""))){
				secretarywindow.lblErrorGetPatient.setForeground(Color.red);
				secretarywindow.lblErrorGetPatient.setText("Error en el formulario");
				log.InfoLog("Error al buscar el paciente");

				
			}else {
				//comprueba si se introduce un dni o numero de habitacion;
				boolean isDniOrRoom = isDni(secretarywindow.textFieldSearchDNIGetPatient.getText().toString());
				
				if (!dao.checkPatientExist(secretarywindow.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom)) {
					secretarywindow.lblErrorGetPatient.setForeground(Color.red);
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
					secretarywindow.lblErrorGetPatient.setForeground(Color.black);
					secretarywindow.lblErrorGetPatient.setText("Mostrando informacion del paciente");


			
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
