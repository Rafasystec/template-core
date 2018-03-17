package br.com.transferr.core.responses;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.MappedSuperclass;
import javax.persistence.SqlResultSetMapping;
@SqlResultSetMapping(
		name=ResponsePassengersOnline.NAME,
		classes = @ConstructorResult(
			targetClass = ResponsePassengersOnline.class,
			columns		= {
					@ColumnResult(name="id"					,type=Long.class),
					@ColumnResult(name="latitude"			,type=Double.class),
					@ColumnResult(name="longitude"			,type=Double.class)
					
			}
		)	
	)
@MappedSuperclass
public class ResponsePassengersOnline {

	public static final String NAME = "br.com.transferr.core.responses.ResponsePassengersOnline";
	private Long id;
	private Double latitude;
	private Double longitude;
	private String distance;
	public ResponsePassengersOnline(Long id, Double latitude, Double longitude) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	
	
	
	
}
