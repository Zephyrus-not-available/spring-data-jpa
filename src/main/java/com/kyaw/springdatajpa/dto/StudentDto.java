package com.kyaw.springdatajpa.dto;


import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
    @NotEmpty
    String firstName,
    @NotEmpty
    String lastName,
    @NotEmpty
    String email,
    Integer schoolId

) {
}
