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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.krzysiekm266.webgradebook.subject.Subject;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Student implements Serializable {

    @Id
    @SequenceGenerator(name = "student_id_sequence", sequenceName = "student_id_sequence", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_sequence")
    @Column(updatable = false)
    private Long id;

    @Column(name = "first_name", columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "last_name", columnDefinition = "TEXT")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    // @JoinTable(name = "students_subjects",
    //     joinColumns = { @JoinColumn(name = "student_id") },
    //     inverseJoinColumns = { @JoinColumn(name = "subject_id") }
    // )
    private Set<Subject> subjects = new HashSet<>();

    public void addSubject(Subject subject) {
    this.subjects.add(subject);
    subject.getStudents().add(this);

    }

    public void removeSubject(Subject subject) {
    this.subjects.remove(subject);
    subject.getStudents().remove(this);
    }

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    

   

}
