package com.alphaware.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphaware.assessment.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
	List<CountryEntity> findbyCountryNameIn(List<String>countries);
}
