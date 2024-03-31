package com.student_management.backend.controller;

import com.student_management.backend.exception.UserNotFoundException;
import com.student_management.backend.model.Student;
import com.student_management.backend.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentRepo studentRepo;

    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent){
        return studentRepo.save(newStudent);
    }

    @GetMapping("/students")
    List<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    @GetMapping("/student/{id}")
    Student getStudentById(@PathVariable Long id){
        return studentRepo.findById(id)
                .orElseThrow(()-> new UserNotFoundException(id));
    }

    @PutMapping("/student/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id){
        return studentRepo.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setRoll_no(newStudent.getRoll_no());
                    student.setEmail_id(newStudent.getEmail_id());
                    return studentRepo.save(student);
                }).orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/student/{id}")
    String deleteStudent(@PathVariable Long id){
        if(!studentRepo.existsById(id)){
            throw new UserNotFoundException(id);
        }
        studentRepo.deleteById(id);
        return "Student record with id " + id + " has been successfully deleted";

    }
}
