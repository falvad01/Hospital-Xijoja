package es.unileon.xijoja.hospital;

import java.io.*;
import java.util.Calendar;

public class Logs {

	FileWriter archivo;
	// nuestro archivo log

	public void InfoLog(String Operacion) {
		try {
			// Pregunta el archivo existe, caso contrario crea uno con el nombre log.txt
			if (new File("etc/log.txt").exists() == false) {

				System.out.println("No exixste el archivo");
				archivo = new FileWriter(new File("etc/log.txt"), false);

			}

			archivo = new FileWriter(new File("etc/log.txt"), true);

			Calendar fechaActual = Calendar.getInstance(); // Para poder utilizar el paquete calendar

			String clas = new Exception().getStackTrace()[1].getClassName();// Obtenemos la calse que llama al log
			
			String[] parts = clas.split("hospital.");// nos uqedamos con la ultima parte

			// Empieza a escribir en el archivo
			archivo.write("["
					+ (String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH)) + "/"
							+ String.valueOf(fechaActual.get(Calendar.MONTH) + 1) + "/"
							+ String.valueOf(fechaActual.get(Calendar.YEAR)) + " "
							+ String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY)) + ":"
							+ String.valueOf(fechaActual.get(Calendar.MINUTE)) + ":"
							+ String.valueOf(fechaActual.get(Calendar.SECOND)))
					+ "]" + "[INFO-" + parts[1].toUpperCase() + "] " + Operacion + "\r\n");

			archivo.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		} // Se cierra el archivo
	}

}
// Fin del metodo InfoLog
