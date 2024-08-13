package com.alphaware.assessment.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_country")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OceanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;

	@Column(name = "ocean_name")
	String oceanName;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_continent_oceans", joinColumns = @JoinColumn(name = "ocean_id"), inverseJoinColumns = @JoinColumn(name = "continent_id"))
	List<ContinentEntity> continents;

	public OceanEntity(String oceanName) {
		super();
		this.oceanName = oceanName;
	}

}
