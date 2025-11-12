package com.codegnan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {
	private String patientName;
	private Date patientRegDate;
	private int patientAge;
	private String patientEmail;
	private String patientMobile;
	private String patientGender;
}
