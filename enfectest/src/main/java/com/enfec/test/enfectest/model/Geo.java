package com.enfec.test.enfectest.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

public class Geo implements Serializable {
	
	public Geo() {}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	private Double lat;
	private Double lng;
	public Geo(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	
	
}
