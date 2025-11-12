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

import com.codegnan.exceptions.InvalidPatientIdException;
import com.codegnan.model.Patient;
import com.codegnan.model.Visit;
import com.codegnan.service.PatientService;
import com.codegnan.service.VisitService;


@RestController
@RequestMapping("/patients")
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class PatientController {
	@Autowired
	private PatientService patientService;
	@Autowired
	private VisitService visitService;
	
	private static final Logger log=LoggerFactory.getLogger(PatientController.class);
	
	@GetMapping("")
	public ResponseEntity<List<Patient>> getAllPatients(){
		List<Patient> patients=patientService.findAllPatients();
		log.info("Total Patients Retireved :: ",patients.size());
		return new ResponseEntity<>(patients,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws InvalidPatientIdException{
		Patient patient=patientService.findPatientById(id);
		log.info("Patinet Found :: Id={},Name={}",patient.getId(),patient.getName());
		return new ResponseEntity<>(patient,HttpStatus.OK);
	}
	
	@GetMapping("/{id}/visits")
	public ResponseEntity<List<Visit>> getVisitsByPatient(@PathVariable Long id)throws InvalidPatientIdException{
		Patient patient=patientService.findPatientById(id);
		List<Visit> visits=visitService.findvisitsbypatient(patient);
		log.info("Total Visits retrieved for the patient Id={}:{}",patient.getId(),visits.size());
		return new ResponseEntity<>(visits,HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Patient> addNewPatient(@RequestBody Patient patient){
		Patient savedPatient=patientService.savePatient(patient);
		log.info("Patient Saved successfully with Id={}",savedPatient.getId());
		return new ResponseEntity<>(savedPatient,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Patient> editPatient(@PathVariable Long id,@RequestBody Patient patient) throws InvalidPatientIdException{
		if(id!=patient.getId()) {
			throw new InvalidPatientIdException("Patient Not Found with Id "+id);
		}
		Patient updatedPatient=patientService.updatePatient(id, patient);
		return new ResponseEntity<>(updatedPatient,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Patient> deletePatient(@PathVariable Long id) throws InvalidPatientIdException{
		log.warn("Request to delete Patient with Id={}"+id);
		Patient deletedPatient=patientService.deletePatient(id);
		log.info("Patient Deleted SuccessFully : Id={},Name={}",deletedPatient.getId(),deletedPatient.getName());
		return new ResponseEntity<>(deletedPatient,HttpStatus.OK);
	}
	

}
