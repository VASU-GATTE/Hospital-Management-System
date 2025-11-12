package com.codegnan.service;

import java.util.List;

import com.codegnan.dto.DoctorDTO;
import com.codegnan.exceptions.InvalidDoctorIdException;
import com.codegnan.model.Doctor;


/**This Class is used to add or delete update or retrieve the data about the 
 * doctor class 
 */
public interface DoctorService {
	
	Doctor save(Doctor doctor);
	
	DoctorDTO findDoctor(Long id) throws InvalidDoctorIdException;
	
	DoctorDTO updateDoctor(Long id,Doctor doctor) throws InvalidDoctorIdException;
	
	DoctorDTO deleteDoctor(Long id) throws InvalidDoctorIdException;
	
	List<Doctor> findAllDoctors();
}
