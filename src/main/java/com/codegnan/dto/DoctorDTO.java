package com.codegnan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDTO {
	private Long doctorId;
	private String doctorName;
	private String doctorGender;
	private String doctorSpecialization;
	private Integer doctorExperience;
}
