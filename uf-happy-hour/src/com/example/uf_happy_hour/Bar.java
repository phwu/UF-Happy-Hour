package com.example.uf_happy_hour;

public class Bar {
	private String name;
	private String address;
	private int phoneNumber;
	private String venueType;
	
	public Bar() {
		this.name = "Error";
		this.address = "nil";
		this.phoneNumber = -1;
		this.venueType = "";
		// .... etc.
	}
	
	public void setBarInfo(String name, String address, int phoneNumer, String venue /*....*/) {
		this.name = name;
		this.address = address;
		// .... etc.
	}
	
	public Bar getBarInfo() {
		return this;
	}
	
	public String toString() {
		return "";
	}

}
