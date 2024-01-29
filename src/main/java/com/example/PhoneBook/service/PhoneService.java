package com.example.PhoneBook.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PhoneBook.model.Directory;
import com.example.PhoneBook.repository.PhoneRepository;

@Service
public class PhoneService {
	
	@Autowired
	PhoneRepository repository;
	
	public String createRecord(Directory entry) {
		
		int id = entry.getId();
		Optional<Directory> record = repository.findById(id);
		
		if(record.isPresent())
			return "Record is already present";
		
		repository.save(entry);
		return "Record added successfully";
	}
	
	public List<JSONObject> getList(List<Directory> list){
		
		List<JSONObject> ans = new ArrayList<>();
		
		for(int i=0;i<list.size();i++) {
			JSONObject response = new JSONObject();
			
			
			Directory row = list.get(i);
			
			response.put("ID", row.getId());
			response.put("Name", row.getName());
			response.put("Phone Number", row.getNumber());
			
			ans.add(response);
		}
		
		return ans;
	}
	
	public String updateRecord(Directory entry) {
	    Optional<Directory> record = repository.findById(entry.getId());

	    if (!record.isPresent()) {
	        return "Record does not exist";
	    }

	    repository.save(entry);
	    return "Record Updated";
	}

	
	public String deleteRecord(int id) {
		repository.deleteById(id);
		
		return "Record deleted successfully";
	}
}
