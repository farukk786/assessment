package com.alphaware.assessment.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "tbl_line")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LineEntity {

	@Id
	String id;

	String name;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_line_country", joinColumns = @JoinColumn(name = "line_id"), inverseJoinColumns = @JoinColumn(name = "country_id"))
	List<CountryEntity> countries;

	public LineEntity(String name) {
		super();
		this.name = name;
	}
}
