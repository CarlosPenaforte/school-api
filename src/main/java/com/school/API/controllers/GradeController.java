package com.school.API.controllers;

import com.school.API.models.Grade;
import com.school.API.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/grades")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @GetMapping
    public ResponseEntity<List<Grade>> findAll() {
        return ResponseEntity.ok(gradeService.findAll());
    }

    @GetMapping("/{subject}")
    public ResponseEntity<List<Grade>> findAllBySubject(@PathVariable String subject) {
        return ResponseEntity.ok(gradeService.findAllBySubject(subject));
    }
}
