package com.example.PhoneBook.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PhoneBook.model.Directory;
import com.example.PhoneBook.repository.PhoneRepository;
import com.example.PhoneBook.service.PhoneService;

@RestController
@RequestMapping("/directory")
public class HomeController {
	
	@Autowired
	PhoneRepository repository;
	@Autowired
	PhoneService service;
	
	@PostMapping("/create")
	public ResponseEntity<String> create(@RequestBody Directory entry) {
		
		return ResponseEntity.ok(service.createRecord(entry));
	}
	
	@GetMapping("/get")
	public String getAll() {
		List<Directory> list = repository.findAll();
		
		if(list.size()==0)
			return "Database is empty!";
			
		List<JSONObject> ans = service.getList(list);
		
		return ans.toString();
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Directory entry) {
		
		return ResponseEntity.ok(service.updateRecord(entry));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable int id ) {
		
		return ResponseEntity.ok(service.deleteRecord(id));
	}
}
