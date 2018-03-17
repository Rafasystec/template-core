package br.com.transferr.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;


@NamedQueries({
	@NamedQuery(name=User.FIND_BY_LOGIN,query="FROM User u WHERE u.email = :email AND u.password = :password"),
	@NamedQuery(name=User.FIND_BY_EMAIL,query="FROM User u WHERE u.email = :email")
})


@Entity
@Table(name="USUARIO")
public class User  extends Entidade{
	
	@Transient
	private static final long serialVersionUID = -5830369890413201493L;
	public static final String FIND_BY_LOGIN   = "br.com.transferr.core.model.User.findByLogin";
	public static final String FIND_BY_EMAIL = "br.com.transferr.core.model.User.findByEmail";

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
