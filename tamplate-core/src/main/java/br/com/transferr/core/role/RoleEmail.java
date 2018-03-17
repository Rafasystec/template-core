package br.com.transferr.core.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.transferr.core.mail.MailException;
import br.com.transferr.core.mail.Sender;


@Service
public class RoleEmail {
	
	@Autowired
	private RoleParametros roleParametros;
	
	
	public void sendEmailRecoverSenha(String emailDestino, String AssuntoDoEmail, String conteudo) throws MailException {
		int port 		= roleParametros.getPortHostEmail();
		String host		= roleParametros.getHostEmail();
		String uname	= roleParametros.getUsuarioEmail();
		String pws		= roleParametros.getSenhaEmail();
		Sender sender = new Sender(port, host, uname, pws);
		sender.send(conteudo, AssuntoDoEmail, roleParametros.getEnderecoEnvioEmail(), emailDestino);
	}
	
	public void sendEmailSuporteIdoctor(String emailDestino, String AssuntoDoEmail, String conteudo) throws MailException {
		int port 		= roleParametros.getPortHostEmail();
		String host		= roleParametros.getHostEmail();
		String uname	= roleParametros.getUsuarioEmail();
		String pws		= roleParametros.getSenhaEmail();
		Sender sender = new Sender(port, host, uname, pws);
		sender.send(conteudo, AssuntoDoEmail, roleParametros.getEnderecoEmailSuporte(), emailDestino);
	}

}