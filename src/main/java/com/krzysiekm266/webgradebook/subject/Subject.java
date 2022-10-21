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

import com.krzysiekm266.webgradebook.student.Student;



@Entity(name = "Subject")
@Table(name = "subjects")
public class Subject implements Serializable {

    @Id
    @SequenceGenerator(
        name = "subject_id_sequence",
        sequenceName = "subject_id_sequence",
        allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "subject_id_sequence")
    @Column(updatable = false)
    private Long id;

    @Column(name = "name",columnDefinition = "TEXT")
    private String name;

   
    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @Column(name = "students")
    private Set<Student> students = new HashSet<>() ;

    public void addStudent(Student student) {
        this.students.add(student);
        student.getSubjects().add(this);
        
    }
    public void removeStudent(Student student) {
        this.students.remove(student);
        student.getSubjects().remove(this);
    }
    /******************************************* */
    public Subject() {
    }
    public Subject(String name) {
        this.name = name;
    }
    /********************************************** */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Set<Student> getStudents() {
        return students;
    }
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
    /************************************************************************ */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subject other = (Subject) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Subject [id=" + id + ", name=" + name + ", students=" + students + "]";
    }

    
}
