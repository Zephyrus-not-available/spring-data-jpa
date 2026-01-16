package com.kyaw.springdatajpa.controller;

import com.kyaw.springdatajpa.dto.SchoolDto;
import com.kyaw.springdatajpa.entity.School;
import com.kyaw.springdatajpa.repository.SchoolRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class SchoolController {

    private final SchoolRepository schoolRepository;

    public SchoolController(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @PostMapping("/schools")
    public SchoolDto createSchool(
            @RequestBody SchoolDto schoolDto
    ) {
        var school = toSchool(schoolDto);
        schoolRepository.save(school);
        return schoolDto;
    }

    public School toSchool(SchoolDto schoolDto) {
        return new School(schoolDto.name());
    }


    @GetMapping("/schools")
    public List<SchoolDto> findAll() {

        return schoolRepository.findAll()
                .stream()
                .map(this::toSchoolDto)
                .collect(Collectors.toList());
    }

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(
                school.getName()
        );
    }

}
