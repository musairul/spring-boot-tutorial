package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service // this annotation tells spring that this class is a service
// a service is a class that contains the business logic of the application
// it is used to perform some business operations
// it is used to interact with the repository and perform some operations on the data
// this class will be used to perform some operations on the student entity

public class StudentService {

    private final StudentRepository studentRepository;


    @Autowired // this annotation tells spring to inject the student repository into this constructor
    // this is a dependency injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
		return studentRepository.findAll();
	}

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional // this annotation tells spring that this method will be used to update the student
    // this annotation is used to perform some database operations
    // it will start a transaction before the method is called and commit the transaction after the method is called
    // if an exception is thrown, it will rollback the transaction
    // this annotation is used to ensure that the database is in a consistent state
    public void updateStudent(Long studentId, String name, String email){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id " + studentId + " does not exist"
                ));
        
        if(name != null && name.length() > 0 && !student.getName().equals(name)){
            student.setName(name);
        }

        if(email != null && email.length() > 0 && !student.getEmail().equals(email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }
}
