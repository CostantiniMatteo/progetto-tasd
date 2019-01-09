package com.tasd.jobcenters;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JobCenterController {

	@Autowired
	JobCenterRepository jobCenterRepository;


	@RequestMapping(value = "/api/centers", method = RequestMethod.GET)
	public List<JobCenterEntity> getJobCenters() {
		return jobCenterRepository.findAll();
	}

	@RequestMapping(value = "/api/centers", method = RequestMethod.POST)
	public ResponseEntity<JobCenterEntity> createInstance(@RequestBody JobCenterEntity jobCenterEntity)
			throws URISyntaxException {
		jobCenterRepository.save(jobCenterEntity);
		return ResponseEntity.created(new URI("/api/centers" + jobCenterEntity.getId())).body(jobCenterEntity);
	}


	@RequestMapping(value = "/api/centers/{username}", method = RequestMethod.GET)
	public ResponseEntity<JobCenterEntity> getJobCenter(@PathVariable String username) {
		JobCenterEntity jobCenterEntity = jobCenterRepository.findByUsername(username);
		if (jobCenterEntity != null) {
			return ResponseEntity.ok().body(jobCenterEntity);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@RequestMapping(value = "/api/centers/{username}", method = RequestMethod.DELETE)
	public void deleteJobCenter(@PathVariable String username) {
		jobCenterRepository.deleteByUsername(username);
	}

	@RequestMapping(value = "/api/centers/{username}", method = RequestMethod.PUT)
	public ResponseEntity<JobCenterEntity> newJobs(@RequestHeader("X-User-Header") String loggedUser,
											 @PathVariable String username,
											 @RequestBody JobCenterEntity jobCenter) {
		if (!username.equals(loggedUser)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		if (!username.equals(jobCenter.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		jobCenterRepository.save(jobCenter);
		return ResponseEntity.ok(jobCenter);
	}

}
