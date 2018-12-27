package com.tasd.jobcenters;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobCenterController {
	@Autowired
	JobCenterRepository jobCenterRepository;
	
	@RequestMapping(value = "/api/centers", method = RequestMethod.POST)
	public JobCenterEntity createInstance() {
		JobCenterEntity jobCenterEntity = new JobCenterEntity(2);
		jobCenterRepository.save(jobCenterEntity);
		return jobCenterEntity;
	}
	//TODO NECESSARIO RITORNARE STATUS 404
	@RequestMapping(value = "/api/centers/{id}", method = RequestMethod.GET)
	public JobCenterEntity getJobCenter(@PathVariable long id) {
		return jobCenterRepository.findById(id).get();
	}
	
}
