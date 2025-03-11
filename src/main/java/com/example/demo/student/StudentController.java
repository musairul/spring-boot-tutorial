package com.example.demo.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // this annotation tells spring that this class is a rest controller
// a rest controller is a class that will handle http requests and return responses
@RequestMapping(path = "api/v1/student") // this annotation tells spring that this class will handle requests that start with /api/v1/student
// this is the base path for this controller
public class StudentController {

	private final StudentService studentService; // this is a dependency of the student controller

	@Autowired // this annotation tells spring to inject the student service into this constructor
	// this is a dependency injection
	public StudentController(StudentService studentService) { // this is a constructor
		// this constructor will be used to create an instance of the student controller
		// spring will inject the student service into this constructor

		this.studentService = studentService;
		
	}
    @GetMapping() // this annotation tells spring that this method will handle GET requests
	public List<Student> getStudents(){
		return studentService.getStudents();
	}

	@PostMapping
	public void registerNewStudent(@RequestBody Student student){
		studentService.addNewStudent(student);
	}

	@DeleteMapping(path = "{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long id){ // this annotation tells spring to get the student id from the path
		studentService.deleteStudent(id);
	}
	
}
