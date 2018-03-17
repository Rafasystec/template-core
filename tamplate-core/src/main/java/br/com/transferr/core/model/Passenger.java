package br.com.transferr.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="PASSENGER")
public class Passenger extends Entidade{

	private static final long serialVersionUID = -5636558642897676931L;
	@Column(name="NAME",length=160)
	private String name 	= "";
	@Column(name="TOKEN",length=300)
	private String token 	= "";
	@Column(name="DT_NASCIMENTO")
	private Integer dtNascimento;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Integer dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	
	
	
}
