package com.example.PhoneBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "directory")
public class Directory {

	@Id
	private Long number;
	
	private String name;
	
	private String address;
	
	private String occupation;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Directory(Long number, String name, String address, String occupation) {
		super();
		this.number = number;
		this.name = name;
		this.address = address;
		this.occupation = occupation;
	}

	@Override
	public String toString() {
		return "Directory [number=" + number + ", name=" + name + ", address=" + address + ", occupation=" + occupation
				+ "]";
	}

	

	
	
	
}
