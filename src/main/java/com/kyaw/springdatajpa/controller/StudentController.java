package com.kyaw.springdatajpa.controller;

import com.kyaw.springdatajpa.dto.StudentDto;
import com.kyaw.springdatajpa.entity.School;
import com.kyaw.springdatajpa.entity.Student;
import com.kyaw.springdatajpa.repository.StudentRepository;
import com.kyaw.springdatajpa.repository.SchoolRepository;
import jakarta.persistence.EntityManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    public StudentController(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/students")
    public Student post(@RequestBody StudentDto studentDto) {
        var student = toStudent(studentDto);
        return studentRepository.save(student);
    }

    public Student toStudent(StudentDto studentDto) {
        var student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());
        student.setAge(studentDto.age());

        var school = schoolRepository.findById(studentDto.schoolId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found"));

        student.setSchool(school);

        return student;
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
