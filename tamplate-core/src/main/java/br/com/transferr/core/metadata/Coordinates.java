package br.com.transferr.core.metadata;

public class Coordinates {

	public Coordinates() {
	}
	
	public Coordinates(double longitude, double latitude) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
	}

	private long id;
	private double longitude;
	private double latitude;
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
