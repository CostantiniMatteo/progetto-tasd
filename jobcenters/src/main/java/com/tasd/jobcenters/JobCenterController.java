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

	@RequestMapping(value = "/api/centers", method = RequestMethod.POST)
	public ResponseEntity<JobCenterEntity> createInstance(@RequestBody JobCenterEntity jobCenterEntity) throws URISyntaxException {
		jobCenterRepository.save(jobCenterEntity);
		return ResponseEntity.created(new URI("/api/centers" + jobCenterEntity.getId())).body(jobCenterEntity);
	}
	
	@RequestMapping(value = "/api/centers", method = RequestMethod.GET)
	public List<JobCenterEntity> getJobCenters(){
		return jobCenterRepository.findAll();
	}
	
	
	@RequestMapping(value = "/api/centers/{username}", method = RequestMethod.GET)
	public ResponseEntity<JobCenterEntity> getJobCenter(@PathVariable String username) {
		try {
			JobCenterEntity jobCenterEntity = jobCenterRepository.findByUsername(username);
			return ResponseEntity.ok().body(jobCenterEntity);
		}
		catch(NoSuchElementException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}
	
	@RequestMapping(value = "/api/centers/{id}", method = RequestMethod.DELETE)
	public void deleteJobCenter(@PathVariable long id) {
		jobCenterRepository.deleteById(id);
	}
	
	
}
