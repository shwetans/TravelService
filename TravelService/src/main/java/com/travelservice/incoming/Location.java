package com.travelservice.incoming;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"lat", "lon","city", "state" })
public class Location {
	
	private Float latitude;
	
	private Float longitude;
	
	private String city;

	private String state;

	public Float getLat() {
		return latitude;
	}

	public void setLat(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLon() {
		return longitude;
	}

	public void setLon(Float longitude) {
		this.longitude = longitude;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public String toString() {
		return " Location ("
		+ " Latitude=" + Objects.toString(this.getLat())
		+ ", Longitude=" + Objects.toString(this.getLon()) 
		+ ", City=" + this.getCity() 
		+ ", State=" + this.getState() 
        +")";		
	}
	
}
