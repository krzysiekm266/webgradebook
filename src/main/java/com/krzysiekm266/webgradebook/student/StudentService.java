package com.krzysiekm266.webgradebook.student;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.krzysiekm266.webgradebook.student.exceptions.StudentIllegalStateException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
   
    StudentRepository studentRepository;

    public List<Student> findAll(Integer page) {
        List<Student> students = this.studentRepository.findAll(PageRequest.of(page, 10))
            .stream().collect(Collectors.toList());
          
        return students;
    }

    public Student findById(Long id) {
        Student studentById =  this.studentRepository.findById(id)
            .orElseThrow(() -> new StudentIllegalStateException("Student by id: "+ id +"not found."));
        return studentById;
    }

    public Student create(Student student) {
        Student newStudent =  this.studentRepository.save(student);
        return newStudent;
    }

    public Student update(Long id,Student student) {
        Student studentById =  this.studentRepository.findById(id)
            .orElseThrow( () -> new StudentIllegalStateException("Student required."));

        Field[] field = student.getClass().getDeclaredFields();
        Boolean isNull = Stream.of(field)
            .filter(f -> !(f.getName().equals("id")) )
            .anyMatch( fld -> { 
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
        return updatedStudent;
    }

    public void delete(Long id) {
        this.studentRepository.deleteById(id);
    }
}
