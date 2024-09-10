package dev.vorstu.services;

import dev.vorstu.dto.StudentCreateDto;
import dev.vorstu.dto.StudentDto;
import dev.vorstu.dto.StudentUpdateDto;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentDto updateStudent(StudentUpdateDto studentData, Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        toStudentEntity(studentData, student);
        studentRepository.save(student);
        var studentDto = toStudentDto(student);
        return  studentDto;
    }

    public Page<StudentEntity> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findAll(pageable);
    }

    public Long deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        return id;
    }

    public StudentDto saveStudent(StudentCreateDto newStudent) {
        var student = toStudentEntity(newStudent);
        studentRepository.save(student);
        var studentDto = toStudentDto(student);
        return studentDto;
    }

    public Page<StudentEntity> findByFilter(String filter, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentRepository.findByFilter(filter, pageable);
    }

    private StudentEntity toStudentEntity(StudentCreateDto studentCreateDto) {
        var studentEntity  = new StudentEntity();
        studentEntity.setDebt(studentCreateDto.getDebt());
        studentEntity.setSurname(studentCreateDto.getSurname());
        studentEntity.setName(studentCreateDto.getName());
        studentEntity.setComents(studentCreateDto.getComents());
        studentEntity.setGroup(studentCreateDto.getGroup());
        return studentEntity;
    }

    private StudentEntity toStudentEntity(StudentUpdateDto studentUpdateDto, StudentEntity studentEntity) {
        studentEntity.setDebt(studentUpdateDto.getDebt());
        studentEntity.setSurname(studentUpdateDto.getSurname());
        studentEntity.setName(studentUpdateDto.getName());
        studentEntity.setComents(studentUpdateDto.getComents());
        studentEntity.setGroup(studentUpdateDto.getGroup());
        return studentEntity;
    }

    private StudentDto toStudentDto(StudentEntity studentEntity) {
        var studentDto = new StudentDto();
        studentDto.setId(studentEntity.getId());
        studentDto.setName(studentEntity.getName());
        studentDto.setSurname(studentEntity.getSurname());
        studentDto.setGroup(studentEntity.getGroup());
        studentDto.setDebt(studentEntity.getDebt());
        studentDto.setComents(studentEntity.getComents());
        return  studentDto;
    }


}
