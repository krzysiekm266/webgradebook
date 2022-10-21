package com.krzysiekm266.webgradebook.student;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.krzysiekm266.webgradebook.student.exceptions.StudentIllegalStateException;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = this.studentRepository.findAll();
        return new ResponseEntity<>(students,HttpStatus.OK);
    }

    public ResponseEntity<Student> findById(Long id) {
        Student studentById =  this.studentRepository.findById(id)
            .orElseThrow(() -> new StudentIllegalStateException("Student by id: "+ id +"not found."));
        return new ResponseEntity<>(studentById,HttpStatus.FOUND);
    }

    public ResponseEntity<Student> create(Student student) {
        Student newStudent =  this.studentRepository.save(student);
        return new ResponseEntity<>(newStudent,HttpStatus.CREATED);
    }

    public ResponseEntity<Student> update(Long id,Student student) {
        Student studentById =  this.studentRepository.findById(id)
            .orElseThrow( () -> new StudentIllegalStateException("Student required."));

        Field[] field = student.getClass().getDeclaredFields();
        Boolean isNull = Stream.of(field)
            .filter(f -> !(f.getName().equals("id")) ).anyMatch( fld -> { 
                try {
                    return fld.get(fld.getName()) == null;
                } catch (Exception e) {
                    return true;
                }
                
            });
        if(isNull) {
            throw new StudentIllegalStateException("Student properties cannot be null.");
        }    
        studentById.setFirstName(student.getFirstName());
        studentById.setLastName(student.getLastName());
        studentById.setSubjects(student.getSubjects());

        Student updatedStudent =  this.studentRepository.saveAndFlush(studentById);
        return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
    }

    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }
}
