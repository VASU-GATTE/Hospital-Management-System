package com.codegnan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codegnan.dto.PatientDTO;
import com.codegnan.model.Patient;
@Mapper(componentModel="spring")
public interface PatientMapper {
	//entity to dto
    @Mapping(target="name",source="patientName")
    @Mapping(target="email",source="patientEmail")
    @Mapping(target="mobile",source="patientMobile")
    @Mapping(target="gender",source="patientGender")
    Patient toEntity(PatientDTO patient);
    
    @Mapping(source="name",target="patientName")
    @Mapping(source="email",target="patientEmail")
    @Mapping(source="mobile",target="patientMobile")
    @Mapping(source="gender",target="patientGender")
    PatientDTO toDTO(Patient patient);

}
