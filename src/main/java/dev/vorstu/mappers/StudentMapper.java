package dev.vorstu.mappers;

import dev.vorstu.dto.StudentShow;
import dev.vorstu.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper {

    StudentEntity toStudentEntity(StudentShow studentShow);
    StudentShow toStudentShow(StudentEntity studentEntity);

    @Mapping(target = "id", ignore = true)
    StudentEntity toStudentEntityExceptId(@MappingTarget StudentEntity studentEntity, StudentShow studentShow);

}
