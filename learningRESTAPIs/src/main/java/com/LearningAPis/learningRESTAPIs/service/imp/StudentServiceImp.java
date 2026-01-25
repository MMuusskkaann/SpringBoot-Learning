package com.LearningAPis.learningRESTAPIs.service.imp;

import com.LearningAPis.learningRESTAPIs.dto.Studentdto;
import com.LearningAPis.learningRESTAPIs.dto.addStudentRequestdto;
import com.LearningAPis.learningRESTAPIs.entity.Student;
import com.LearningAPis.learningRESTAPIs.repository.StudentRepository;
import com.LearningAPis.learningRESTAPIs.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service   //business logic
//@RequiredArgsConstructor //lombok anotation  //used to create a constuctor automatically
public  class StudentServiceImp implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public StudentServiceImp(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Studentdto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream()
                .map(student -> modelMapper.map(student, Studentdto.class)).toList();
    }

    @Override
    public Studentdto getStudentById(Long id) {
//        Student student =  studentRepository.findBy(id).orElseThrow(() -> new IllegalArgumentException("Student not found with id  :" id));
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student not found  with ID : " + id));
        return modelMapper.map(student, Studentdto.class);  //provide new object of the class
    }

    @Override
    public Studentdto createNewStudent(addStudentRequestdto  addStudentRequestdto) {
        Student newStudent = modelMapper.map(addStudentRequestdto, Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student,Studentdto.class);
    }

    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new IllegalArgumentException("Student does not exist with id: " + id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public Studentdto updateStudent(Long id, addStudentRequestdto addStudentRequestdto) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found  with ID : " + id));
        modelMapper.map(addStudentRequestdto,student);
        student = studentRepository.save(student);
        return  modelMapper.map(student,Studentdto.class);
    }

    @Override
    public Studentdto updatePartialStudent(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found  with ID : " + id));
        updates.forEach((field,value) -> {
            switch (field){
                case "name" : student.setName((String) value);
                break;
                case "email" : student.setEmail((String) value);
                break;

                default:
                    throw  new IllegalArgumentException("Field is not supported");
            }
        });

        Student savedstudent = studentRepository.save(student);
        return  modelMapper.map(student,Studentdto.class);
    }
}
