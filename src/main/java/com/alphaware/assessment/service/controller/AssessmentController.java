package com.alphaware.assessment.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alphaware.assessment.service.AssessmentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("alphaware/assessment")
public class AssessmentController {

	@Autowired
	AssessmentService assessmentService;

	@GetMapping("/load")
	public void loadData() {
		log.info("loadData invoked");
		assessmentService.saveData();
	}

}
