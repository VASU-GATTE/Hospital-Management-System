package com.codegnan.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.codegnan.dto.DoctorDTO;
import com.codegnan.model.Doctor;
@Mapper(componentModel="spring")
public interface DoctorMapper {
	//entity to dto
    @Mapping(target="name",source="doctorName")
    @Mapping(target="gender",source="doctorGender")
    @Mapping(target="specialization",source="doctorSpecialization")
    @Mapping(target="experience",source="doctorExperience")
    Doctor toEntity(DoctorDTO doctor);
    
    @Mapping(source="name",target="doctorName")
    @Mapping(source="gender",target="doctorGender")
    @Mapping(source="specialization",target="doctorSpecialization")
    @Mapping(source="experience",target="doctorExperience")
    DoctorDTO toDto(Doctor doctor);
    
    

}
