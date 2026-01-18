package com.kyaw.springdatajpa.controller;

import com.kyaw.springdatajpa.dto.StudentDto;
import com.kyaw.springdatajpa.dto.StudentResponseDto;
import com.kyaw.springdatajpa.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public StudentResponseDto saveStudent(@Valid @RequestBody StudentDto studentDto) {
       return studentService.saveStudent(studentDto);
    }


    @GetMapping("/students")
    public List<StudentResponseDto> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{student_id}")
    public StudentResponseDto getStudentById(@PathVariable Integer student_id) {
        return studentService.getStudentById(student_id);
    }

    @GetMapping("students/search/{student_name}")
    public List<StudentResponseDto> getStudentByName(@PathVariable("student_name") String name){
        return studentService.getStudentByName(name);
    }

    @DeleteMapping("/students/{student_id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentById(@PathVariable Integer student_id){
    studentService.deleteStudentById(student_id);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getFieldErrors()
                .forEach(error -> {
                    var fieldName = error.getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
