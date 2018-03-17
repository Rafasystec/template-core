package br.com.transferr.core.role;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.transferr.core.dao.UserDAO;
import br.com.transferr.core.exceptions.ValidationException;
import br.com.transferr.core.mail.MailException;
import br.com.transferr.core.model.User;
import br.com.transferr.core.responses.ResponseLogin;

@Service
public class RoleUser extends RoleSuperClass<User> {

	
	public RoleUser() {
		System.out.println(this.getClass().getName());
	}
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private RoleEmail roleEmail;
	
	
	
	
	@Override
	public User insert(User entidade) throws ValidationException {
		return userDAO.insert(entidade);
	}

	@Override
	public void delete(long codigo) throws ValidationException {
		userDAO.delete(codigo);
	}

	@Override
	public User update(User entidade) throws ValidationException {
		return userDAO.update(entidade);
	}

	@Override
	public User find(long codigo) throws ValidationException {
		return userDAO.find(codigo);
	}
	
	
	@Transactional(readOnly=true)
	public ResponseLogin doLogin(String email, String password) throws ValidationException {
		ResponseLogin responseLogin = new ResponseLogin();
		User user = getUserLogin(email, password);
		if(user != null && user.getId() > 0){
			responseLogin.setUser(user);
		}else{
			throw new ValidationException("Usuário não cadastrado!");
		}
		return responseLogin;
	}
	public User getUserLogin(String email, String password) {
		String senhaCripty = encryptPasswordOrEmail(password);
		return this.userDAO.getUsuarioLogin(email, senhaCripty);
	}
	
	
	// TODO
	public String encryptPasswordOrEmail(String password) {
		//HelperEncrypt encrypt = new HelperEncrypt();
		//encrypt.setKeyVar("h2R45X7x9r1X8888");
		//encrypt.setVetVar("123XTY789HIJ34XX");
		//return encrypt.encryptVar(password);
		return  password;
	}
	
	
	public String recoverPassWord(String email) throws ValidationException {
		if(email == null || email.trim().isEmpty()){
			throw new ValidationException("O E-mail de recuperação de senha NÃO deve estar vazio!");
		}
		User user = findUserByEmail(email.trim());
		if(user != null){
			String senhaCript     = user.getPassword();
			String decripted      = decryptPasswordOrEmail(senhaCript);
			String conteudo	      = emailContent(user,decripted);
			String AssuntoDoEmail = "Recuperação de Senha iDoctor Brasil.";
			try {
				roleEmail.sendEmailRecoverSenha(email, AssuntoDoEmail, conteudo);
			} catch (MailException e) {
				e.printStackTrace();
				throw new ValidationException("Erro ao enviar o email de recuperação de senha: "+e.getMessage());
			}
			return "Um e-mail foi enviado pra você.";
		}else{
			throw new ValidationException("Usuário "+ email + " NÃO cadastrado na base de dados!");
		}
}
	
	public User findUserByEmail(String email) {
		return userDAO.getUsuarioEmail(email);
	}

	private String emailContent(User user,String password) {
		StringBuilder content = new StringBuilder("");

		content.append("Estamos lhe enviando este e-mail porque você solicitou a recuperação da sua senha. Por favor Não precisa responder este e-mail nem tampouco agradecer.").append("\n\n")
		.append("Sua senha é:     "+	password).append("\n\n\n")
		.append("Pedimos que você memorize sua senha e depois apague este e-mail somente por segurança");
		return content.toString();
}
	
	//TODO
	public String decryptPasswordOrEmail(String password) {
//		HelperEncrypt encrypt = new HelperEncrypt();
//		encrypt.setKeyVar("h2R45X7x9r1X8888");
//		encrypt.setVetVar("123XTY789HIJ34XX");
//		String firstPart = encrypt.decryptVar(password);
//		return HelperEncrypt.decrypt(firstPart) ;
		return password;
	}
	
	
	public void changePassword(long idUser, String actualPassword, String newPassword) throws ValidationException {
		if(actualPassword == null || newPassword == null){
			throw new ValidationException("Dados inválidos para redefinir a senha! Veja se a senha antiga ou a nova foram informadas corretamente.");
		}
		if(actualPassword == null || newPassword.trim().isEmpty()){
			throw new ValidationException("A nova senha é inválida! Ela não pode está em branco.");
		}
		User user = find(idUser);
		if(user != null){
			String senhaAntiga = user.getPassword().trim();
			String criptSenha  = encryptPasswordOrEmail(actualPassword).trim();
			String criptSenhaNova = encryptPasswordOrEmail(newPassword.trim()).trim();
			if(senhaAntiga.equals(criptSenha)){
				user.setPassword(criptSenhaNova);
				update(user);
			}else{
				throw new ValidationException("A senha não confere com a informada! Verifique se ela está correta e tente novamente.");
			}
			update(user);
		}else{
			throw new ValidationException("Usuário não cadastrado na base de dados.");
		}
}
	
	

}