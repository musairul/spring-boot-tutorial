package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // this annotation tells spring that this interface is a repository
// a repository is a class that will handle the database operations
// this interface will be used to interact with the database
// it extends the JpaRepository interface
// the JpaRepository interface is a spring data interface that provides some methods for performing common database operations
// it takes two parameters, the entity class and the type of the primary key
// in this case, the entity class is Student and the primary key is Long
// so JpaRepository<Student, Long> means that this interface will be used to perform database operations on the Student entity
// it provides some methods for performing common database operations like save, findAll, findById, delete, etc.
// we don't need to implement these methods, spring will provide the implementation for us
// we just need to define this interface and spring will provide the implementation
// we can also define custom methods in this interface if we want to perform some custom database operations

public interface StudentRepository extends JpaRepository<Student, Long> {
    
    //Student in the code below is the Student class
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
