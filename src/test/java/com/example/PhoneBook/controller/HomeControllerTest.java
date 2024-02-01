package com.example.PhoneBook.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.PhoneBook.model.Directory;
import com.example.PhoneBook.repository.PhoneRepository;
import com.example.PhoneBook.service.PhoneService;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhoneRepository repository;

    @MockBean
    private PhoneService service;

    @Test
    public void testGetAll() throws Exception {
        // Arrange
        List<Directory> mockList = new ArrayList<>();
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("Test Name");
        directory.setNumber(1234567890L);
        mockList.add(directory);

        when(repository.findAll()).thenReturn(mockList);

        List<JSONObject> mockResponseList = new ArrayList<>();
        JSONObject mockResponse = new JSONObject();
        mockResponse.put("ID", 1);
        mockResponse.put("Name", "Test Name");
        mockResponse.put("Phone Number", 1234567890L);
        mockResponseList.add(mockResponse);

        when(service.getList(mockList)).thenReturn(mockResponseList);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/directory/get"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(mockResponseList.toString()));
    }
    
    @Test
    public void testCreateRecord_Success() throws Exception {
        // Arrange
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("Test Name");
        directory.setNumber(1234567890L);

        when(service.createRecord(directory)).thenReturn(directory.toString());
        
        String response = service.createRecord(directory);
        
        Assertions.assertThat(response).isNotNull();
    }
    
    @Test
    public void testUpdateRecord_Success() throws Exception {
        // Arrange
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("Test Name");
        directory.setNumber(1234567890L);
        repository.save(directory);

        when(service.updateRecord(directory)).thenReturn("Record Updated");

        String response = service.updateRecord(directory);
        
        Assertions.assertThat(response).isNotNull();
    }
    
    @Test
    public void testDeleteRecord_Success() throws Exception {
        // Arrange
        int id = 1;
        
        when(service.deleteRecord(id)).thenReturn("Record Updated");

        String response = service.deleteRecord(id);
        
        Assertions.assertThat(response).isNotNull();
    }
}