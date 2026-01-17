package com.kyaw.springdatajpa.service;

import com.kyaw.springdatajpa.dto.SchoolDto;
import com.kyaw.springdatajpa.dto.SchoolResponseDto;
import com.kyaw.springdatajpa.entity.School;
import org.springframework.stereotype.Service;

@Service
public class SchoolMapper {

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(
                school.getName()
        );
    }

    public School toSchool(SchoolDto schoolDto) {
        return new School(schoolDto.name());
    }

    public SchoolResponseDto toSchoolResponseDto(School school) {
        return new SchoolResponseDto(
                school.getName()
        );
    }


}
