package com.devsu.banking.customers.domain.model;

public class Person {

	private String name;
	private String gender;
	private int age;
	private String identification;
	private String address;
	private String phoneNumber;

	public Person() {
	}

	public Person(String name, String gender, int age, String identification, String address, String phoneNumber) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.identification = identification;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
