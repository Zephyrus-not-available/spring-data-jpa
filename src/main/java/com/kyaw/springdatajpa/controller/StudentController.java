package com.kyaw.springdatajpa.controller;

import com.kyaw.springdatajpa.entity.Student;
import com.kyaw.springdatajpa.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping("/students")
    public Student post(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{student_id}")
    public Student getStudentById(@PathVariable Integer student_id) {
        return studentRepository.findById(student_id).orElse(new Student());
    }

    @GetMapping("students/search/{student_name}")
    public List<Student> getStudentByName(@PathVariable("student_name") String name){
        return studentRepository.findAllByFirstNameContaining(name);
    }

    @DeleteMapping("/students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable Integer student_id){
        studentRepository.deleteById(student_id);
    }
}
