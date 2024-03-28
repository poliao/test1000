package com.example.demo.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;

import java.security.Key;

@Service
public class TokenService {
    private Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    @Autowired
    private PersonRepository personRepository;

    public String generateToken(String email) {
    Person person = personRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User not found"));
    
    Claims claims = Jwts.claims().setSubject(email);
    claims.put("id", person.getId());
    claims.put("email", person.getEmail());
    claims.put("firstname", person.getFirstname());
    claims.put("lastname", person.getLastname());
    claims.put("phonenumber", person.getPhonenumber());
    claims.put("tyuser", person.getTyuser());

    return Jwts.builder()
            .setClaims(claims)
            .signWith(key)
            .compact();
}

}
