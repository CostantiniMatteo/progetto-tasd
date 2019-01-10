package com.tasd.seekers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * api/seekers/ GET, POST api/seekers/{username}/ GET, PUT, DELETE
 * api/seekers/{username}/skills/ GET, POST
 * api/seekers/{username}/skills/{skillId}/ DELETE
 * 
 * @author thekeymaker
 *
 */
@RestController
public class SeekerController {

	@Autowired
	private SeekerRepository seekerRepository;

	@RequestMapping(value = "/api/seekers", method = RequestMethod.GET)
	public ResponseEntity<List<SeekerEntity>> getAllSeekers() {
		return ResponseEntity.ok().body(seekerRepository.findAll());
	}

	@RequestMapping(value = "/api/seekers", method = RequestMethod.POST)
	public ResponseEntity<SeekerEntity> createInstance(@RequestBody SeekerEntity seekerEntity) throws URISyntaxException {
		return ResponseEntity.created(new URI("/api/seekers" + seekerEntity.getId()))
				.body(seekerRepository.save(seekerEntity));
	}

	@RequestMapping(value = "/api/seekers/{username}", method = RequestMethod.GET)
	public ResponseEntity<SeekerEntity> getJobSeeker(@RequestHeader("X-User-Header") String loggedUser,
			@PathVariable String username) {
		if (!username.equals(loggedUser)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			SeekerEntity seekerEntity = seekerRepository.findByUsername(username);
			if (seekerEntity != null) {
				return ResponseEntity.ok().body(seekerEntity);
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@RequestMapping(value = "/api/seekers/{username}", method = RequestMethod.DELETE)
	public ResponseEntity deleteJobSeeker(@RequestHeader("X-User-Header") String loggedUser,
			@PathVariable String username) {
		if (!username.equals(loggedUser)) 
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		if (seekerRepository.existsByUsername(username)) {
			seekerRepository.delete(seekerRepository.findByUsername(username));
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@RequestMapping(value = "/api/seekers/{username}/skills", method = RequestMethod.GET)
	public ResponseEntity<List<String>> getSkills(@RequestHeader("X-User-Header") String loggedUser,
			@PathVariable String username) throws URISyntaxException {
		
		if (!username.equals(loggedUser))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		if (seekerRepository.findByUsername(username) == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		return ResponseEntity.ok(seekerRepository.findByUsername(username).getSkills());
	}

	@RequestMapping(value = "/api/seekers/{username}/skills", method = RequestMethod.PUT)
	public ResponseEntity<List<String>> postSkills(@RequestHeader("X-User-Header") String loggedUser,
			@PathVariable String username, @RequestBody List<String> skills) throws URISyntaxException {
		if (!username.equals(loggedUser))
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		
		if (seekerRepository.findByUsername(username) == null) 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			
		SeekerEntity seeker = seekerRepository.findByUsername(username);
		seeker.setSkills(skills);
		seekerRepository.save(seeker);
		return ResponseEntity.created(new URI("api/seekers/" + username + "skills"))
					.body(seekerRepository.findByUsername(username).getSkills());
		
	}

	// TODO controllare che la skill appartenga a quell'utente prima di cancellarla
	@RequestMapping(value = "/api/seekers/{username}/skills/{skillId}", method = RequestMethod.DELETE)
	public ResponseEntity deletetSkill(@RequestHeader("X-User-Header") String loggedUser,
			@PathVariable String username, @PathVariable long skillId) throws URISyntaxException {
		if (!username.equals(loggedUser)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		} else {
			//TODO eliminare una skill dalla lista skillRepository.deleteById(skillId);
			return ResponseEntity.ok().build();
		}
	}

}
