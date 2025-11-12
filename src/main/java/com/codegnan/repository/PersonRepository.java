package com.codegnan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codegnan.model.Person;
//Because Doctor and patient inherit from person 
//this repository can perform queries on person object
//but usually we work with Doctor Repo or Patient Repo for type safety


@Repository
public interface PersonRepository extends JpaRepository<Person,Long>{

}
