package dev.vorstu.controllers;

import dev.vorstu.dto.Student;
import dev.vorstu.repositories.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("api/base")
public class BaseController {

    @Autowired
    private StudentRepository studentRepository;

    private Long counter = 0L;

    private Long generateId() {return counter++; };

    private final List<Student> students = new ArrayList<>();

    @PutMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student changestudent(@RequestBody Student changingstudent) {
        return updatestudent(changingstudent);
    }

    private Student updatestudent(Student student) {
        if(student.getId() == null) {
            throw new RuntimeException("id of changing student can not be null");
        }

        Student changingStudent = studentRepository.findById(student.getId())
                .orElseThrow(() -> new RuntimeException("student with id: " + student.getId() + "was not found" ));
        changingStudent.setName(student.getName());
        changingStudent.setSurname(student.getSurname());
        changingStudent.setGroup(student.getGroup());
        changingStudent.setDebt(student.getDebt());
        changingStudent.setComents(student.getComents());
        return studentRepository.save(changingStudent);
    }

    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        return removeStudent(id);
    }

    private Long removeStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student with id: " + id + " was not found");
        }
        studentRepository.deleteById(id);
        return id;
    }





    @PostMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student createStudent(@RequestBody Student newStudent) {return addStudent(newStudent);}

    private Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @GetMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Student getStudentById(@PathVariable("id") Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("student with id: " + id + " was not found"));
    }

    @GetMapping(value = "students/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudentByGroup(@RequestParam(value = "group") String group) {
        return studentRepository.findByGroup(group);
    }

    @GetMapping("students")
    public List<Student> getAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }


    @GetMapping("check")
    public String greetJava() {
        return "Hello World!" + new Date();
    }
}
