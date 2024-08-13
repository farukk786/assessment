package com.alphaware.assessment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alphaware.assessment.entity.OceanEntity;

public interface OceanRepository extends JpaRepository<OceanEntity, Long> {
	List<OceanEntity> findbyOceanNameIn(List<String>oceans);
}
