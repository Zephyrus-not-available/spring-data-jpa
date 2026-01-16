package com.kyaw.springdatajpa.controller;

import com.kyaw.springdatajpa.dto.StudentDto;
import com.kyaw.springdatajpa.dto.StudentResponseDto;
import com.kyaw.springdatajpa.entity.Student;
import com.kyaw.springdatajpa.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(@RequestBody StudentDto studentDto) {
       return studentService.saveStudent(studentDto);
    }


    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{student_id}")
    public StudentResponseDto getStudentById(@PathVariable Integer student_id) {
        return studentService.getStudentById(student_id);
    }

    @GetMapping("students/search/{student_name}")
    public List<Student> getStudentByName(@PathVariable("student_name") String name){
        return studentService.getStudentByName(name);
    }

    @DeleteMapping("/students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable Integer student_id){
    studentService.deleteStudentById(student_id);
    }
}
