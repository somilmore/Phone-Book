package com.example.PhoneBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PhoneBook.entity.Directory;

@Repository
public interface PhoneRepository extends JpaRepository<Directory,Long>{

}
