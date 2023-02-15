package org.antwalk.model;

public class User {

	String name;
	String gender;
	String country;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public User(String name, String gender, String country) {
		super();
		this.name = name;
		this.gender = gender;
		this.country = country;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [name=" + name + ", gender=" + gender + ", country=" + country + "]";
	}
	
}
