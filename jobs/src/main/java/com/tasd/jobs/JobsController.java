package com.tasd.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

@RestController
public class JobsController {
	
	@Autowired
	JobsRepository jobRepository;
	
	@RequestMapping(value = "/api/centers/{username}/jobs", method = RequestMethod.POST)
	public ResponseEntity<JobEntity> newJob(@RequestHeader("X-User-Header") String loggedUser, @PathVariable String username, @RequestBody JobEntity job) throws URISyntaxException {
		if (!loggedUser.equals(username)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		job.setDateCreation(new Date());
		job.setUsername(username);
		JobEntity j = jobRepository.save(job);
		return ResponseEntity.created(new URI("/api/centers/" + username + "/jobs/" + j.getId())).body(j);
	}

	// PROVA sei una gioia
	@RequestMapping(value = "/api/centers/{username}/jobs", method = RequestMethod.GET)
	public ResponseEntity<List<JobEntity>> newJobs(@RequestHeader("X-User-Header") String loggedUser, @PathVariable String username) {
		if (!loggedUser.equals(username)){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		return ResponseEntity.ok().body(jobRepository.findAllByUsername(username));
	}

}
