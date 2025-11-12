package com.codegnan.service;

import java.util.List;

import com.codegnan.exceptions.InvalidPatientIdException;
import com.codegnan.model.Patient;

public interface PatientService {

	public Patient savePatient(Patient patient);
	
	public Patient findPatientById(Long id) throws InvalidPatientIdException;
	
	public List<Patient> findAllPatients();
	
	public Patient updatePatient(Long id,Patient patient)throws InvalidPatientIdException;
	
	public Patient deletePatient(Long id) throws InvalidPatientIdException;
}
