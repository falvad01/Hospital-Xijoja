package es.unileon.xijoja.hospital.secretary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;

import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.login.ControlerLoginWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;

public class ControlerSecretaryWindow implements ActionListener {
	
	private SecretaryWindow secretarywindow;
	private PacientesDAO dao;
	private PersonalDAO daoPersonal;
	private ArrayList<String[]> arrayNurse, arrayMedic;
	String[] getPatientData = null;
	
	public ControlerSecretaryWindow(SecretaryWindow window) {
		this.dao = new PacientesDAO();
		this.daoPersonal= new PersonalDAO();
		this.secretarywindow = window;
	}
	
	

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("Añadir")) {////////////////////////////////// Aï¿½adir

			boolean add = true;

			if ((secretarywindow.textFieldName.getText().equals("")) || (secretarywindow.textFieldSurname1.getText().equals(""))
					|| (secretarywindow.textFieldSurname2.getText().equals("")) || (secretarywindow.textFieldNIFNIE.getText().equals(""))
					|| (secretarywindow.textFieldRoom.getText().equals(""))) {// Comprobamos
				// si algum
				// campo esta
				// vacio

				add = false;
				secretarywindow.lblError.setText("Hay campos vacios");
			} else if(secretarywindow.jcbMedic.getSelectedItem()==null||secretarywindow.jcbNurse.getSelectedItem()==null) {
				add = false;
				secretarywindow.lblError.setText("No hay medicos/enfermeros disponibles");
				
			}else {
				secretarywindow.lblError.setText("");
			}

			if (add) {// Si da error no se aï¿½ade el empleado
				System.out.println("Correcto");

				//TODO get last id funciona regular, me puso un -1
				int id = dao.getLastID();
				
				Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual
				int idMedic=0,idNurse=0;
				try {
					//TODO: añadir medico
					secretarywindow.jcbMedic.getSelectedIndex();
					
					for (int i = 0; i < arrayMedic.size(); i++) {
						if (secretarywindow.jcbMedic.getSelectedItem().toString().equals(arrayMedic.get(i)[1])) {
							idMedic=i;
						}
					}
					for (int i = 0; i < arrayNurse.size(); i++) {
						if (secretarywindow.jcbNurse.getSelectedItem().toString().equals(arrayNurse.get(i)[1])) {
							idNurse=i;
						}
					}
					System.out.println("id medico: "+ idMedic+" id Enfermero: "+idNurse);
					

					dao.addPatient(id+1, secretarywindow.textFieldName.getText(), secretarywindow.textFieldSurname1.getText(),
							secretarywindow.textFieldSurname2.getText(), secretarywindow.textFieldNIFNIE.getText(), date,
							Integer.parseInt(secretarywindow.textFieldRoom.getText()),idMedic,idNurse);// LLamamos a la
																				// funcion del DAO
																				// que inserta el
																				// paciente
				} catch (SQLException e1) {

					e1.printStackTrace();
				}
//                try {
//                    lblAdministrador.setText(String.valueOf(numAdmin));
//                    lblMedico.setText(String.valueOf(numDoc));
//                    lblEnfermero.setText(String.valueOf(numNurse));
//                    lblSecretario.setText(String.valueOf(numSecre));
//                    lblTotal.setText(String.valueOf(numAdmin + numDoc + numNurse + numSecre));
//                } catch (SQLException e) {
//
//                    e.printStackTrace();
//                }
//            }
			}
			
			} 
		 else if (arg0.getActionCommand().equals("Cerrar sesion")) {

				secretarywindow.setVisible(false);
				//TODO arreglar que se borren los campos al cerrar sesion
				try {
					LoginWindow newlogin = new LoginWindow();
					ControlerLoginWindow controlerLogin = new ControlerLoginWindow(newlogin);
					controlerLogin.resetJField();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 }else if (arg0.getActionCommand().equals("Buscar Paciente")) {
				System.out.println("1111111");
				secretarywindow.addPatientPane.setVisible(false);
				secretarywindow.getPatientPane.setVisible(true);
				
			} else if (arg0.getActionCommand().equals("Añadir Paciente")) {
				System.out.println("2");
				secretarywindow.getPatientPane.setVisible(false);
				secretarywindow.addPatientPane.setVisible(true);
				
				
			} else if (arg0.getActionCommand().equals("Buscar")) {

				
				 // TODO busqueda por nombre y por habitacion 
			
				
			if ((secretarywindow.textFieldSearchDNIGetPatient.getText().toString().equals(""))){
				secretarywindow.lblErrorGetPatient.setText("Error en el formulario");
			}else {
				//comprueba si se introduce un dni o numero de habitacion;
				boolean isDniOrRoom = isDni(secretarywindow.textFieldSearchDNIGetPatient.getText().toString());
				
					if (!dao.checkPatientExist(secretarywindow.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom)) {
						secretarywindow.lblErrorGetPatient.setText("Error en el formulario");
					}else {
					
						getPatientData = dao.getPatient(secretarywindow.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom);

				secretarywindow.textFieldNameGetPatient.setText(getPatientData[1]);
				secretarywindow.textFieldSurname1GetPatient.setText(getPatientData[2]);
				secretarywindow.textFieldSurname2GetPatient.setText(getPatientData[3]);
				secretarywindow.textFieldDNIGetPatient.setText(getPatientData[4]);
				secretarywindow.textFieldRoomGetPatient.setText(getPatientData[6]);

			
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
