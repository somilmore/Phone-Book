package com.example.PhoneBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "directory")
public class Directory{

	@Id
	private Long number;
	
	private String name;

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



	@Override
	public String toString() {
		return "Directory [number=" + number + ", name=" + name + "]";
	}

	public Directory(Long number, String name) {
		super();
		this.number = number;
		this.name = name;
	}

	
}
