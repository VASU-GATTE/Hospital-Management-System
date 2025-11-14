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

import com.codegnan.exceptions.InvalidDoctorIdException;
import com.codegnan.model.Doctor;
import com.codegnan.model.Visit;
import com.codegnan.service.DoctorService;
import com.codegnan.service.VisitService;


@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins="*",methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class DoctorController {
	@Autowired
	public DoctorService docService;
	@Autowired
	public VisitService visService;
	
	private static final Logger log=LoggerFactory.getLogger(DoctorController.class);
	
	@GetMapping("")
	public ResponseEntity<List<Doctor>> getAllDoctors(){
		List<Doctor> doctors=docService.findAllDoctors();
		log.info("Successfully retrieved {} doctors",doctors.size());
		return ResponseEntity.ok(doctors);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) throws InvalidDoctorIdException{
		Doctor doctor=docService.findDoctor(id);
		log.info("Doctor found : Name{}",doctor.getName());
		return ResponseEntity.ok(doctor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Doctor> updateDoctorById(@PathVariable Long id,@RequestBody Doctor doctor) throws InvalidDoctorIdException{
		log.info("DoctorController::updateDoctor {}", id);
		if(id!=doctor.getId()) {
			throw new InvalidDoctorIdException("Doctor Not Found with Id::"+id);
		}
		Doctor updatedDoctor=docService.updateDoctor(id, doctor);
		log.info("Doctor Updated Successfully :: Name={}",updatedDoctor.getName());
		return ResponseEntity.ok(updatedDoctor);
	}
	
	@GetMapping("/{id}/visits")
	public ResponseEntity<List<Visit>> getVisitsByDoctor(@PathVariable Long id)throws InvalidDoctorIdException{
		Doctor doc=docService.findDoctor(id);
		List<Visit> visits=visService.findVisitsByDoctor(doc);
		log.info("Total Visits Retrieved for doctor Id={}:{}",id,doc.getName());
		return ResponseEntity.ok(visits);
	}
	
	@PostMapping("")
	public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doc){
		Doctor savedDoctor=docService.save(doc);
		log.info("New Doctor Record Inserted Successfully :: Name={}",savedDoctor.getName());
		return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Doctor> deleteDoctor(@PathVariable Long id) throws InvalidDoctorIdException{
		log.warn("requested to retrieve with Id :{}",id);
		Doctor deletedDoctor=docService.deleteDoctor(id);
		log.info("Doctor Record Deleted Successfully :: Name={}",deletedDoctor.getName());
		return ResponseEntity.ok(deletedDoctor);
	}
	
	
	

}
