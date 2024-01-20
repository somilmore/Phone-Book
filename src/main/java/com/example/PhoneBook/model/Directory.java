package com.example.PhoneBook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity(name = "directory")
public class Directory{

	@Id
	private int id;
	
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Directory [id=" + id + ", number=" + number + ", name=" + name + "]";
	}

	public Directory(int id, String name, Long number) {
		super();
		this.id = id;
		this.number = number;
		this.name = name;
	}

	public Directory() {
		super();
	}



	

}
