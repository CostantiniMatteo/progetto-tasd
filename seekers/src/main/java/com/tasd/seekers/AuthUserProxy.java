package com.tasd.seekers;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
@FeignClient(name="auth")
@RibbonClient(name="auth")
public interface AuthUserProxy {

	@DeleteMapping("/users/{username}")
    public void deleteAuthUser(@PathVariable String username);
	
}
