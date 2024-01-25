package com.example.PhoneBook.controller;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
        mockMvc.perform(MockMvcRequestBuilders.get("/get"))
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

        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        when(service.createRecord(directory)).thenReturn("Record added successfully");

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .content("{\"id\":\"1\",\"name\":\"Test Name\",\"Phone Number\":\"1234567890\"}") // replace with actual JSON content
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Record added successfully"));
    }

    @Test
    public void testCreateRecord_RecordAlreadyPresent() throws Exception {
        // Arrange
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("Test Name");
        directory.setNumber(1234567890L);

        when(repository.findById(anyInt())).thenReturn(Optional.of(directory));
        when(service.createRecord(directory)).thenReturn("Record is already present");

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .content("{\"id\":\"1\",\"name\":\"Test Name\",\"Phone Number\":\"1234567890\"}") // replace with actual JSON content
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Record is already present"));
    }
    
    @Test
    public void testUpdateRecord_Success() throws Exception {
        // Arrange
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("Test Name");
        directory.setNumber(1234567890L);

        when(repository.findById(anyInt())).thenReturn(Optional.of(directory));
        when(service.updateRecord(directory)).thenReturn("Record Updated");

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/update")
                .content("{\"id\":\"1\",\"name\":\"Test Name\",\"Phone Number\":\"1234567890\"}") // replace with actual JSON content
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Record Updated"));
    }

    @Test
    public void testUpdateRecord_RecordNotFound() throws Exception {
        // Arrange
        Directory directory = new Directory();
        directory.setId(1);
        directory.setName("Test Name");
        directory.setNumber(1234567890L);

        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        when(service.updateRecord(directory)).thenReturn("Record does not exist");

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/update")
                .content("{\"id\":\"1\",\"name\":\"Test Name\",\"Phone Number\":\"1234567890\"}") // replace with actual JSON content
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Record does not exist"));
    }
    
    @Test
    public void testDeleteRecord_Success() throws Exception {
        // Arrange
        int id = 1;

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Record deleted successfully"));

        // Verify that deleteById is called with the correct ID
        verify(repository, times(1)).deleteById(id);
    }
}
