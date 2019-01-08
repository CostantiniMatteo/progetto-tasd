package com.tasd.seekers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeekerController {

	@Autowired
	private SeekerRepository seekerRepository;

	@RequestMapping(value = "/api/seekers", method = RequestMethod.GET)
	public ResponseEntity<List<SeekerEntity>> getAllSeekers() {
		return ResponseEntity.ok().body(seekerRepository.findAll());
	}

	@RequestMapping(value = "/api/seekers", method = RequestMethod.POST)
	public ResponseEntity<SeekerEntity> createInstance(@RequestBody SeekerEntity seekerEntity)
			throws URISyntaxException {
		seekerRepository.save(seekerEntity);
		return ResponseEntity.created(new URI("/api/seekers" + seekerEntity.getId())).body(seekerEntity);
	}

	@RequestMapping(value = "/api/seekers/{username}", method = RequestMethod.GET)
	public ResponseEntity<SeekerEntity> getJobCenter(@PathVariable String username) {
		//TODO authorization control
		SeekerEntity seekerEntity = seekerRepository.findByUsername(username);
		if (seekerEntity != null) {
			return ResponseEntity.ok().body(seekerEntity);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

}
