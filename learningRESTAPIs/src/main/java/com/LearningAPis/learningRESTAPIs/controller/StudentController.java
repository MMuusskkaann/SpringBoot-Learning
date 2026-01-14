package com.LearningAPis.learningRESTAPIs.controller;

import com.LearningAPis.learningRESTAPIs.dto.Studentdto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    public Studentdto  getStudent(){
          return new Studentdto(5L,"Muskan","muskan123@gmail.com");
    }
}
