package dev.vorstu.services;

import dev.vorstu.dto.StudentShow;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.entities.UserEntity;
import dev.vorstu.mappers.StudentMapper;
import dev.vorstu.repositories.StudentRepository;
import dev.vorstu.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StudentMapper studentMapper;

    public StudentShow updateStudent(StudentShow studentData, Long id) {
        //todo
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        studentRepository.save(studentMapper.toStudentEntityExceptId(student, studentData));
        StudentShow studentShow = studentMapper.toStudentShow(student);
        return studentShow;
    }

    public Page<StudentShow> findAll(Pageable pageable ) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            Page<StudentEntity> studentEntities = studentRepository.findAll(pageable);
            Page<StudentShow> studentDtos = studentEntities.map(this::toStudentDto);
            return studentDtos;
        }
        else {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;
            if (principal instanceof org.springframework.security.core.userdetails.User) {
                org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) principal;
                username = userDetails.getUsername();
            } else {
                username = principal.toString();
            }
            UserEntity currentUser = userRepository.findByUsername(username);
            Long userId = currentUser.getId();
            Page<StudentEntity> studentEntities = studentRepository.findAllToUser(pageable, userId);
            Page<StudentShow> studentDtos = studentEntities.map(this::toStudentDto);
            return studentDtos;
        }
    }

    public Long deleteStudentById(Long id) {
        studentRepository.deleteById(id);
        return id;
    }

    public StudentShow saveStudent(StudentShow newStudent) {
        StudentEntity student = studentMapper.toStudentEntity(newStudent);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User userDetails = (org.springframework.security.core.userdetails.User) principal;
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        UserEntity currentUser = userRepository.findByUsername(username);
        student.setUser(currentUser);
        studentRepository.save(student);
        StudentShow studentShow = studentMapper.toStudentShow(student);
        return studentShow;
    }

    public  Page<StudentShow> findByFilter(String filter, Pageable pageable) {
        Page<StudentEntity> studentEntities = studentRepository.findByFilter(filter, pageable);
        Page<StudentShow> studentDtos = studentEntities.map(this::toStudentDto);
        return studentDtos;
    }

    //Нету смысла убирать, так как в маппере все равно придется описывать функционал работы с Page<>
    private StudentShow toStudentDto(StudentEntity studentEntity) {
        StudentShow studentDto = new StudentShow();
        studentDto.setId(studentEntity.getId());
        studentDto.setName(studentEntity.getName());
        studentDto.setSurname(studentEntity.getSurname());
        studentDto.setGroup(studentEntity.getGroup());
        studentDto.setDebt(studentEntity.getDebt());
        studentDto.setComents(studentEntity.getComents());
        studentDto.setUser_id(studentEntity.getUser().getId());
        return  studentDto;
    }


}
