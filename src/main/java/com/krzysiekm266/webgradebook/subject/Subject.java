package com.krzysiekm266.webgradebook.subject;

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
import com.krzysiekm266.webgradebook.student.Student;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Subject implements Serializable {

    @Id
    @SequenceGenerator(name = "subject_id_sequence", sequenceName = "subject_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_id_sequence")
    @Column(updatable =  false)
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects",fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    private Set<Student> students = new HashSet<>();

    // public void addStudent(Student student) {
    //     this.students.add(student);
    //     student.getSubjects().add(this);

    // }

    // public void removeStudent(Student student) {
    //     this.students.remove(student);
    //     student.getSubjects().remove(this);
    // }

    
    public Subject(String name) {
        this.name = name;
    }

   

   

}
