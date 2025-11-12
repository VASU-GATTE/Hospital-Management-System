package com.codegnan.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.dto.DoctorDTO;
import com.codegnan.exceptions.InvalidDoctorIdException;
import com.codegnan.mapper.DoctorMapper;
import com.codegnan.model.Doctor;
import com.codegnan.repository.DoctorRepository;

@Service
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private static final Logger log=LoggerFactory.getLogger(DoctorServiceImpl.class);
	
	@Autowired
	private DoctorRepository repo;
	
	@Autowired
	private DoctorMapper mapper;
	
	
	@Override
	public Doctor save(Doctor doctor) {
		log.info("Adding a New Doctor :: DoctorServiceImpl{}"+doctor.getName());
		Doctor doc=repo.save(doctor);
		return doc;
	}

	@Override
	public DoctorDTO findDoctor(Long id) throws InvalidDoctorIdException {
		log.info("Requested Id :: {}"+id);
		Doctor doctor=repo.findById(id).orElseThrow(()->
		new InvalidDoctorIdException("Requested Doctor with Id "+id+"not Found"));
		log.info("Doctor Retrieval Successful::{}",doctor.getName());
		return mapper.toDto(doctor);
	}

	@Override
	public DoctorDTO updateDoctor(Long id, Doctor doctor) throws InvalidDoctorIdException {
		Doctor doc=repo.findById(id).orElseThrow(()->
		new InvalidDoctorIdException("Requested Doctor with Id "+id+"not Found"));
		repo.save(doc);
		return mapper.toDto(doc);
	}

	@Override
	public DoctorDTO deleteDoctor(Long id) throws InvalidDoctorIdException {
		Doctor doc=repo.findById(id).orElseThrow(()->
		new InvalidDoctorIdException("Requested Doctor with Id "+id+"not Found"));
		repo.deleteById(id);
		log.info("Doctor Data Deleted Successfully");
		return mapper.toDto(doc);

	}

	@Override
	public List<Doctor> findAllDoctors() {
		log.info("Fetching the list of the doctors");
		List<Doctor> doctors=repo.findAll();
		log.info("Total List of Doctors Retireved::{}",doctors.size());
		return doctors;
	}

}
