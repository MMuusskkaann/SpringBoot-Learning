package com.LearningAPis.learningRESTAPIs.controller;
import java.util.*;
import com.LearningAPis.learningRESTAPIs.dto.Studentdto;
import com.LearningAPis.learningRESTAPIs.entity.Student;
import com.LearningAPis.learningRESTAPIs.repository.StudentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/student")

    public List<Student> getStudent(){

        return studentRepository.findAll();
    }

//    @GetMapping("/student/{id}")
//
//    public Studentdto  getStudentById(){
//        return new Studentdto(5L,"Muskan","muskan123@gmail.com");
//    }
}
