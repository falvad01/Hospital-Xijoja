package es.unileon.xijoja.hospital;


import java.io.*;
import java.util.Calendar;

	public class Logs {

		String dir;

		FileWriter archivo;
		//nuestro archivo log

		public void InfoLog(String Operacion,String dir) throws IOException{

			this.dir = dir;
			//Pregunta el archivo existe, caso contrario crea uno con el nombre log.txt
			if (new File(dir+"//"+"log.txt").exists()==false){archivo=new FileWriter(new File(dir +"//"+"log.txt"),false);}
			archivo = new FileWriter(new File(dir +"//"+"log.txt"), true);
			Calendar fechaActual = Calendar.getInstance(); //Para poder utilizar el paquete calendar
			//Empieza a escribir en el archivo
			archivo.write("["+(String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH))
					+"/"+String.valueOf(fechaActual.get(Calendar.MONTH)+1)
					+"/"+String.valueOf(fechaActual.get(Calendar.YEAR))
					+" "+String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY))
					+":"+String.valueOf(fechaActual.get(Calendar.MINUTE))
					+":"+String.valueOf(fechaActual.get(Calendar.SECOND)))+"]"+"[INFO]"+ " " +Operacion+"\r\n");
			archivo.close(); //Se cierra el archivo
		}
		
		
	}
		//Fin del metodo InfoLog
