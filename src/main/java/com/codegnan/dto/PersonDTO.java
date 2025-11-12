package com.codegnan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
	private String personName;
	private String personEmail;
	private String personMobile;
	private String personGender;
}
