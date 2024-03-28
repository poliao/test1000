package com.example.demo.controller;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.TokenService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable(name = "id") Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Person person) {
        // เช็คการยืนยันตัวตนของผู้ใช้
        if (authenticationService.authenticate(person.getEmail(), person.getPassword())) {
            String token = tokenService.generateToken(person.getEmail());
            return ResponseEntity.ok(Map.of("token", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}")
    public Person updatePerson(@PathVariable(name = "id") Long id, @RequestBody Person person) {
        person.setId(id);
        return personRepository.save(person);
    }
    

    @DeleteMapping("/{id}")
    public void deletePerson(@PathVariable(name = "id") Long id) {
        personRepository.deleteById(id);
    }
}