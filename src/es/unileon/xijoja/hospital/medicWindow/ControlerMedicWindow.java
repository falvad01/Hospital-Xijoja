package es.unileon.xijoja.hospital.medicWindow;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import es.unileon.xijoja.hospital.AlmacenDAO;
import es.unileon.xijoja.hospital.Logs;
import es.unileon.xijoja.hospital.PacientesDAO;
import es.unileon.xijoja.hospital.PersonalDAO;
import es.unileon.xijoja.hospital.login.ControlerLoginWindow;
import es.unileon.xijoja.hospital.login.LoginWindow;


public class ControlerMedicWindow implements ActionListener {

	private MedicWindow window;
	private Logs log;
	//private PersonalDAO dao;
	private PacientesDAO dao;
	private PersonalDAO daoPersonal;
	private AlmacenDAO daoAlmacen;
	private ArrayList<String[]> arrayNurse, arrayMedic;
	private ArrayList<String[]> arrayMedicine;
	String[] getPatientData = null;
	String[] getPatientDNI = null;
	private int id,idProduct;
	private ArrayList<String[]> arrayPacientes;
	String[] getProductData = null;



	

	//TODO poner aqui el dao en privado
	
	public ControlerMedicWindow(MedicWindow window) {

		this.window = window;
		dao = new PacientesDAO();
		this.daoAlmacen= new AlmacenDAO();
		this.daoPersonal= new PersonalDAO();
		//this. id= daoPersonal.getIdByUserAndPass(window.user,window.password);
		log = new Logs();
		//TODO inicializar aqui el dao
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
		// TODO aqui van las acciones al pulsar botones
		if ((arg0.getActionCommand().equals("Ver Pacientes"))) {//falta por probar con el dao
			
			window.seePacientsPanel.setVisible(true);
			window.addPatientsPanel.setVisible(false);
			window.deletePatientsPanel.setVisible(false);
			window.getPatientPane.setVisible(false);
			window.addMedicine.setVisible(false);
			window.week.setVisible(false);

			
			ArrayList<String[]> insert = null;

			String[] titles = null;

			String[][] matrixToInsert = null;

			titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "Fecha", "Habitación",
					"Enfermedad", "Producto", "Medico", "Unidades medicamento", "Enfermero " }; // Titulos de la tabla de
																	// los empleados
			insert = dao.getAllPatients();// ArrayList de Arrays
		
			matrixToInsert = new String[insert.size() + 1][12];
			window.seePacientsPanel.setPreferredSize(new Dimension(624, 20 + 20 * insert.size()));
			window.seePacientsPanel.setBounds(284, 11, 624, 20 + 20 * insert.size());
			
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
			window.seePacientsPanel.add(PatientsTable);
			PatientsTable.setAutoscrolls(true);
			

			
			DefaultTableModel tableModel = new DefaultTableModel(matrixToInsert, titles);
			PatientsTable.setModel(tableModel);
			
			
		
	} else if (arg0.getActionCommand().equals("Registrar")) {
		
		// log.InfoLog("Se ha pulsado el boton de registrar");

		boolean add = true;

		if ((window.NombreP.getText().equals("")) || (window.Apellido1.getText().equals(""))
				|| (window.Apellido2.getText().equals("")) || (window.DNI.getText().equals(""))
				|| (window.Habitacion.getText().equals(""))) {// Comprobamos
			// si algum
			// campo esta
			// vacio

			add = false;
			window.lberror.setText("Hay campos vacios");
			log.InfoLog("Error, no se pudo introducir el paciente, hay campos vacios");

		} else if(window.jcbMedic.getSelectedItem()==null||window.jcbNurse.getSelectedItem()==null) {
			add = false;
			window.lberror.setText("No hay medicos/enfermeros disponibles");
			log.InfoLog("Error, no se pudo introducir el paciente, no hay medicos/enfermeros disponibles");

		}else if (dao.checkIfRoomIsBusy(Integer.parseInt(window.Habitacion.getText()))) {
			add = false;
			window.lberror.setText("Esa habitacion no est� disponible, proxima: "+ dao.firstRoomFree());
			log.InfoLog("Error, no se pudo introducir el paciente, habitaci�n ocupada");

		}else{
			window.lberror.setText("");
		}
		
//TOOD: comprob
//TOOD: comprobar haitacion unica 
		if (add) {// Si da error no se a�ade el empleado
			System.out.println("Correcto");

			//int id = dao.getLastID()+1;//siguiente id
			int id = dao.firstIdFree();

			
			Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual
			int idMedic=0,idNurse=0;
			try {
				window.jcbMedic.getSelectedIndex();
				
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
				System.out.println("id medico: "+ idMedic+" id Enfermero: "+idNurse);
				

				dao.addPatient(id, window.NombreP.getText(), window.Apellido1.getText(),
						window.Apellido2.getText(), window.DNI.getText(), date,
						Integer.parseInt(window.Habitacion.getText()),window.textEnfermedad.getText(),idMedic,idNurse);
				log.InfoLog("A�adido el paciente con id: "+id);
												
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
				log.InfoLog("Error, no se selecion� un paciente");
	}else {
		window.lblErrorDelete.setText("");

	}
		
		
		if(add) {
		//comprueba si se introduce un dni o numero de habitacion;
		

			boolean isDniOrRoom = isDni(window.textFieldDNIToDelete.getText().toString());

			if (!dao.checkPatientExist(window.textFieldDNIToDelete.getText().toString(),isDniOrRoom)) {
				add=false;
				window.lblErrorDelete.setText("DNI erroneo");
				log.InfoLog("Error, DNI o habitación erroneos");
			}else {
				
				//getPatientDNI=dao.getPatientDNI(window.textFieldDNIToDelete.getText().toString());
				//arrayMedicine.add(getPatientDNI);
				
				/*
				Iterator<String[]> it = arrayPacientes.iterator();
				while(it.hasNext())
				 System.out.println(it.next());
				*/
					
					dao.deletePatient(window.textFieldNameToDelete.getText().toString(),
					window.textFieldFirstDeleteToDelete.getText().toString(),
					window.textFieldSecondDeleteToDelete.getText().toString(), window.textFieldDNIToDelete.getText().toString());
					window.lblErrorDelete.setText("");

			}
		}
		
	
	}else if (arg0.getActionCommand().equals("Asignar Medicamento Paciente")) {
		
		window.seePacientsPanel.setVisible(false);
		window.addPatientsPanel.setVisible(false);
		window.deletePatientsPanel.setVisible(false);
		window.getPatientPane.setVisible(false);
		window.addMedicine.setVisible(true);
		window.week.setVisible(false);

	
	}else if (arg0.getActionCommand().equals("Asignar")) {//probablemente haya que hacer un comprobarmedicamento, pro pal final
		
		int n,m;
		boolean add=true;
		
		int idMedicine=0;
		window.jcbMedic.getSelectedIndex();
		for (int i = 0; i < arrayMedicine.size(); i++) {
			if (window.jcbMedicine.getSelectedItem().toString().equals(arrayMedicine.get(i)[1])) {
				idMedicine=Integer.parseInt(arrayMedicine.get(i)[0]);;
			}
		}
		
		//habria que cambiarlo con el JBox, el cual aun no funciona
		if ((window.DNIM.getText().toString().equals("")) || (window.units.getText().toString().equals(""))){
			add=false;
			window.lblError2.setText("Hay campos vacios");
			log.InfoLog("campos vacios al asignar medicamento");
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
				
						//daoAlmacen.restMedicine(Integer.parseInt(window.units.getText().toString()), /Integer.parseInt(window.Medicine.getText().toString()));
						daoAlmacen.restMedicine(Integer.parseInt(window.units.getText().toString()),idMedicine);//,idMedic);
						window.lblError2.setText("");

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
		
		//para ver ue pacientes hay
		/*
		Iterator<String[]> it = arrayPacientes.iterator();
		while(it.hasNext())
		 System.out.println(it.next());
		*/
		
		//copiad de ver pacientes, mi idea es con un arraylist guardar los eliminados y hacer una tabla
		/*ArrayList<String[]> insert = null;

		String[] titles = null;

		String[][] matrixToInsert = null;

		titles = new String[] { "  Id", "Nombre", "Apellido 1", "Apellido 2", "NIF", "Fecha", "Habitación",
				"Enfermedad", "Producto", "Medico", "Unidades medicamento", "Enfermero " }; // Titulos de la tabla de
																// los empleados
		insert = dao.getAllPatients();// ArrayList de Arrays
	
		matrixToInsert = new String[insert.size() + 1][12];
		window.seePacientsPanel.setPreferredSize(new Dimension(624, 20 + 20 * insert.size()));
		window.seePacientsPanel.setBounds(284, 11, 624, 20 + 20 * insert.size());
		
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
		window.seePacientsPanel.add(PatientsTable);
		PatientsTable.setAutoscrolls(true);
		

		
		DefaultTableModel tableModel = new DefaultTableModel(matrixToInsert, titles);
		PatientsTable.setModel(tableModel);
		
		*/
		
		
    }else if (arg0.getActionCommand().equals("Cerrar Sesión")) {

				window.setVisible(false);
				//TODO arreglar que se borren los campos al cerrar sesion
				try {
					LoginWindow newlogin = new LoginWindow();
					ControlerLoginWindow controlerLogin = new ControlerLoginWindow(newlogin);
					controlerLogin.resetJField();
				} catch (IOException e) {
					e.printStackTrace();
				}
		
	
        	}
	}

}
