package br.com.transferr.core.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="driver")
public class Driver  extends Entidade{

	@Column(name = "name")
	private String name;
	
	@Column(name = "country_register")
	private String countryRegister;

	@Column(name = "birth_date")
	private Integer birthDate;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER" ,referencedColumnName="ID")
	private User user;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryRegister() {
		return countryRegister;
	}

	public void setCountryRegister(String countryRegister) {
		this.countryRegister = countryRegister;
	}

	public Integer getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}