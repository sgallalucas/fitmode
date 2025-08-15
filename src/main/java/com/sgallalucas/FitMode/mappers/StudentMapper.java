package com.sgallalucas.FitMode.mappers;

import com.sgallalucas.FitMode.dtos.StudentDTO;
import com.sgallalucas.FitMode.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    Student toEntity(StudentDTO dto);

    StudentDTO toDTO(Student student);
}
