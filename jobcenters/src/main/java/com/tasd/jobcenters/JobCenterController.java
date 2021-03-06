package com.tasd.jobcenters;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JobCenterController {

	@Autowired
	private JobCenterRepository jobCenterRepository;

	@Autowired
	private JobsProxy jobsProxy;

	@RequestMapping(value = "/api/centers", method = RequestMethod.GET)
	public List<JobCenterEntity> getJobCenters() {
		return jobCenterRepository.findAll();
	}

	@RequestMapping(value = "/api/centers", method = RequestMethod.POST)
	public ResponseEntity<JobCenterEntity> createJobCenter(@RequestBody JobCenterEntity jobCenterEntity)
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
	public ResponseEntity<JobCenterEntity> deleteJobCenter(@RequestHeader("X-User-Header") String loggedUser,
														   @PathVariable String username) {
		if (!username.equals(loggedUser)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		JobCenterEntity jobCenter = jobCenterRepository.findByUsername(username);
		jobsProxy.deleteAllByUsername(loggedUser, username);
		jobCenterRepository.delete(jobCenter);
		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/api/centers/{username}", method = RequestMethod.PUT)
	public ResponseEntity<JobCenterEntity> updateJobCenter(@RequestHeader("X-User-Header") String loggedUser,
														   @PathVariable String username,
														   @RequestBody JobCenterEntity jobCenter) {
		if (!username.equals(loggedUser)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		if (!username.equals(jobCenter.getUsername())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!checkField(jobCenter)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		JobCenterEntity jobCenterOld = jobCenterRepository.findByUsername(username);
		jobCenterOld.setName(jobCenter.getName());
		jobCenterOld.setEmail(jobCenter.getEmail());
		jobCenterRepository.save(jobCenterOld);
		return ResponseEntity.ok(jobCenterOld);
	}
	
	
	private boolean checkField(JobCenterEntity jobCenterEntity) {
		if(jobCenterEntity.getUsername() == null) {
			return false;
		}
		if(jobCenterEntity.getEmail() == null) {
			return false;
		}
		if(jobCenterEntity.getName() == null) {
			return false;
		}
		return true;
	}

}
