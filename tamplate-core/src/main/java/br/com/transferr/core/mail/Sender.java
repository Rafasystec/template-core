package br.com.transferr.core.mail;

public class Sender {

	private ISender iSender;
	
	public Sender(int port, String host, String uname, String pws) {
		iSender = new SenderMail();
		iSender.setPassword(pws);
		iSender.setUserName(uname);
		iSender.setHostEmail(host);
		iSender.setSmtpPort(port);
	}
	
	public void send(String content, String subject, String from, String to) throws MailException{
		iSender.sendEmail(content, subject, from, to);
	}

}