package com.codegnan.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)//each subclass has its own subclass
public class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank(message="Name is mandatory")
	@NonNull private String name;				// name must not be Empty
	
	@Email(message="Email Should be Valid")
	@NotBlank
	@NonNull private String email;             //Validation must be in the email format 
	
	@Pattern(regexp="\\d{10}",message="Mobile Number must be 10 digits")
	@NotBlank
	@NonNull private String mobile;				//mobile number must be 10 digits 
	
	@NotBlank(message="Gender id mandatory") //cannot be null or empty
	@NonNull private String gender;
}
