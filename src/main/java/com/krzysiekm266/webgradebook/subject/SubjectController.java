package com.krzysiekm266.webgradebook.subject;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SubjectController {
    SubjectService subjectService;

    @GetMapping(path = "/subjects")
    public ResponseEntity<List<Subject>> getAllSubjectsPage(@RequestParam(name = "page") Integer page) {
        return new ResponseEntity<>(this.subjectService.findAll(page),HttpStatus.OK);    
    }

    @PostMapping(path = "/subjects")
    public ResponseEntity<Subject> createSubject(@RequestBody Subject Subject) {
        return new ResponseEntity<>(this.subjectService.create(Subject),HttpStatus.CREATED);
    }

    @GetMapping(path = "/subjects/{subjectId}")
    public ResponseEntity<Subject> getSubject(@PathParam(value = "subjectId") Long id) {
        return  new  ResponseEntity<>(this.subjectService.findById(id),HttpStatus.FOUND);
    }

    @PutMapping(path = "/subjects/{subjectId}")
    public ResponseEntity<Subject> updateSubject(@PathParam(value = "subjectId") Long id, @RequestBody Subject subject) {
        return new ResponseEntity<>(this.subjectService.update(id, subject),HttpStatus.OK);

    }
    
    @DeleteMapping(name = "/subjects/{subjectId}")
    public ResponseEntity<Boolean> deleteSubject(@PathParam(value = "subjectId") Long id) {
        this.subjectService.delete(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
