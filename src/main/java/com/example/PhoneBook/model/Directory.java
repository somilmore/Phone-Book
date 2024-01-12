package com.example.PhoneBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name = "directory")
public class Directory{

	@Id
	private Long number;
	
	private String name;
	
	private String education;

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



	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	@Override
	public String toString() {
		return "Directory [number=" + number + ", name=" + name + ", education=" + education + "]";
	}

	public Directory(Long number, String name, String education) {
		super();
		this.number = number;
		this.name = name;
		this.education = education;
	}

	

	

	
	
	
}
