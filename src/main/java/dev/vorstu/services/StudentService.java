package dev.vorstu.services;

import dev.vorstu.dto.StudentCreateDto;
import dev.vorstu.dto.StudentDto;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public StudentEntity updateStudent(StudentEntity student) {
        if(student.getId() == null) {
            throw new RuntimeException("id of changing student can not be null");
        }

        StudentEntity changingStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("student with id: " + student.getId() + "was not found" ));
        changingStudent.setName(student.getName());
        changingStudent.setSurname(student.getSurname());
        changingStudent.setGroup(student.getGroup());
        changingStudent.setDebt(student.getDebt());
        changingStudent.setComents(student.getComents());
        return studentRepository.save(changingStudent);
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
