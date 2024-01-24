package com.example.PhoneBook.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.PhoneBook.model.Directory;
import com.example.PhoneBook.repository.PhoneRepository;

@SpringBootTest
public class PhoneServiceTest {

    @InjectMocks
    private PhoneService phoneService;

    @Mock
    private PhoneRepository phoneRepository;

    @Test
    public void testCreateRecord() {
        Directory entry = new Directory(1,"Somil",8104750509L);
        entry.setId(1);

        when(phoneRepository.findById(1)).thenReturn(Optional.empty());
        when(phoneRepository.save(entry)).thenReturn(entry);

        String result = phoneService.createRecord(entry);

        assertEquals("Record added successfully", result);
        verify(phoneRepository, times(1)).findById(1);
        verify(phoneRepository, times(1)).save(entry);
    }

    @Test
    public void testCreateRecordRecordExists() {
        Directory entry = new Directory(1,"Somil",8104750509L);
        entry.setId(1);

        when(phoneRepository.findById(1)).thenReturn(Optional.of(entry));

        String result = phoneService.createRecord(entry);

        assertEquals("Record is already present", result);
        verify(phoneRepository, times(1)).findById(1);
        verify(phoneRepository, never()).save(entry);
    }

    @Test
    public void testGetList() throws JSONException {
        List<Directory> list = new ArrayList<>();
        list.add(new Directory(1, "John", 12345L));

        List<JSONObject> expected = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ID", 1);
        jsonObject.put("Name", "John");
        jsonObject.put("Phone Number", "12345");
        expected.add(jsonObject);

        List<JSONObject> result = phoneService.getList(list);

        assertEquals(expected, result);
    }

    @Test
    public void testUpdateRecord() {
        Directory entry = new Directory(1,"Somil",8104750509L);
        entry.setId(1);

        when(phoneRepository.findById(1)).thenReturn(Optional.of(entry));
        when(phoneRepository.save(entry)).thenReturn(entry);

        String result = phoneService.updateRecord(entry);

        assertEquals("Record Updated", result);
        verify(phoneRepository, times(1)).findById(1);
        verify(phoneRepository, times(1)).save(entry);
    }

    @Test
    public void testUpdateRecordRecordNotExists() {
        Directory entry = new Directory(1,"Somil",8104750509L);
        entry.setId(1);

        when(phoneRepository.findById(1)).thenReturn(Optional.empty());

        String result = phoneService.updateRecord(entry);

        assertEquals("Record does not exist", result);
        verify(phoneRepository, times(1)).findById(1);
        verify(phoneRepository, never()).save(entry);
    }

    @Test
    public void testDeleteRecord() {
        int id = 1;

        String result = phoneService.deleteRecord(id);

        assertEquals("Record deleted successfully", result);
        verify(phoneRepository, times(1)).deleteById(id);
    }
}
