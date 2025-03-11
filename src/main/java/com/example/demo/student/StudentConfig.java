package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // this annotation tells spring that this is a configuration class
// this class will be used to configure the beans that we want to add to the application context

public class StudentConfig {
    @Bean // this annotation tells spring to run this method when the application context is created
    // CommandLineRunner is a functional interface that allows us to run some code when the application starts
    // it has a single method called run that we need to implement

    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student mariam = new Student("Mariam","mariamjamal@gmail.com",LocalDate.of(2000, Month.JANUARY, 5));

            Student alex = new Student("Alex","alex@gmail.com",LocalDate.of(2004, Month.JANUARY, 5));

            repository.saveAll(List.of(mariam,alex));
        };
    }
}