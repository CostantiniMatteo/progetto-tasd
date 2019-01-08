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
                                                   @RequestParam("sinceDate") Optional<Date> sinceDate,
                                                   @RequestParam("toDate") Optional<Date> toDate,
                                                   @RequestParam("location") Optional<String> location) {
        String query = "";
        Date sinceDateAaaaaaaaaa = null;
        Date toDateAaaaaaaaaaa = null;
        String locationAaaaaaaaa = "";
        if (q.isPresent()) {
            query = q.get();
        }
        if (sinceDate.isPresent()) {
            sinceDateAaaaaaaaaa = sinceDate.get();
        }
        if (toDate.isPresent()) {
            toDateAaaaaaaaaaa = toDate.get();
        }
        if (location.isPresent()) {
            locationAaaaaaaaa = location.get();
        }


        List<JobEntity> result = jobsRepository.findUserByQuery(query, sinceDateAaaaaaaaaa, toDateAaaaaaaaaaa, locationAaaaaaaaa);
        return ResponseEntity.ok(result);
    }

}
