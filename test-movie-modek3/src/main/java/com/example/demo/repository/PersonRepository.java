package com.example.demo.repository;

import com.example.demo.model.Person;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);
    // Add custom queries if needed

    Object findByEmailAndPassword(String email, String password);
}