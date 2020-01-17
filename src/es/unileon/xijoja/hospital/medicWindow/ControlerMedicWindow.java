package es.unileon.xijoja.hospital.medicWindow;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.unileon.xijoja.hospital.WarehouseDAO;
import es.unileon.xijoja.hospital.EliminarDAO;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PatientsDAO;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.login.ControlerLoginWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.plaf.synth.SynthSpinnerUI;
import javax.swing.table.DefaultTableModel;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;
import com.sun.mail.handlers.text_html;

public class ControlerMedicWindow implements ActionListener {

	private MedicWindow window;
	private Logs log;
	private int count;
	private int count2;
	private int count3;
	private PatientsDAO dao;
	private PersonalDAO daoPersonal;
	private WarehouseDAO daoAlmacen;
	private EliminarDAO daoE;
	private ArrayList<String[]> arrayNurse, arrayMedic;
	private ArrayList<String[]> arrayMedicine;
	String[] getPatientData = null;
	String[] getPatientDNI = null;
	private int id,idProduct;
	private ArrayList<String[]> arrayPacientes;
	String[] getProductData = null;
	


	
	public ControlerMedicWindow(MedicWindow window) {

		this.window = window;
		dao = new PatientsDAO();
		this.daoAlmacen= new WarehouseDAO();
		this.daoPersonal= new PersonalDAO();
		this.daoE= new EliminarDAO();
		log = new Logs();
		
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
	

	public void filJComboBoxMedicines(JComboBox edit) {
		
		ArrayList<String[]> Medicines=daoAlmacen.getMedicineMedic();// ArrayList de Arrays;

		
			arrayMedicine=Medicines;	
		
		
		String[] data = new String[2];
		if (Medicines==null) {
			
		}else {
			for (int i = 0; i < Medicines.size(); i++) {
				data= Medicines.get(i);
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


	public void actionPerformed(ActionEvent arg0) {
		
		if ((arg0.getActionCommand().equals("Ver Pacientes"))) {
			
			window.seePacientsPanel.setVisible(true);
			window.addPatientsPanel.setVisible(false);
			window.deletePatientsPanel.setVisible(false);
			window.getPatientPane.setVisible(false);
			window.addMedicine.setVisible(false);
			window.week.setVisible(false);

		

			ArrayList<String[]> insert = null;
			int numOfRows= dao.getNumRow();

			String[] titles = null;

			String[][] matrixToInsert = null;
			
			titles = new String[] { "ID", "NOMBRE", "APELLIDO", "APELLIDO", "NIF", "FECHA", "NºHABITACION",
					"ENFERMEDAD", "IDMEDICAMENTO", "MEDICO", "TRATAMIENTO", "ENFERMERO " }; // Titulos de la tabla de
	
																	// los empleados
			insert = dao.getAllPatients();// ArrayList de Arrays
		
			matrixToInsert = new String[insert.size()][12];
			window.seePacientsPanel.setPreferredSize(new Dimension(624, 20 + 20 * insert.size()));
			window.seePacientsPanel.setBounds(274, 30, 695, 466);
			window.setBackground(Color.white);
			
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
			window.seePacientsPanel.setViewportView(PatientsTable);

			
			
		
	} else if (arg0.getActionCommand().equals("Registrar")) {
		
		

		boolean add = true;

		
		
		
		
		if ((window.NombreP.getText().equals("")) || (window.Apellido1.getText().equals(""))
				|| (window.Apellido2.getText().equals("")) || (window.DNI.getText().equals(""))
				|| (window.Habitacion.getText().equals("")) || (window.textU.getText().equals(""))) {// Comprobamos
			// si algum
			// campo esta
			// vacio

			add = false;
			window.lberror.setText("Hay campos vacios");
			log.InfoLog("Error, no se pudo introducir el paciente, hay campos vacios");
		} else if (!window.textU.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
			add = false;

			window.lberror.setText("Las unidades deben ser un numero");
		} else if (!window.Habitacion.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
			add = false;

			window.lberror.setText("La habitacion debe ser un numero");
		} else if(window.jcbMedic.getSelectedItem()==null||window.jcbNurse.getSelectedItem()==null) {
			add = false;
			window.lberror.setText("No hay medicos/enfermeros disponibles");
			log.InfoLog("Error, no se pudo introducir el paciente, no hay medicos/enfermeros disponibles");

		}else if (dao.checkIfRoomIsBusy(Integer.parseInt(window.Habitacion.getText()))) {
			add = false;
			window.lberror.setText("Esa habitación no está disponible, proxima: "+ dao.firstRoomFree());
			log.InfoLog("Error, no se pudo introducir el paciente, habitaciï¿½n ocupada");
		}else if(window.jcbMedicineadd.getSelectedItem()==null) {
			add = false;
			window.lberror.setText("No hay medicamentos");
			log.InfoLog("Error, No hay medicamentos");
	
		}else{
			window.lberror.setText("");
		}
		
 
		if (add) {// Si da error no se aï¿½ade el empleado
			
		
			int id = dao.firstIdFree();
			int m;
			
			Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual
			int idMedic=0,idNurse=0, idMedicine=0;;
			try {
				window.jcbMedicineadd.getSelectedIndex();
				for (int i = 0; i < arrayMedicine.size(); i++) {
					if (window.jcbMedicineadd.getSelectedItem().toString().equals(arrayMedicine.get(i)[1])) {
						idMedicine=Integer.parseInt(arrayMedicine.get(i)[0]);;
					}
				}
				for (int i = 0; i < arrayMedic.size(); i++) {
					if (window.jcbMedic.getSelectedItem().toString().equals(arrayMedic.get(i)[1])) {
						idMedic=Integer.parseInt(arrayMedic.get(i)[0]);;
					}
				}
				for (int i = 0; i < arrayNurse.size(); i++) {
					if (window.jcbNurse.getSelectedItem().toString().equals(arrayNurse.get(i)[1])) {
						idNurse=Integer.parseInt(arrayNurse.get(i)[0]);
					}
				}
				
			if(daoAlmacen.Medicine(Integer.parseInt(window.textU.getText()), idMedicine)<0) {
				add=false;
				m=daoAlmacen.MedicineA(Integer.parseInt(window.textU.getText()), idMedicine);
				window.lberror.setText("Error,quedan solo estas unidades: "+m);
			}else {
				dao.addPatientM(id, window.NombreP.getText(), window.Apellido1.getText(),
						window.Apellido2.getText(), window.DNI.getText(), date,
						Integer.parseInt(window.Habitacion.getText()),window.textEnfermedad.getText(),idMedicine,idMedic,Integer.parseInt(window.textU.getText()),idNurse);
				daoAlmacen.restMedicine(Integer.parseInt(window.textU.getText().toString()),idMedicine);//,idMedic);
				count2=1;
				daoE.eliminadosB(count2);
				count3=Integer.parseInt(window.textU.getText().toString());
				daoE.eliminadosM(count3);
				
				log.InfoLog("Añadido el paciente con id: "+id);
			}								
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
		
	} else if (arg0.getActionCommand().equals("Ingresar Paciente")) {
		window.seePacientsPanel.setVisible(false);
		window.addPatientsPanel.setVisible(true);
		window.deletePatientsPanel.setVisible(false);
		window.getPatientPane.setVisible(false);
		window.addMedicine.setVisible(false);
		window.week.setVisible(false);

		
		
	} else if (arg0.getActionCommand().equals("Dar alta Paciente")) {
	
		window.seePacientsPanel.setVisible(false);
		window.addPatientsPanel.setVisible(false);
		window.deletePatientsPanel.setVisible(true);
		window.getPatientPane.setVisible(false);
		window.addMedicine.setVisible(false);
		window.week.setVisible(false);
		window.lblErrorDelete.setText("");
		
	} else if (arg0.getActionCommand().equals("Borrar")) {
		
		

		boolean add = true;

		if ((window.textFieldNameToDelete.getText().equals("")) || (window.textFieldFirstDeleteToDelete.getText().equals(""))
				|| (window.textFieldSecondDeleteToDelete.getText().equals("")) || (window.textFieldDNIToDelete.getText().equals(""))) {
			add=false;
		window.lblErrorDelete.setText("Hay campos vacios");
		log.InfoLog("Error, no se pudo borrar el paciente, hay campos vacios");
		}else if ((window.jcbMedicine.getSelectedItem()==null)){
				window.lblErrorGetPatient.setText("No tiene pacientes asignados");
				log.InfoLog("Error, no se selecionó un paciente");
	}else {
		window.lblErrorDelete.setText("");

	}
		
		
		if(add) {
		//comprueba si se introduce un dni o numero de habitacion;
		
			
			boolean isDniOrRoom = isDni(window.textFieldDNIToDelete.getText().toString());

			if (!dao.checkPatientExist(window.textFieldDNIToDelete.getText().toString(),isDniOrRoom)) {
				add=false;
				window.lblErrorDelete.setText("DNI erroneo");
				log.InfoLog("Error, DNI o habitaciÃ³n erroneos");
			}else {
				
				
					Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual
					dao.deletePatient(window.textFieldNameToDelete.getText().toString(),
					window.textFieldFirstDeleteToDelete.getText().toString(),
					window.textFieldSecondDeleteToDelete.getText().toString(), window.textFieldDNIToDelete.getText().toString());
					window.lblErrorDelete.setText("");
					count=1;
					daoE.eliminadosAct(count);
					

			}
		}
		
	
	}else if (arg0.getActionCommand().equals("Asignar Medicamentos")) {
		
		window.seePacientsPanel.setVisible(false);
		window.addPatientsPanel.setVisible(false);
		window.deletePatientsPanel.setVisible(false);
		window.getPatientPane.setVisible(false);
		window.addMedicine.setVisible(true);
		window.week.setVisible(false);

	
	}else if (arg0.getActionCommand().equals("Asignar")) {
		
		int n,m;
		boolean add=true;
		
		int idMedicine=0;
		window.jcbMedic.getSelectedIndex();
		for (int i = 0; i < arrayMedicine.size(); i++) {
			if (window.jcbMedicine.getSelectedItem().toString().equals(arrayMedicine.get(i)[1])) {
				idMedicine=Integer.parseInt(arrayMedicine.get(i)[0]);;
			}
		}
		
		
		if ((window.DNIM.getText().toString().equals("")) || (window.units.getText().toString().equals(""))){
			add=false;
			window.lblError2.setText("Hay campos vacios");
			log.InfoLog("campos vacios al asignar medicamento");
		} else if (!window.units.getText().matches("[+-]?\\d*(\\.\\d+)?")) {
			add = false;

			window.lblError2.setText("Las unidades deben ser un numero");
		}else if(daoAlmacen.Medicine(Integer.parseInt(window.units.getText().toString()), idMedicine)<0) {
			add=false;
			m=daoAlmacen.MedicineA(Integer.parseInt(window.units.getText().toString()), idMedicine);

			window.lblError2.setText("Error,quedan solo estas unidades: "+m);
		}else if(window.jcbMedicine.getSelectedItem()==null) {
					add = false;
					window.lberror.setText("No hay medicamentos");
					log.InfoLog("Error, No hay medicamentos");
		

		}else {
			window.lblError2.setText("");

		}
		
		if(add) {
			
			
			
	
			
			boolean isDniOrRoom = isDni(window.DNIM.getText().toString());

			if(!dao.checkPatientExist(window.DNIM.getText().toString(),isDniOrRoom)) {
				add=false;

				window.lblError2.setText("DNI erroneo");
				log.InfoLog("DNI erroneo");
			}else {
				
				
					dao.AsignMedicine(Integer.parseInt(window.units.getText().toString()),idMedicine,window.DNIM.getText().toString());
				
						daoAlmacen.restMedicine(Integer.parseInt(window.units.getText().toString()),idMedicine);//,idMedic);
						window.lblError2.setText("");
						count3=Integer.parseInt(window.units.getText().toString());
						daoE.eliminadosM(count3);
					}
		
			
			
			
		
		}
		
	} else if (arg0.getActionCommand().equals("Buscar Paciente")) {
		window.seePacientsPanel.setVisible(false);
		window.addPatientsPanel.setVisible(false);
		window.deletePatientsPanel.setVisible(false);
		window.getPatientPane.setVisible(true);
		window.addMedicine.setVisible(false);
		window.week.setVisible(false);

		
	} else if (arg0.getActionCommand().equals("Buscar")) {
		
		boolean add=true;
		
		if ((window.textFieldSearchDNIGetPatient.getText().toString().equals(""))){
			add=false;
			window.lblErrorGetPatient.setText("                             campos vacios");
			log.InfoLog("campos vacios");

			
		}else {
			window.lblErrorGetPatient.setText("");

		}
		
		if(add) {
			//comprueba si se introduce un dni o numero de habitacion;
			boolean isDniOrRoom = isDni(window.textFieldSearchDNIGetPatient.getText().toString());
			
			if (!dao.checkPatientExist(window.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom)) {
				add=false;

				window.lblErrorGetPatient.setText("                              Error en el formulario");
				log.InfoLog("Error, no se encuentra el paciente indicado");
			}else {
				
				getPatientData = dao.getPatient(window.textFieldSearchDNIGetPatient.getText().toString(),isDniOrRoom);
				window.textFieldNameGetPatient.setText(getPatientData[1]);
				window.textFieldSurname1GetPatient.setText(getPatientData[2]);
				window.textFieldSurname2GetPatient.setText(getPatientData[3]);
				window.textFieldDNIGetPatient.setText(getPatientData[4]);
				window.textFieldRoomGetPatient.setText(getPatientData[6]);
				log.InfoLog("Devuelto el paciente con id: "+getPatientData[0]);
				window.lblErrorGetPatient.setText("");

		
			}	
		}
	
	

		
	}else if (arg0.getActionCommand().equals("Informe de la semana")) {
		
		window.seePacientsPanel.setVisible(false);
		window.addPatientsPanel.setVisible(false);
		window.deletePatientsPanel.setVisible(false);
		window.getPatientPane.setVisible(false);
		window.addMedicine.setVisible(false);
		window.week.setVisible(true);
		
		JLabel meds = new JLabel(String.valueOf(daoE.getElimMedicines()));
		meds.setBounds(318, 227, 56, 16);
		meds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		meds.setForeground(Color.BLACK);
		window.week.add(meds);
		

		JLabel adds = new JLabel(String.valueOf(daoE.getAdd()));
		adds.setBounds(318, 151, 56, 16);
		adds.setFont(new Font("Tahoma", Font.PLAIN, 15));
		window.week.add(adds);
		
		
		JLabel eliminados = new JLabel(String.valueOf(daoE.getEliminated()));
		eliminados.setBounds(318, 67, 56, 16);
		eliminados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		window.week.add(eliminados);
		
		
		
    }else if (arg0.getActionCommand().equals("Cerrar Sesión")) {

				window.setVisible(false);
				LoginWindow newlogin = LoginWindow.getInstance();
				newlogin.resetJField();
		
	
        	}
	}

}
