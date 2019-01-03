package com.tasd.jobcenters;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobCenterController {
	
	@Autowired
	JobCenterRepository jobCenterRepository;
	
	@RequestMapping(value = "/api/centers", method = RequestMethod.POST)
	public void createInstance() {
		JobCenterEntity jobCenterEntity = new JobCenterEntity(2);
		jobCenterRepository.save(jobCenterEntity);
	}
	
	//TODO NECESSARIO RITORNARE STATUS 404: https://spring.io/guides/tutorials/bookmarks/
	@RequestMapping(value = "/api/centers/{id}", method = RequestMethod.GET)
	public JobCenterEntity getJobCenter(@PathVariable long id) {
		return jobCenterRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
	}
	
	@RequestMapping(value = "/api/centers/{id}", method = RequestMethod.DELETE)
	public void deleteJobCenter(@PathVariable long id) {
		jobCenterRepository.deleteById(id);
	}
	
}
