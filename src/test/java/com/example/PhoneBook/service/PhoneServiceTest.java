package com.example.PhoneBook.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.PhoneBook.model.Directory;
import com.example.PhoneBook.repository.PhoneRepository;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceTest {

    @Mock
    private List<Directory> mockDirectoryList;

    @InjectMocks
    private PhoneService phoneService;
    
    @Mock
    private PhoneRepository mockRepository;

    @Test
    public void testGetList() throws JSONException {
        // Mocking Directory objects
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
        assertEquals(1234567890L, entry1.getString("Phone Number"));

        JSONObject entry2 = result.get(1);
        assertEquals(2, entry2.getInt("ID"));
        assertEquals("Jane", entry2.getString("Name"));
        assertEquals(9876543210L, entry2.getString("Phone Number"));
    }
    
    @Test
    public void testCreateRecordRecordAlreadyPresent() {
        // Mocking the behavior of the repository findById method
        when(mockRepository.findById(1)).thenReturn(Optional.of(new Directory()));

        // Creating a directory entry with an existing ID
        Directory entry = new Directory();
        entry.setId(1);
        entry.setName("Test Name");
        entry.setNumber(1234567890L);

        // Testing the createRecord method
        String result = phoneService.createRecord(entry);

        // Asserting the result
        assertEquals("Record is already present", result);

        // Verifying that repository save method is not called
        verify(mockRepository, never()).save(any());
    }

    @Test
    public void testCreateRecordRecordAddedSuccessfully() {
        // Mocking the behavior of the repository findById method
        when(mockRepository.findById(1)).thenReturn(Optional.empty());

        // Creating a directory entry with a non-existing ID
        Directory entry = new Directory();
        entry.setId(1);
        entry.setName("Test Name");
        entry.setNumber(1234567890L);

        // Testing the createRecord method
        String result = phoneService.createRecord(entry);

        // Asserting the result
        assertEquals("Record added successfully", result);

        // Verifying that repository save method is called once with the provided entry
        verify(mockRepository, times(1)).save(entry);
    }
    
    @Test
    public void testUpdateRecordRecordExists() {
        // Mocking the behavior of the repository findById method
        when(mockRepository.findById(1)).thenReturn(Optional.of(new Directory()));

        // Creating a directory entry with an existing ID
        Directory entry = new Directory();
        entry.setId(1);
        entry.setName("Test Name");
        entry.setNumber(1234567890L);

        // Testing the updateRecord method
        String result = phoneService.updateRecord(entry);

        // Asserting the result
        assertEquals("Record Updated", result);

        // Verifying that repository save method is called once with the provided entry
        verify(mockRepository, times(1)).save(entry);
    }

    @Test
    public void testUpdateRecordRecordDoesNotExist() {
        // Mocking the behavior of the repository findById method
        when(mockRepository.findById(1)).thenReturn(Optional.empty());

        // Creating a directory entry with a non-existing ID
        Directory entry = new Directory();
        entry.setId(1);
        entry.setName("Test Name");
        entry.setNumber(1234567890L);

        // Testing the updateRecord method
        String result = phoneService.updateRecord(entry);

        // Asserting the result
        assertEquals("Record does not exist", result);

        // Verifying that repository save method is not called
        verify(mockRepository, never()).save(any());
    }
    
    @Test
    public void testDeleteRecord() {
        // Mocking the behavior of the repository deleteById method
        doNothing().when(mockRepository).deleteById(1);

        // Testing the deleteRecord method
        String result = phoneService.deleteRecord(1);

        // Asserting the result
        assertEquals("Record deleted successfully", result);

        // Verifying that repository deleteById method is called once with the provided ID
        verify(mockRepository, times(1)).deleteById(1);
    }
}
