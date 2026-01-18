package com.kyaw.springdatajpa.dto;


import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
    @NotEmpty(message = "first name must not be empty")
    String firstName,
    @NotEmpty(message = "last name must not be empty")
    String lastName,
    @NotEmpty
    String email,
    Integer schoolId

) {
}
