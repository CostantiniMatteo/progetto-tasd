package com.tasd.auth.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Repository
@FeignClient(name="jobcenters")
@RibbonClient(name="jobcenters")
public interface CenterEntityProxy {

	@RequestMapping(value = "/api/centers", method = RequestMethod.POST)
	public void createCenter(@RequestBody JobCenterEntity jobCenterEntity);
	@RequestMapping(value="/api/centers/exists/{centerName}", method = RequestMethod.GET)
	public boolean existsCenter(@PathVariable("centerName") String centerName);
}
