package com.kyaw.springdatajpa.controller;

import com.kyaw.springdatajpa.dto.SchoolDto;
import com.kyaw.springdatajpa.dto.SchoolResponseDto;
import com.kyaw.springdatajpa.service.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class SchoolController {

    private SchoolService schoolService;

    @PostMapping("/schools")
    public SchoolDto createSchool(
            @RequestBody SchoolDto schoolDto
    ) {

        return schoolService.createSchool(schoolDto);
    }


    @GetMapping("/schools")
    public List<SchoolResponseDto> findAll() {

       return schoolService.findAll();
    }



}
