package com.alphaware.assessment.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResponseDto {

	private String code;
	private String name;
	private long areaSqKm;
	private long population;
	private List<String> lines;
	private int countries;
	private List<String> oceans;
	private List<String> developedCountries;

}
