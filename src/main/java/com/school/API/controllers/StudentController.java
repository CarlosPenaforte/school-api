package com.school.API.controllers;

import com.school.API.exceptions.StudentAlreadyRegisteredException;
import com.school.API.exceptions.StudentNotFoundException;
import com.school.API.exceptions.UserAlreadyRegisteredException;
import com.school.API.models.Student;
import com.school.API.models.User;
import com.school.API.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/admin")
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> findById(@PathVariable Long id) throws StudentNotFoundException {
        return ResponseEntity.ok(studentService.findById(id));
    }

    @PostMapping("/students")
    public ResponseEntity<Student> insert(@RequestBody Student student) throws StudentAlreadyRegisteredException {
        studentService.insert(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody Student student) throws StudentNotFoundException {
        studentService.update(id, student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable Long id) throws StudentNotFoundException {
        studentService.delete(id);
        return ResponseEntity.ok().build();
    }

}
