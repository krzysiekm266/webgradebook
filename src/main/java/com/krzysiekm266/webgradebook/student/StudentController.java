package com.krzysiekm266.webgradebook.student;

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
public class StudentController {
    StudentService studentService;

    @GetMapping(path = "/students")
    public ResponseEntity<List<Student>> getAllStudentsPage(@RequestParam(name = "page") Integer page) {
        return new ResponseEntity<>(this.studentService.findAll(page),HttpStatus.OK);    
    }

    @PostMapping(path = "/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return new ResponseEntity<>(this.studentService.create(student),HttpStatus.CREATED);
    }

    @GetMapping(path = "/students/{studentId}")
    public ResponseEntity<Student> getStudent(@PathParam(value = "studentId") Long id) {
        return  new  ResponseEntity<>(this.studentService.findById(id),HttpStatus.OK);
    }

    @PutMapping(path = "/students/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathParam(value = "studentId") Long id, @RequestBody Student student) {
        return new ResponseEntity<>(this.studentService.update(id, student),HttpStatus.OK);

    }
    
    @DeleteMapping(name = "/students/{studentId}")
    public ResponseEntity<Boolean> deleteStudent(@PathParam(value = "studentId") Long id) {
        this.studentService.delete(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
