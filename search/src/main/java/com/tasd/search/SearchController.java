package com.tasd.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class SearchController {

    @Autowired
    JobsRepository jobsRepository;

    @RequestMapping(value = "/api/jobs/search", method = RequestMethod.GET)
    public ResponseEntity<List<JobEntity>> newJobs(@RequestParam("q") Optional<String> q,
                                                   @RequestParam("location") Optional<String> location) {
        String query = "";
        String locationAaaaaaaaa = "";

        if (q.isPresent()) {
            query = q.get();
        }
        if (location.isPresent()) {
            locationAaaaaaaaa = location.get();
        }


        List<JobEntity> result = jobsRepository.findUserByQuery(query, locationAaaaaaaaa);
        return ResponseEntity.ok(result);
    }

}
