package com.kyaw.springdatajpa.repository;

import com.kyaw.springdatajpa.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School,Integer> {

}
