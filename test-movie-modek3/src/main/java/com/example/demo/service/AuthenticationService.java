package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    @Autowired
    private PersonRepository personRepository;

    public boolean authenticate(String email, String password) {
        Optional<Person> person = personRepository.findByEmail(email);
        return person.map(p -> p.getPassword().equals(password)).orElse(false);
    }
}

