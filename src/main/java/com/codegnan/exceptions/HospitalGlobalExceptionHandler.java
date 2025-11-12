package com.codegnan.exceptions;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HospitalGlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(HospitalGlobalExceptionHandler.class);

	@ExceptionHandler(InvalidDoctorIdException.class)
	public ResponseEntity<ErrorApi> handleInvalidDoctorId(InvalidDoctorIdException ex) {
		log.error("InvalidDoctorIdException {}" + ex.getMessage());

		ErrorApi error = new ErrorApi();
		error.setLocalDateTime(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		error.setError("Doctor Not Found");
		error.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidPatientIdException.class)
	public ResponseEntity<ErrorApi> handleInvalidPatientId(InvalidPatientIdException ex) {
		log.error("InvalidPatientIdException {}" + ex.getMessage());

		ErrorApi error = new ErrorApi();
		error.setLocalDateTime(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		error.setError("Patient Not Found");
		error.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(InvalidVisitIdException.class)
	public ResponseEntity<ErrorApi> handleInvalidVisitId(InvalidVisitIdException ex) {
		log.error("InvalidVisitIdException {}" + ex.getMessage());

		ErrorApi error = new ErrorApi();
		error.setLocalDateTime(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		error.setError("Visit Not Found");
		error.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(InvalidDateFormatException.class)
	public ResponseEntity<ErrorApi> handleInvalidDateFormatId(InvalidDateFormatException ex) {
		log.error("InvalidDateFormatException {}" + ex.getMessage());

		ErrorApi error = new ErrorApi();
		error.setLocalDateTime(LocalDateTime.now());
		error.setMessage(ex.getMessage());
		error.setError("Date Format Format");
		error.setStatus(HttpStatus.NOT_FOUND.getReasonPhrase());
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}



}
