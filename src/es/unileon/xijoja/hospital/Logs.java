package es.unileon.xijoja.hospital;


import java.io.*;
import java.util.Calendar;

	public class Logs {

		

		FileWriter archivo;
		//nuestro archivo log

		public void InfoLog(String Operacion){

			
			//Pregunta el archivo existe, caso contrario crea uno con el nombre log.txt
			if (new File("log.txt").exists()==false){try {
				archivo=new FileWriter(new File("log.txt"),false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
			try {
				archivo = new FileWriter(new File("log.txt"), true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Calendar fechaActual = Calendar.getInstance(); //Para poder utilizar el paquete calendar
			//Empieza a escribir en el archivo
			try {
				archivo.write("["+(String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH))
						+"/"+String.valueOf(fechaActual.get(Calendar.MONTH)+1)
						+"/"+String.valueOf(fechaActual.get(Calendar.YEAR))
						+" "+String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY))
						+":"+String.valueOf(fechaActual.get(Calendar.MINUTE))
						+":"+String.valueOf(fechaActual.get(Calendar.SECOND)))+"]"+"[INFO]"+ " " +Operacion+"\r\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				archivo.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //Se cierra el archivo
		}
		
		
	}
		//Fin del metodo InfoLog
