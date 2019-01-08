package com.tasd.auth.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient(name="seekers")
@RibbonClient(name="seekers")
public interface SeekerEntityProxy {
	
	@RequestMapping(value = "/api/seekers", method = RequestMethod.POST)
	void createSeeker(SeekerEntity seekerEntity);

}
