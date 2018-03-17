package br.com.transferr.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COORDINATE_PASS")
public class CoordinatePassenger  extends Entidade{

	public CoordinatePassenger() {
		
	}
	private static final long serialVersionUID = 3580158683573139918L;

	@Column(name = "latitude")
	private Double latitude;
	
	@Column(name = "longitude")
	private Double longitude;
	
	@Column(name="TOKEN",length=300)
	private String token 	= "";
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}

	@ManyToOne
	@JoinColumn(name = "ID_PASS" ,referencedColumnName="ID")
	private Passenger passenger;
	
	

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
}