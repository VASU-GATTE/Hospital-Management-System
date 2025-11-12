package com.codegnan.service;

import java.util.List;

import com.codegnan.exceptions.InvalidVisitIdException;
import com.codegnan.model.Doctor;
import com.codegnan.model.Patient;
import com.codegnan.model.Visit;

public interface VisitService {
	public Visit saveVisit(Visit visit);

	public List<Visit> findvisitsbypatient(Patient patient);

	public List<Visit> findVisitsByDoctor(Doctor doctor);

	public List<Visit> findVisits();

	public Visit findVisitById(Long id) throws InvalidVisitIdException;

	public Visit editVisit(Long id,Visit visit) throws InvalidVisitIdException;

	public Visit deleteVisit(Long id) throws InvalidVisitIdException;

}