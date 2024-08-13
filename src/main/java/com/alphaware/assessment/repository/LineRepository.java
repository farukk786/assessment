package com.alphaware.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphaware.assessment.entity.CountryEntity;
import com.alphaware.assessment.entity.LineEntity;

public interface LineRepository extends JpaRepository<LineEntity, Long> {
	List<LineEntity> findbyNameIn(List<String>lines);
}
