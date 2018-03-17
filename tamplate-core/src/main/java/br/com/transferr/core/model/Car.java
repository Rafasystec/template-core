package br.com.transferr.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.transferr.core.enums.EnumStatus;



@NamedQueries({
	@NamedQuery(name=Car.FIND_BY_DRIVER,query="FROM Car c WHERE c.driver.id = :idDriver")
})



@Entity
@Table(name="Car")
public class Car  extends Entidade{
	
	public static final String FIND_BY_DRIVER   = "br.com.transferr.core.model.User.findByDriver";
	
	
	@Column(name = "photo")
	private String photo;
	
	@Column(name = "model")
	private String model;
	
	@Column(name = "car_identity")
	private String carIdentity;
	
	@Column(name = "color")
	private String color;
	
	@Column(name = "fl_external_Equip")
	private Boolean externalEquip;
	
	@OneToOne
	@JoinColumn(name = "ID_DRIVER",referencedColumnName="ID",nullable=true)
	private Driver driver;
	
	@Column(name="STATUS",nullable=false)
	@Enumerated(EnumType.ORDINAL)
	private EnumStatus status;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCarIdentity() {
		return carIdentity;
	}

	public void setCarIdentity(String carIdentity) {
		this.carIdentity = carIdentity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Boolean getExternalEquip() {
		return externalEquip;
	}

	public void setExternalEquip(Boolean externalEquip) {
		this.externalEquip = externalEquip;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public EnumStatus getStatus() {
		return status;
	}

	public void setStatus(EnumStatus status) {
		this.status = status;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
	
}