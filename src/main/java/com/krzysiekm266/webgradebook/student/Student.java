package com.krzysiekm266.webgradebook.student;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.krzysiekm266.webgradebook.subject.Subject;

import lombok.Getter;
import lombok.Setter;



@Entity(name = "Student")
@Table(name = "students")
@Getter
@Setter
public class Student implements Serializable {
    
    @Id
    @SequenceGenerator(
        name = "student_id_sequence",
        sequenceName = "student_id_sequence",
        initialValue = 1000,
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "student_id_sequence"
    )
    @Column(updatable = false)
    private Long id;

    @Column(name = "first_name",columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name",columnDefinition = "TEXT")
    private String lastName;

    @JsonIgnore
    @ManyToMany(mappedBy = "students",fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @Column(name = "subjects")
    private Set<Subject> subjects = new HashSet<>();

    /******************************************************* */
    
    protected Student() {
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

  
    
}
