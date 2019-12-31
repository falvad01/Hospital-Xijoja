package es.unileon.xijoja.hospital.secretary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import es.unileon.xijoja.hospital.PacientesDAO;

public class ControlerSecretaryWindow implements ActionListener {
	
	private SecretaryWindow window;
	private PacientesDAO dao;
	public ControlerSecretaryWindow(SecretaryWindow window) {
		this.dao = new PacientesDAO();
		this.window = window;
	}
	
	
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals("A�adir")) {////////////////////////////////// A�adir

			boolean add = true;

			if ((window.textFieldName.getText().equals("")) || (window.textFieldSurname1.getText().equals(""))
					|| (window.textFieldSurname2.getText().equals("")) || (window.textFieldNIFNIE.getText().equals(""))
					|| (window.textFieldHabitacion.getText().equals(""))) {// Comprobamos
				// si algum
				// campo esta
				// vacio

				add = false;
				window.lblError.setText("Hay campos vacios");
			} else {
				window.lblError.setText("");
			}

			if (add) {// Si da error no se a�ade el empleado
				System.out.println("Correcto");

				// TODO getlast id?� para que�?

				int id = dao.getLastID();

				Date date = new Date(Calendar.getInstance().getTime().getTime());// Obtenemos la fecha actual

				try {

					dao.addPaciente(id, window.textFieldName.getText(), window.textFieldSurname1.getText(),
							window.textFieldSurname2.getText(), window.textFieldNIFNIE.getText(), date,
							Integer.parseInt(window.textFieldHabitacion.getText()));// LLamamos a la
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

			} else if (arg0.getActionCommand().equals("Buscar")) {

				/*
				 * TODO busqueda por nombre y por habitacion if
				 * (Character.isDigit(textFieldSearch.getText().charAt(0))) {
				 * 
				 * System.out.println("Busqueda por DNI"); try { String[] employee =
				 * dao.getPaciente(textFieldSearch.getText().toString());
				 * textFieldNameEdit.setText(employee[0]);
				 * textFieldSurname1Edit.setText(employee[1]);
				 * 
				 * } catch (SQLException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); }
				 * 
				 * } else {
				 * 
				 * System.out.println("Busqueda por nombre y apellidos"); }
				 */

			}
		}
	}

}
