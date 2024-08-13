package com.alphaware.assessment.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.alphaware.assessment.dto.RestResponseDto;
import com.alphaware.assessment.entity.ContinentEntity;
import com.alphaware.assessment.entity.CountryEntity;
import com.alphaware.assessment.entity.LineEntity;
import com.alphaware.assessment.entity.OceanEntity;
import com.alphaware.assessment.repository.ContinentRepository;
import com.alphaware.assessment.repository.CountryRepository;
import com.alphaware.assessment.repository.LineRepository;
import com.alphaware.assessment.repository.OceanRepository;
import com.alphaware.assessment.service.AssessmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AssessmentServiceImpl implements AssessmentService {

	@Value("${alphaware.assessment.api}")
	String Api;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	ContinentRepository continentRepository;

	@Autowired
	CountryRepository countryRepository;

	@Autowired
	OceanRepository oceanRepository;

	@Autowired
	LineRepository lineRepository;

	public void saveData() {
		ResponseEntity<List<RestResponseDto>> response = restTemplate.exchange(Api, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<RestResponseDto>>() {
				});
		List<RestResponseDto> objects = response.getBody();
		if (response.getStatusCode() == HttpStatusCode.valueOf(200) && CollectionUtils.isEmpty(response.getBody())) {

			List<String> allCountries = objects.stream().flatMap(dto -> dto.getDevelopedCountries().stream()).toList();

			List<String> allLines = objects.stream().flatMap(dto -> dto.getLines().stream()).toList();

			List<String> allOceans = objects.stream().flatMap(dto -> dto.getOceans().stream()).toList();

			List<CountryEntity> savedCountries = countryRepository.findbyCountryNameIn(allCountries);

			List<OceanEntity> savedOceans = oceanRepository.findbyOceanNameIn(allOceans);
			List<LineEntity> savedLines = lineRepository.findbyNameIn(allLines);
			List<ContinentEntity> continents = objects.stream()
					.map(request -> ContinentEntity.builder().areaSqKm(request.getAreaSqKm()).code(request.getCode())
							.countries(request.getCountries()).name(request.getName())
							.population(request.getPopulation())
							.developedCountries(getCountries(allCountries, savedCountries, request))
							.lines(getLines(allLines, savedLines, request))
							.oceans(getOceans(allOceans, savedOceans, request)).build()

					).toList();
			continentRepository.saveAll(continents);
			log.info("request saved");

		} else {
			log.info("Unable to fetch request");
		}
	}

	public List<CountryEntity> getCountries(List<String> allCountries, List<CountryEntity> savedCountries,
			RestResponseDto request) {
		List<CountryEntity> newCountries = allCountries.stream()
				.filter(countryName -> savedCountries.stream()
						.noneMatch(country -> country.getCountryName().equals(countryName)))
				.map(c -> CountryEntity.builder().countryName(c).build()).toList();

		List<CountryEntity> fetchedCOuntries = savedCountries.stream()
				.filter(element -> request.getDevelopedCountries().contains(element.getCountryName())).toList();

		newCountries.addAll(fetchedCOuntries);
		return newCountries;
	}

	public List<OceanEntity> getOceans(List<String> allOceans, List<OceanEntity> savedOceans, RestResponseDto request) {
		List<OceanEntity> newOceans = allOceans.stream()
				.filter(oceanName -> savedOceans.stream().noneMatch(ocean -> ocean.getOceanName().equals(oceanName)))
				.map(o -> OceanEntity.builder().oceanName(o).build()).toList();

		List<OceanEntity> fetchedOceans = savedOceans.stream()
				.filter(element -> request.getOceans().contains(element.getOceanName())).toList();

		newOceans.addAll(fetchedOceans);
		return newOceans;
	}

	public List<LineEntity> getLines(List<String> allLines, List<LineEntity> savedLines, RestResponseDto request) {
		List<LineEntity> newLines = allLines.stream()
				.filter(lineName -> savedLines.stream().noneMatch(line -> line.getName().equals(lineName)))
				.map(l -> LineEntity.builder().name(l).build()).toList();

		List<LineEntity> fetchedLines = savedLines.stream()
				.filter(element -> request.getOceans().contains(element.getName())).toList();

		newLines.addAll(fetchedLines);
		return newLines;
	}

}
