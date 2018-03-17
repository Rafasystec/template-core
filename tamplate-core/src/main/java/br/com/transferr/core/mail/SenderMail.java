package br.com.transferr.core.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

class SenderMail implements ISender {

	private String fromEmail;
	private String destinationEmail;
	private String password;
	private String userName;
	private String hostEmail;
	private Session sessionEmail;
	private int smtpPort = 25;
	
	/**
	 * Send a message email.
	 * @param content : Conteudo do email
	 * @param subject : Assunto do email
	 * @param from : email de origente (remetente)
	 * @param to : email de destino (destinatario)
	 * @throws MailException
	 */
	public void sendEmail(String content, String subject,  String from, String to) throws MailException {
		setFromEmail(from);
		setDestinationEmail(to);
		Message message = new MimeMessage(configure());
		try {
			message.setFrom(new InternetAddress(getFromEmail()));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(getDestinationEmail()));
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException e) {
			throw new MailException("Erro ao tentar enviar email para : " + getDestinationEmail() + " > " + e.getMessage());
		}

	}

	/**
	 * Realiza a configuração do email.
	 */
	public Session configure() throws MailException {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.user", getUserName());
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", getHostEmail());
		props.put("mail.smtp.port", getSmtpPort());
		props.put("mail.smtp.socketFactory.port", getSmtpPort());  
	    props.put("mail.smtp.socketFactory.fallback", "false");  
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getUserName(), getPassword());
					}
				});
		
		setSessionEmail(sessionEmail);
		return session;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHostEmail() {
		return hostEmail;
	}

	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}

	public Session getSessionEmail() {
		return sessionEmail;
	}

	public void setSessionEmail(Session sessionEmail) {
		this.sessionEmail = sessionEmail;
	}

	public int getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}

}