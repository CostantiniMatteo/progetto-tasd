package com.tasd.advisor;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Repository
@FeignClient(name="seekers")
@RibbonClient(name="seekers")
public interface SeekerProxy {
	
	@RequestMapping(value = "/api/seekers/{username}", method = RequestMethod.GET)
	SeekerEntity getSeeker(String username);

}
