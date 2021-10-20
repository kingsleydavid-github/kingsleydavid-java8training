package com.enfec.test.enfectest.model;

import java.io.Serializable;

import org.springframework.stereotype.Component;

class Address implements Serializable{
	
	public Address() {}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSuit() {
		return suit;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Geo getGeo() {
		return geo;
	}
	public void setGeo(Geo geo) {
		this.geo = geo;
	}
	private String street;
	private String suit;
	private String city;
	private String zipcode;
	private Geo geo;
	
	public Address(String street, String suit, String city, String zipcode, Geo geo) {
		super();
		this.street = street;
		this.suit = suit;
		this.city = city;
		this.zipcode = zipcode;
		this.geo = geo;
	}
	
	
}
