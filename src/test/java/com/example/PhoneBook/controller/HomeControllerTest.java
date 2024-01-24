package com.example.PhoneBook.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PhoneBook.model.Directory;
import com.example.PhoneBook.repository.PhoneRepository;
import com.example.PhoneBook.service.PhoneService;

@SpringBootTest
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private PhoneService phoneService;

    @Test
    public void testCreate() {
        Directory entry = new Directory();
        when(phoneService.createRecord(entry)).thenReturn("Record added successfully");

        String result = homeController.create(entry);

        assertEquals("Record added successfully", result);
        verify(phoneService, times(1)).createRecord(entry);
    }

    @Test
    public void testGetAll() {
        List<Directory> list = new ArrayList<>();
        when(phoneRepository.findAll()).thenReturn(list);
        when(phoneService.getList(list)).thenReturn(new ArrayList<>());

        String result = homeController.getAll();

        assertEquals("[]", result); // Assuming empty list results in an empty JSON array
        verify(phoneRepository, times(1)).findAll();
        verify(phoneService, times(1)).getList(list);
    }

    @Test
    public void testUpdate() {
        Directory entry = new Directory();
        when(phoneService.updateRecord(entry)).thenReturn("Record Updated");

        String result = homeController.update(entry);

        assertEquals("Record Updated", result);
        verify(phoneService, times(1)).updateRecord(entry);
    }

    @Test
    public void testDelete() {
        int id = 1;
        when(phoneService.deleteRecord(id)).thenReturn("Record deleted successfully");

        String result = homeController.delete(id);

        assertEquals("Record deleted successfully", result);
        verify(phoneService, times(1)).deleteRecord(id);
    }
}
