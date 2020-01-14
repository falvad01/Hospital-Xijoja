package es.unileon.xijoja.hospital.nurse;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;
import com.sun.mail.handlers.text_html;

import es.unileon.xijoja.hospital.AlmacenDAO;
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
	private AlmacenDAO daoAlmacen;
	private int id, idPatient;;
	private ArrayList<String[]> listPatients;
	String[] getPatientData = null;
	
	public ControlerNurseWindow(NurseWindow window) {
		this.dao = new PacientesDAO();
		this.daoPersonal= new PersonalDAO();
		this.daoAlmacen= new AlmacenDAO();
		this.nurseWindow = window;
		log = new Logs();
		this. id= daoPersonal.getIdByUserAndPass(nurseWindow.user,nurseWindow.password);
		try {
		     GraphicsEnvironment ge =   GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("etc/rexlia.ttf")));
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}

	}
	
	

	public void actionPerformed(ActionEvent arg0) {

		if  (arg0.getActionCommand().equals("Cerrar sesion")) {

				nurseWindow.setVisible(false);
				//TODO arreglar que se borren los campos al cerrar sesion
				LoginWindow newlogin = LoginWindow.getInstance();
				newlogin.resetJField();
				
		} else if (arg0.getActionCommand().equals("Usar Medicamento")) {
				nurseWindow.seePatientPane.setVisible(false);
				nurseWindow.getPatientPane.setVisible(true);
				
		} else if (arg0.getActionCommand().equals("Ver Pacientes") || (arg0.getActionCommand().equals("Recargar"))) {

				nurseWindow.seePatientPane.setVisible(true);
				nurseWindow.getPatientPane.setVisible(false);
				nurseWindow.btnVerPlantilla.setText("Recargar");
				//nurseWindow.lblError.setText("");

				ArrayList<String[]> insert = null;
				int numOfRows= dao.getNumRow();

				String[] titles = null;

				String[][] matrixToInsert = null;
				
				titles = new String[] { "ID", "NOMBRE", "APELLIDO", "APELLIDO", "NIF", "FECHA", "NºHABITACION",
						"ENFERMEDAD", "IDMEDICAMENTO", "MEDICO", "TRATAMIENTO", "ENFERMERO " }; // Titulos de la tabla de
		
																		// los empleados
				insert = dao.getAllPatients(id,false);// ArrayList de Arrays
			
				matrixToInsert = new String[insert.size()][12];
				nurseWindow.seePatientPane.setPreferredSize(new Dimension(624, 20 + 20 * insert.size()));
				nurseWindow.seePatientPane.setBounds(274, 30, 695, 466);
				nurseWindow.setBackground(Color.white);
				
				for (int i = 0; i < insert.size(); i++) { // rellenamos la matriz que meteremos en la tabla a partir
					// del ArrayList de arrays devuelto del DAO
					for (int j = 0; j < 12; j++) {
					
							matrixToInsert[i][j] = insert.get(i)[j];					
					}
				}
	
				
				JTable PatientsTable = new JTable();
				PatientsTable.setBackground(Color.WHITE);
				PatientsTable.setBounds(5, 5, 600, 20 + 20 * insert.size());
			;

				PatientsTable.setVisible(true);
			//	nurseWindow.seePatientPane.add(PatientsTable);
				PatientsTable.setAutoscrolls(true);
				

				
				DefaultTableModel tableModel = new DefaultTableModel(matrixToInsert, titles);

				PatientsTable.setModel(tableModel);
				PatientsTable.setFont(new Font("Rexlia Rg", Font.TRUETYPE_FONT, 9));				
				nurseWindow.seePatientPane.setViewportView(PatientsTable);

				
				
				
		} else if (arg0.getActionCommand().equals("Buscar")) {
			
				
			if ((nurseWindow.jcbPatient.getSelectedItem()==null)){
				nurseWindow.lblErrorGetPatient.setText("No tiene pacientes asignados");
				log.InfoLog("Error, no se selecionó un paciente");

				
			}else {
				
				
				for (int i = 0; i < listPatients.size(); i++) {
					if (nurseWindow.jcbPatient.getSelectedItem().toString().equals(listPatients.get(i)[1])) {
						idPatient=Integer.parseInt(listPatients.get(i)[0]);
					}
				}
					nurseWindow.labelFieldMedicine.setEnabled(true);
					nurseWindow.labelFieldUnits.setEnabled(true);
					nurseWindow.btnUseMedicine.setEnabled(true);

					filJComboBoxUnits();
					getPatientData = dao.getPatient(idPatient);
					if (getPatientData[8]==null) {
						nurseWindow.labelFieldMedicine.setText("Sin tratamiento asignado");
						
					}else {
						String nombreMedicina= daoAlmacen.getMedicineName(Integer.parseInt(getPatientData[8]));
						System.out.println(nombreMedicina);
						nombreMedicina= nombreMedicina.replace("0","");
					nurseWindow.labelFieldMedicine.setText(nombreMedicina);
					}
					nurseWindow.labelFieldUnits.setText(getPatientData[10]);
					log.InfoLog("Devuelto el paciente con id: "+getPatientData[0]);

			
			}
		}else if (arg0.getActionCommand().equals("Tratamiento")) {
			System.out.println("asdasd");
			if ((nurseWindow.jcbNUtits.getSelectedItem().toString().equals("0"))){
				nurseWindow.lblErrorGetPatient.setText("Seleciona cuanto tratamiento has realizado");
				log.InfoLog("Error, se seleciono 0 de cantidad de medicamento");

				
			}else {
				
				int newUnitMedicine = Integer.parseInt(getPatientData[10])-Integer.parseInt(nurseWindow.jcbNUtits.getSelectedItem().toString());
				dao.UseMedicine(newUnitMedicine, idPatient);
				
				
				if (newUnitMedicine==0) {
					dao.setfkProductoNull(idPatient);
				}
					filJComboBoxUnits();
					getPatientData = dao.getPatient(idPatient);
					if (getPatientData[8]==null) {
						nurseWindow.labelFieldMedicine.setText("Sin tratamiento asignado");

					}else {
						String nombreMedicina= daoAlmacen.getMedicineName(Integer.parseInt(getPatientData[8]));
						System.out.println(nombreMedicina);
						nombreMedicina= nombreMedicina.replace(" ","");
						nurseWindow.labelFieldMedicine.setText(nombreMedicina);
					}
					
					nurseWindow.labelFieldUnits.setText(getPatientData[10]);
					
					log.InfoLog("Se le aplico el tratamiento al paciente: "+getPatientData[0]);

			
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
		nurseWindow.jcbNUtits.removeAllItems();
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
