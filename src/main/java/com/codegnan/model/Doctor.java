package com.codegnan.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


//Doctor Extends person and adds doctor specific fields 
//A doctor can have multiple visits(OneToMany relationship)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper=true) //includes person fields in equals and hashcode
public class Doctor extends Person{
	@NotBlank(message="Specialization is madatory")
	private String specialization;
	@NotBlank
	@Min(value=0,message="Experience cannot be negative and null")
	private Integer experience;
	@NotBlank(message="Degree are mandatory")
	private String degrees;
	@Min(value=0,message="salary must not be negative")
	private Double salary;
	
	@OneToMany(mappedBy="doctor",cascade=CascadeType.ALL)
	@JsonIgnore //prevents the inifinite recursion when converting to json
	List<Visit> visits;
}
