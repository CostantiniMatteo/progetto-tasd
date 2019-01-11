package com.tasd.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class JobsController {

    @Autowired
    JobsRepository jobRepository;


    @RequestMapping(value = "/api/centers/{username}/jobs/", method = RequestMethod.GET)
    public ResponseEntity<List<JobEntity>> getJobs(@PathVariable String username) {
    	List<JobEntity> jobs = jobRepository.findAllByUsername(username);
    	if (jobs == null)
    		return ResponseEntity.notFound().build();
        return ResponseEntity.ok(jobs);
    }

    @RequestMapping(value = "/api/centers/{username}/jobs", method = RequestMethod.POST)
    public ResponseEntity<JobEntity> createJob(@RequestHeader("X-User-Header") String loggedUser, @PathVariable String username, @RequestBody JobEntity job) throws URISyntaxException {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        job.setDateCreation(new Date());
        job.setUsername(username);
        JobEntity j = jobRepository.save(job);
        return ResponseEntity.created(new URI("/api/centers/" + username + "/jobs/" + j.getId())).body(j);
    }


    @RequestMapping(value = "/api/centers/{username}/jobs/{jobId}", method = RequestMethod.GET)
    public ResponseEntity<JobEntity> getJob(@RequestHeader("X-User-Header") String loggedUser, @PathVariable String username, @PathVariable long jobId) {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<JobEntity> jobOpt = jobRepository.findById(jobId);
        if (!jobOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        JobEntity job = jobOpt.get();
        if (username.equals(job.getUsername())) {
            return ResponseEntity.ok(job);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/api/centers/{username}/jobs/{jobId}", method = RequestMethod.DELETE)
    public ResponseEntity<JobEntity> deleteJob(@RequestHeader("X-User-Header") String loggedUser,
                                             @PathVariable String username,
                                             @PathVariable long jobId) {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<JobEntity> jobOpt = jobRepository.findById(jobId);
        if (!jobOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        JobEntity job = jobOpt.get();
        if (!username.equals(job.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        jobRepository.deleteById(jobId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/api/centers/{username}/jobs/{jobId}", method = RequestMethod.PUT)
    public ResponseEntity<JobEntity> updateJob(@RequestHeader("X-User-Header") String loggedUser,
                                               @PathVariable String username,
                                               @PathVariable long jobId,
                                               @RequestBody JobEntity job) {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (job.getId() != jobId || !username.equals(job.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        jobRepository.save(job);
        return ResponseEntity.ok(job);
    }


}
