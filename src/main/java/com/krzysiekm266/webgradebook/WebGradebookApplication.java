package com.krzysiekm266.webgradebook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.krzysiekm266.webgradebook.student.Student;
import com.krzysiekm266.webgradebook.student.StudentRepository;
import com.krzysiekm266.webgradebook.subject.Subject;
import com.krzysiekm266.webgradebook.subject.SubjectRepository;

@SpringBootApplication
public class WebGradebookApplication implements CommandLineRunner {
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	SubjectRepository subjectRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebGradebookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Student student1 = new Student("Jan","Kowalski");
		// Subject subject1 = new Subject("Matematyka");
		
		// student1.addSubject(subject1);
		

		// this.subjectRepository.save(subject1);
		// this.studentRepository.save(student1);

		
		
		
	}

}
