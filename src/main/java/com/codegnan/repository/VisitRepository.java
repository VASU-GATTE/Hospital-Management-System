package com.codegnan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegnan.model.Doctor;
import com.codegnan.model.Patient;
import com.codegnan.model.Visit;

//Custom Methods : 
//	1. getAllByPatient method finds all the visit for a specific parent.Spring DataJpa
//	automatically generates the query from method one
//	2. getAllByDoctor method finds all the visit for a specific doctor. SpringDataJPA
//	automatically generates the query from method two.

@Repository
public interface VisitRepository extends JpaRepository<Visit,Long>{
	
	//Custom Methods
	
	public List<Visit> getAllByPatient(Patient patient);

	public List<Visit> getAllByDoctor(Doctor Doctor);

}
