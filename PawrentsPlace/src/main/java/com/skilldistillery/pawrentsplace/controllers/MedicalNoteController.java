package com.skilldistillery.pawrentsplace.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.skilldistillery.pawrentsplace.entities.MedicalNote;
import com.skilldistillery.pawrentsplace.services.MedicalNoteService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost/" })
public class MedicalNoteController {

    @Autowired
    private MedicalNoteService medicalNoteService;

    @GetMapping("medicalnote/{id}")
    public MedicalNote show(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal,
    		@PathVariable int id) {
        return medicalNoteService.show(principal.getName(), id);
    }

    @PostMapping("medicalnote")
    public MedicalNote create(HttpServletRequest req, HttpServletResponse res, Principal principal, @RequestBody MedicalNote medicalNote) {
        try {
            return medicalNoteService.create(principal.getName(), medicalNote);
        } catch (Exception e) {
            e.printStackTrace();
            res.setStatus(400);
            medicalNote = null;
        }
        return medicalNote;
    }

    @PutMapping("medicalnote/{id}")
    public MedicalNote update(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal, 
    		@PathVariable int id,
            @RequestBody MedicalNote medicalNote) {
        return medicalNoteService.update(principal.getName(), id, medicalNote);
    }

    @DeleteMapping("medicalnote/{id}")
    public void destroy(HttpServletRequest req, 
    		HttpServletResponse res, 
    		Principal principal,
            @PathVariable int id) {
        medicalNoteService.delete(principal.getName(), id);
    }
    
}
