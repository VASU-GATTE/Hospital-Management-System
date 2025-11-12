package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.exceptions.InvalidPatientIdException;
import com.codegnan.model.Patient;
import com.codegnan.repository.PatientRepository;
@Service
public class PatientServiceImpl implements PatientService {
	
	private static final Logger log=LoggerFactory.getLogger(PatientServiceImpl.class);

	@Autowired
	private PatientRepository repo; 
	
	@Override
	public Patient savePatient(Patient patient) {
		log.info("Registering new Patient :: {}",patient.getName());
		Patient savedPatient=repo.save(patient);
		log.info("Patient Registration Successful :: Id{}",patient.getId());
		return savedPatient;
	}

	@Override
	public Patient findPatientById(Long id) throws InvalidPatientIdException{
		log.info("Looking for patient with ID :: {}",id);
		Optional<Patient> optPatient=repo.findById(id);
		Patient patient=optPatient.orElseThrow(()->
		new InvalidPatientIdException("Patient With Id : "+id+"Doesn't exist"));
		
		log.info("Fetch patient with Id Successfull :{}",patient.getId());
		
		return patient;
	}

	@Override
	public List<Patient> findAllPatients() {
		log.info("Fetching all the Patients List....");
		List<Patient> patients=repo.findAll();
		log.info("Total Records Found :: {}",patients.size());
		return patients;
	}

	@Override
	public Patient updatePatient(Long id, Patient patient) throws InvalidPatientIdException {
		log.info("Updating patient with Id {}",patient.getId());
		Patient existingPatient=findPatientById(patient.getId());
		existingPatient.setName(patient.getName());
		existingPatient.setAge(patient.getAge());
		existingPatient.setGender(patient.getGender());
		existingPatient.setMobile(patient.getMobile());
		existingPatient.setEmail(patient.getEmail());
		Patient updatedPatient=repo.save(existingPatient);
		log.info("Patient Updated Successfully :: Id={},Name={}"
				,updatedPatient.getId(),updatedPatient.getName());
		
		return updatedPatient;
		
	}

	@Override
	public Patient deletePatient(Long id) throws InvalidPatientIdException {
		log.warn("Request to delete Patient with Id {}",id);
		
		Patient patient =findPatientById(id);
		repo.deleteById(id);
		log.info("Patient deleted Successfully Id={},Name={}"
				,patient.getId(),patient.getName());
		
		return patient;
	}

}
