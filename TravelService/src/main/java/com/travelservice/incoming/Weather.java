package com.travelservice.incoming;


import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({"id", "date","location", "temperature" })
public class Weather {

	private Integer id;
			
	private Date date;
	
	private Location location;

	private String[] temperature;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location loc) {
		this.location = loc;
	}

	public String[] getTemperature() {
		return temperature;
	}

	public void setTemperature(String[] temp) {
		this.temperature = temp;
	}
	
	public String toString() {
		return "Weather("
		+ " Id=" + this.getId()
		+ ", Date=" + Objects.toString(this.getDate()) 
		+ this.getLocation().toString() 
		+ ", Temperature=" + Arrays.toString(this.getTemperature())
        +")";		
	}
}
