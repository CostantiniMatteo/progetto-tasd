package com.tasd.applications;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name = "jobs")
@RibbonClient(name = "jobs")
public interface JobEntityProxy {
	
	@RequestMapping(value = "/api/centers/{username}/jobs/{jobId}", method = RequestMethod.GET)
	JobEntityBean getJob(@PathVariable String username, @PathVariable long jobId);

}
