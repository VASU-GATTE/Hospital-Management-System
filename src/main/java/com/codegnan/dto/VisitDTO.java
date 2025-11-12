package com.codegnan.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitDTO {
	private String visitName;
	private String visitEmail;
	private String visitMobile;
	private Date visitedDate;
}
