package es.unileon.xijoja.hospital;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	Logs archivo = new Logs(); //Instancia de la clase para utilizar sus metodos

	private String addressee;
	private String subject;
	private String body;

	/**
	 * 
	 * @param addressee
	 * @param subject
	 * @param body
	 */
	public Email(String addressee, String subject, String body) {

		this.addressee = addressee;
		this.subject = subject;
		this.body = body;

	}

	/**
	 * Metodo para enviar un correo a los empleados dados de alta
	 * 
	 * @param destinatario A quien le enviamos el correo
	 * @param asunto       //Ausinto del correo
	 * @param cuerpo       //Mensaje del correo
	 * @throws IOException
	 */
	public void send() throws IOException {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "hospitalxijoja"; // Para la dirección nomcuenta@gmail.com

		InputStream is = getClass().getResourceAsStream("/documents/password.txt"); // lee el fichero txt de contraseñas
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String password;
		password = br.readLine();

		Properties props = System.getProperties();
		System.out.println(password);
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", password); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, this.addressee);
			message.setSubject(this.subject);
			message.setText(this.body);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
		}

	}

}
