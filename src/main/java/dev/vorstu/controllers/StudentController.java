package dev.vorstu.controllers;

import dev.vorstu.dto.StudentShow;
import dev.vorstu.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("api/base")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PutMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentShow changeStudent(@RequestBody StudentShow changingStudent, @PathVariable Long id) {
        return studentService.updateStudent(changingStudent, id);
    }


    @DeleteMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long deleteStudent(@PathVariable("id") Long id) {
        return studentService.deleteStudentById(id);
    }

    @PostMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentShow createStudent(@RequestBody StudentShow newStudent) {
        return studentService.saveStudent(newStudent);
    }


    @GetMapping("students")
    public Page<StudentShow> getAllStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        return studentService.findAll(page, size, sortField, sortDirection);
    }

    @GetMapping(value = "students/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<StudentShow> searchStudents(
            @RequestParam("filter") String filter,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sortField", defaultValue = "id") String sortField,
            @RequestParam(value = "sortDirection", defaultValue = "asc") String sortDirection)
    {
        return studentService.findByFilter(filter,page,size, sortField, sortDirection);
    }


}
