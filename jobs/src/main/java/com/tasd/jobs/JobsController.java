package com.tasd.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class JobsController {
	
	@Autowired
	JobsRepository jobRepository;
	
	@RequestMapping(value = "/api/jobs", method = RequestMethod.POST)
	public void newJobs() {
		
	}
}
