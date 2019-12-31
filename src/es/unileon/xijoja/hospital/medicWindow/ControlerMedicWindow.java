package es.unileon.xijoja.hospital.medicWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import es.unileon.xijoja.hospital.Logs;


public class ControlerMedicWindow implements ActionListener {

	private MedicWindow window;
	private Logs log;
	//TODO poner aqui el dao en privado

	public ControlerMedicWindow(MedicWindow window) {

		this.window = window;
		log = new Logs();
		//TODO inicializar aqui el dao
	}

	public void actionPerformed(ActionEvent e) {
		// TODO aqui van las acciones al pulsar botones
		

	}

}
