package com.krzysiekm266.webgradebook.subject;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}
