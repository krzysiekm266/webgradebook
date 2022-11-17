package com.krzysiekm266.webgradebook.subject;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.krzysiekm266.webgradebook.subject.exceptions.SubjectIllegalStateException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectService {
    private SubjectRepository subjectRepository;
    
    public List<Subject> findAll(Integer page) {
        List<Subject> subjects = this.subjectRepository.findAll(Pageable.ofSize(page))
            .stream().collect(Collectors.toList()); 
        return subjects;   
    }

    public Subject findById(Long id) {
        Subject SubjectById =  this.subjectRepository.findById(id)
            .orElseThrow(() -> new SubjectIllegalStateException("Subject by id: "+ id +"not found."));
        return SubjectById;
    }

    public Subject create(Subject Subject) {
        Subject newSubject =  this.subjectRepository.save(Subject);
        return newSubject;
    }

    public Subject update(Long id,Subject Subject) {
        Subject SubjectById =  this.subjectRepository.findById(id)
            .orElseThrow( () -> new SubjectIllegalStateException("Subject required."));

        Field[] field = Subject.getClass().getDeclaredFields();
        Boolean isNull = Stream.of(field)
            .filter(f -> !(f.getName().equals("id")) ).anyMatch( fld -> { 
                try {
                    return fld.get(fld.getName()) == null;
                } catch (Exception e) {
                    return true;
                }
                
            });
        if(isNull) {
            throw new SubjectIllegalStateException("Subject properties cannot be null.");
        }    
        

        Subject updatedSubject =  this.subjectRepository.saveAndFlush(SubjectById);
        return updatedSubject;
    }

    public void delete(Long id) {
        this.subjectRepository.deleteById(id);
    }
}
