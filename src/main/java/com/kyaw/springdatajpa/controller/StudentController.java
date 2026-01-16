package com.kyaw.springdatajpa.controller;

import com.kyaw.springdatajpa.dto.StudentDto;
import com.kyaw.springdatajpa.dto.StudentResponseDto;
import com.kyaw.springdatajpa.entity.Student;
import com.kyaw.springdatajpa.repository.StudentRepository;
import com.kyaw.springdatajpa.repository.SchoolRepository;
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
    public StudentResponseDto post(@RequestBody StudentDto studentDto) {
        var student = toStudent(studentDto);
        studentRepository.save(student);
        return toStudentResponseDto(student);
    }

    public Student toStudent(StudentDto studentDto) {
        var student = new Student();
        student.setFirstName(studentDto.firstName());
        student.setLastName(studentDto.lastName());
        student.setEmail(studentDto.email());
        var school = schoolRepository.findById(studentDto.schoolId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "School not found"));

        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/students/{student_id}")
    public StudentResponseDto getStudentById(@PathVariable Integer student_id) {
        var saveInfo = studentRepository.findById(student_id).orElse(new Student());
        return toStudentResponseDto(saveInfo);
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
