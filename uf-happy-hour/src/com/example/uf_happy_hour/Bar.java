package com.example.uf_happy_hour;


// Make Bar similar to Contacts Class

public class Bar {
	private String name;
	private String address;
	private String phoneNumber;
	private String venueType;
	private String hoursOpen;
	private String specials;
	private int ageReq;
	private int food;
	private String alcohol;
	
	public Bar() {
		this.name = "Error";
		this.address = "nil";
		this.phoneNumber = "-1";
		this.venueType = "";
		this.hoursOpen = "5pm-7pm";
		this.specials = "None";
		this.ageReq = 21;
		this.food = 0;
		this.alcohol = "Beer/Wine only";
	}

	public Bar(String name, String address, String phoneNumber, String venue, String hoursOpen, String alcohol, String specials, int ageReq, int food) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.venueType = venue;
		this.hoursOpen = hoursOpen;
		this.specials = specials;
		this.ageReq = ageReq;
		this.food = food;
		this.alcohol = alcohol;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String number) {
		this.phoneNumber = number;
	}
	
	public String getVenue() {
		return this.venueType;
	}
	
	public void setVenue(String venue) {
		this.venueType = venue;
	}
	
	public String getHoursOpen() {
		return this.hoursOpen;
	}
	
	public void setHoursOpen(String hours) {
		this.hoursOpen = hours;
	}
	
	public String getSpecials() {
		return this.specials;
	}
	
	public void setSpecials(String specials) {
		this.specials = specials;
	}
	
	public int getAgeReq() {
		return this.ageReq;
	}
	
	public void setAgeReq(int age) {
		this.ageReq = age;
	}
	
	public int getFood() {
		return this.food;
	}
	
	public void setFood(int food) {
		this.food = food;
	}
	
	public String getAlcohol() {
		return this.alcohol;
	}
	
	public void setAlcohol(String alcohol) {
		this.alcohol = alcohol;
	}

}
