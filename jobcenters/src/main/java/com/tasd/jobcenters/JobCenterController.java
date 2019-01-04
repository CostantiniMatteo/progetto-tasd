package com.tasd.jobcenters;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class JobCenterController {
	
	@Autowired
	JobCenterRepository jobCenterRepository;

	@RequestMapping(value = "/api/centers", method = RequestMethod.POST)
	public ResponseEntity<JobCenterEntity> createInstance(@ModelAttribute JobCenterEntity jobCenterEntity) throws URISyntaxException {
		if(jobCenterRepository.findByName(jobCenterEntity.getName()) == null) {
			jobCenterRepository.save(jobCenterEntity);
			return ResponseEntity.created(new URI("/api/centers")).body(jobCenterEntity);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}
	
	@RequestMapping(value = "/api/centers", method = RequestMethod.GET)
	public List<JobCenterEntity> getJobCenters(){
		return jobCenterRepository.findAll();
	}
	
	
	@RequestMapping(value = "/api/centers/{id}", method = RequestMethod.GET)
	public ResponseEntity<JobCenterEntity> getJobCenter(@PathVariable long id) {
		try {
			JobCenterEntity jobCenterEntity = jobCenterRepository.findById(id).get();
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
