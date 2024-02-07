package com.example.PhoneBook.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.PhoneBook.model.Directory;
import com.example.PhoneBook.repository.PhoneRepository;

public class PhoneServiceTest {

    @Mock
    private List<Directory> mockDirectoryList;

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository mockRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    @Test
    public void getList() throws JSONException {
    	  
    	  Directory directory1 = new Directory(1, "John", 1234567890L);
          Directory directory2 = new Directory(2, "Jane", 9876543210L);

          // Mocking the behavior of the repository findAll method
          when(mockDirectoryList.size()).thenReturn(2);
          when(mockDirectoryList.get(0)).thenReturn(directory1);
          when(mockDirectoryList.get(1)).thenReturn(directory2);

          // Testing the getList method
          List<JSONObject> result = phoneService.getList(mockDirectoryList);

          // Asserting the result
          assertEquals(2, result.size());

          JSONObject entry1 = result.get(0);
          assertEquals(1, entry1.getInt("ID"));
          assertEquals("John", entry1.getString("Name"));
          assertEquals(1234567890L, entry1.getLong("Phone Number"));

          JSONObject entry2 = result.get(1);
          assertEquals(2, entry2.getInt("ID"));
          assertEquals("Jane", entry2.getString("Name"));
          assertEquals(9876543210L, entry2.getLong("Phone Number"));
    	
    }

	    @Test
	    public void testCreateRecord() throws JSONException {
	    	  
	    	 Directory directory = new Directory();
	         directory.setId(1);
	         directory.setName("Test Name");
	         directory.setNumber(1234567890L);

	         when(mockRepository.save(directory)).thenReturn(directory);
	         String response = phoneService.createRecord(directory);

	         Assertions.assertThat(response).isNotNull();
	    }
	    
	    @Test
	    public void testUpdateRecord() throws JSONException {
	    	  
	    	 Directory directory = new Directory();
	         directory.setId(1);
	         directory.setName("Test Name");
	         directory.setNumber(1234567890L);

	         when(mockRepository.save(directory)).thenReturn(directory);
	         String response = phoneService.updateRecord(directory);

	         Assertions.assertThat(response).isNotNull();
	    }
	    
	    @Test
	    public void testDeleteRecord() throws JSONException {
	    	  
	    	int id = 1;
	    	
	    	doNothing().when(mockRepository).deleteById(id);
	    	String response = phoneService.deleteRecord(id);

		    Assertions.assertThat(response).isNotNull();
	    	
	    }
}
