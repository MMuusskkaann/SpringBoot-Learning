package com.LearningAPis.learningRESTAPIs.service;
import java.util.*;

import com.LearningAPis.learningRESTAPIs.dto.Studentdto;
import com.LearningAPis.learningRESTAPIs.dto.addStudentRequestdto;

public interface StudentService {

    List<Studentdto>  getAllStudents();

    Studentdto getStudentById(Long id);

    Studentdto createNewStudent(addStudentRequestdto addStudentRequestdto);

    void deleteStudentById(Long id);

    Studentdto updateStudent(Long id, addStudentRequestdto addStudentRequestdto);

    Studentdto updatePartialStudent(Long id, Map<String, Object> updates);
}
