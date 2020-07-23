package com.travelservice.db;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "travel_weather")
public class WeatherEntity {

	@Id
	private Integer id;
		
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@Column(name="date")
	private Date date;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", nullable = true)	
	private LocationEntity location;

	@Column(name="temp")
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

	public String[] getTemperature() {
		return temperature;
	}

	public void setTemperature(String[] temp) {
		this.temperature = temp;
	}

	public LocationEntity getLocation() {
		return location;
	}

	public void setLocation(LocationEntity loc) {
		this.location = loc;
	}
}
