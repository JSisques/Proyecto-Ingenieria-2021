package entity;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	
	private String destiny;
	private String subject;
	private String body;
	private String sender;
	private String password;
	private int port;
	private boolean auth;
	
	
	public boolean sendEmail(String destiny) {
		boolean response = false;
		
		Properties p = new Properties();
		
		if (destiny.isEmpty() || destiny == null) {
			System.err.println("Sin correo electrónico");
		} else {
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.user", "usuario");
			p.put("mail.smtp.clave", "ProyectoIngenieria2021");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.port",587);
			
			Session sesion = Session.getDefaultInstance(p);
			MimeMessage message = new MimeMessage(sesion);

			try {

				message.setFrom(new InternetAddress("Listphy"));
				message.addRecipients(Message.RecipientType.TO, destiny);
				message.setSubject("Datos personales de su cuenta");
				message.setText("Prueba de envio");
				Transport transport = sesion.getTransport("smtp");
				transport.connect("smtp.gmail.com", "Listphy", "ProyectoIngenieria2021");
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
				
				response = true;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return response;
		
	}

}
