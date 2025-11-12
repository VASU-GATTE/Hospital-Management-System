package com.codegnan.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codegnan.exceptions.InvalidVisitIdException;
import com.codegnan.model.Doctor;
import com.codegnan.model.Patient;
import com.codegnan.model.Visit;
import com.codegnan.repository.VisitRepository;
@Service
public class VisitServiceImpl implements VisitService {

	private static final Logger log = LoggerFactory.getLogger(VisitServiceImpl.class);
	@Autowired
	private VisitRepository repo;

	@Override
	public Visit saveVisit(Visit visit) {
		log.info("Registering new Visit:Patient={},Doctor={}", visit.getPatient(), visit.getDoctor());
		Visit savedVisit = repo.save(visit);
		log.info("Visit Registration Successful ::Id{}", visit.getId());
		return savedVisit;
	}

	@Override
	public List<Visit> findvisitsbypatient(Patient patient) {
		log.info("Fetching visits for patient::{}", patient.getName());
		List<Visit> visits = repo.getAllByPatient(patient);
		log.info("Total visits retrieved for a patient {}:{}", patient.getName(), patient.getVisits());
		return visits;

	}

	@Override
	public List<Visit> findVisitsByDoctor(Doctor doctor) {
		log.info("Fetching visits by Doctor ::{}", doctor.getName());
		List<Visit> visits = repo.getAllByDoctor(doctor);
		log.info("Total visits retireved for a Doctor {}:{}", doctor.getName(), visits.size());
		return visits;
	}

	@Override
	public List<Visit> findVisits() {
		log.info("Fetching all visits from database...");
		List<Visit> visits = repo.findAll();
		log.info("Number of Records Found :: {}", visits.size());
		return visits;
	}

	@Override
	public Visit findVisitById(Long id) throws InvalidVisitIdException {
		log.info("Fetching the Visit Detials with Id {}", id);
		Optional<Visit> optVisit = repo.findById(id);
		Visit visit = optVisit.orElseThrow(() -> new InvalidVisitIdException("Visit With Id " + id + " doesn't exist"));
		log.info("visit found : Id={},Patient={},Doctor={}", visit.getId(), visit.getPatient().getName(),
				visit.getDoctor().getName());
		return visit;
	}

	@Override
	public Visit editVisit(Long id, Visit visit) throws InvalidVisitIdException {
		log.info("Editing Visit Information Using Id {}", id);
		Visit existingVisit = findVisitById(visit.getId());
		existingVisit.setPatient(visit.getPatient());
		existingVisit.setDoctor(visit.getDoctor());
		Visit updatedVisit = repo.save(existingVisit);
		log.info("Visit Updated Successfully :Id={},Patient={},Doctor={}", updatedVisit.getId(),
				updatedVisit.getPatient().getName(), updatedVisit.getDoctor().getName());

		return updatedVisit;

	}

	@Override
	public Visit deleteVisit(Long id) throws InvalidVisitIdException {
		log.warn("Request To delete visit with Id :{}", id);
		Visit visit = findVisitById(id);
		repo.deleteById(id);
		log.info("Visit Deleted Successfully Id={},Patient={},Doctor={}", visit.getId(), visit.getPatient().getName(),
				visit.getDoctor().getName());
		return visit;
	}

}
