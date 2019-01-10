package com.tasd.applications;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//TODO fare chiamata esistenza job
@RestController
public class ApplicationsController {

	@Autowired
    ApplicationsRepository applicationsRepository;


    @RequestMapping(value = "/api/seekers/{username}/applications/", method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationsEntity>> getApplications(@RequestHeader("X-User-Header") String loggedUser, @PathVariable String username) {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().body(applicationsRepository.findAllByUsername(username));
    }

    @RequestMapping(value = "/api/seekers/{username}/applications/", method = RequestMethod.POST)
    public ResponseEntity<ApplicationsEntity> createApplication(@RequestHeader("X-User-Header") String loggedUser, @PathVariable String username, @RequestBody ApplicationsEntity application) throws URISyntaxException {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        application.setDateCreation(new Date());
        application.setUsername(username);
        ApplicationsEntity a = applicationsRepository.save(application);
        return ResponseEntity.created(new URI("/api/seekers/" + username + "/applications/" + a.getId())).body(a);
    }


    @RequestMapping(value = "/api/seekers/{username}/applications/{applicationId}", method = RequestMethod.GET)
    public ResponseEntity<ApplicationsEntity> getJob(@RequestHeader("X-User-Header") String loggedUser, @PathVariable String username, @PathVariable long applicationId) {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<ApplicationsEntity> applicationOpt = applicationsRepository.findById(applicationId);
        if (!applicationOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ApplicationsEntity application = applicationOpt.get();
        if (username.equals(application.getUsername())) {
            return ResponseEntity.ok(application);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/api/seekers/{username}/applications/{applicationId}", method = RequestMethod.DELETE)
    public ResponseEntity<ApplicationsEntity> deleteApplication(@RequestHeader("X-User-Header") String loggedUser,
                                             @PathVariable String username,
                                             @PathVariable long applicationId) {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Optional<ApplicationsEntity> applicationOpt = applicationsRepository.findById(applicationId);
        if (!applicationOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ApplicationsEntity application = applicationOpt.get();
        if (!username.equals(application.getUsername())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        applicationsRepository.deleteById(applicationId);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/api/seekers/{username}/applications/{applicationId}", method = RequestMethod.PUT)
    public ResponseEntity<ApplicationsEntity> updateJob(@RequestHeader("X-User-Header") String loggedUser,
                                               @PathVariable String username,
                                               @PathVariable long applicationId,
                                               @RequestBody ApplicationsEntity application) {
        if (!username.equals(loggedUser)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (application.getId() != applicationId || !username.equals(application.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        applicationsRepository.save(application);
        return ResponseEntity.ok(application);
    }

}