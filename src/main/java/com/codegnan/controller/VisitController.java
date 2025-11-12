package com.codegnan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codegnan.exceptions.InvalidVisitIdException;
import com.codegnan.model.Visit;
import com.codegnan.service.VisitService;

@RestController
@RequestMapping("/visits")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
		RequestMethod.PUT })

public class VisitController {

	@Autowired
	private VisitService visitService;

	private static final Logger log = LoggerFactory.getLogger(PatientController.class);

	@GetMapping("")
	public ResponseEntity<List<Visit>> getAllVisits() {
		List<Visit> visits = visitService.findVisits();
		log.info("Total Patients Retireved :: ", visits.size());
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Visit> getVisitById(@PathVariable Long id) throws InvalidVisitIdException {
		Visit visits = visitService.findVisitById(id);
		log.info("Visitor Found :: Id={}", visits.getId());
		return new ResponseEntity<>(visits, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Visit> addNewVisit(@RequestBody Visit visit) {
		Visit savedVisit = visitService.saveVisit(visit);
		log.info("Visit Saved successfully with Id={}", savedVisit.getId());
		return new ResponseEntity<>(savedVisit, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Visit> editVisit(@PathVariable Long id, @RequestBody Visit visit)
			throws InvalidVisitIdException {
		if (id != visit.getId()) {
			throw new InvalidVisitIdException("Visit Not Found with Id " + id);
		}
		Visit updatedVisit = visitService.editVisit(id, visit);
		return new ResponseEntity<>(updatedVisit, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Visit> deleteVisit(@PathVariable Long id) throws InvalidVisitIdException {
		log.warn("Request to delete Visit with Id={}" + id);
		Visit deletedVisit = visitService.deleteVisit(id);
		log.info("Visit Deleted SuccessFully : Id={},Patient={},Doctor={}",
				deletedVisit.getId(),
				deletedVisit.getPatient(),
				deletedVisit.getDoctor());
		return new ResponseEntity<>(deletedVisit, HttpStatus.OK);
	}

}
