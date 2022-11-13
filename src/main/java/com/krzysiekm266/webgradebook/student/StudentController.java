package com.krzysiekm266.webgradebook.student;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return this.studentService.findAll();
    }

    @PostMapping(path = "/create")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return this.studentService.create(student);
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<Student> getStudent(@PathParam(value = "studentId") Long id) {
        return this.studentService.findById(id);
    }

    @PutMapping(path = "/{studentId}/update")
    public ResponseEntity<Student> updateStudent(@PathParam(value = "studentId") Long id, @RequestBody Student student) {
        return this.studentService.update(id, student);

    }
    
    @DeleteMapping(name = "/{studentId}/delete")
    public ResponseEntity<Boolean> deleteStudent(@PathParam(value = "studentId") Long id) {
        this.studentService.delete(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
