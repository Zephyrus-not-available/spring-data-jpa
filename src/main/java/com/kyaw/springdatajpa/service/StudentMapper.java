package com.kyaw.springdatajpa.service;

import com.kyaw.springdatajpa.dto.StudentDto;
import com.kyaw.springdatajpa.dto.StudentResponseDto;
import com.kyaw.springdatajpa.entity.Student;
import com.kyaw.springdatajpa.repository.SchoolRepository;
import com.kyaw.springdatajpa.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StudentMapper {
    private final SchoolRepository schoolRepository;

    public StudentMapper(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
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
                student.getLastName()
        );
    }

}
