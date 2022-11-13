package com.krzysiekm266.webgradebook.subject;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private SubjectRepository subjectRepository;
    

    public ResponseEntity<List<Subject>> getAllSubjects() {
         List<Subject> subjects = this.subjectRepository.findAll();
        return new ResponseEntity<>(subjects,HttpStatus.OK);
    }
}
