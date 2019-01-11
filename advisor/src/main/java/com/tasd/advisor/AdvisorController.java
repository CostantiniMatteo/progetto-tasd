package com.tasd.advisor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvisorController {

	@Autowired
	private JobsRepository jobsRepository;

	@RequestMapping(value = "api/seekers/{user_id}/suggestions", method = RequestMethod.GET)
	public ResponseEntity<List<JobEntity>> getAllSeekers() {
		return ResponseEntity.ok().body(jobsRepository.findAllByLocation("Milan"));
	}
}
