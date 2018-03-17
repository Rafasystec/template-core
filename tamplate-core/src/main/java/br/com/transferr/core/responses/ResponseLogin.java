package br.com.transferr.core.responses;

import br.com.transferr.core.model.User;

public class ResponseLogin {

	private User user;
	private String token;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
