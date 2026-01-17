package com.kyaw.springdatajpa.service;

import com.kyaw.springdatajpa.dto.StudentDto;
import com.kyaw.springdatajpa.dto.StudentResponseDto;
import com.kyaw.springdatajpa.entity.Student;
import com.kyaw.springdatajpa.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public StudentResponseDto saveStudent(StudentDto studentDto) {
        var student = studentMapper.toStudent(studentDto);
        var savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponseDto(savedStudent);
    }

    public List<StudentResponseDto> getStudents() {

        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .toList();
    }

    public StudentResponseDto getStudentById(Integer student_id) {
        var student = studentRepository.findById(student_id).orElse(new Student());
        return studentMapper.toStudentResponseDto(student);
    }

    public List<StudentResponseDto> getStudentByName(String name){
        return studentRepository.findAllByFirstNameContaining(name)
                .stream()
                .map(studentMapper::toStudentResponseDto)
                .collect(toList());
    }

    public void deleteStudentById(Integer student_id){
        studentRepository.deleteById(student_id);
    }


}
