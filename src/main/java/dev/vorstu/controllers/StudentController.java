package dev.vorstu.controllers;

import dev.vorstu.services.StudentService;
import dev.vorstu.entities.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("api/base")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PutMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentEntity changestudent(@RequestBody StudentEntity changingstudent) {
        return studentService.updateStudent(changingstudent);
    }


    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudentById(id);
    }

    @PostMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentEntity createStudent(@RequestBody StudentEntity newStudent) {
        return studentService.saveStudent(newStudent);
    }


    @GetMapping("students")
    public Page<StudentEntity> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return studentService.findAll(page, size);
    }

    @GetMapping(value = "students/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentEntity> searchStudents(
            @RequestParam("filter") String filter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {
        return studentService.findByFilter(filter,page,size);
    }

}
