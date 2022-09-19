package com.school.API.services;

import com.school.API.exceptions.StudentNotFoundException;
import com.school.API.models.Grade;
import com.school.API.models.Student;
import com.school.API.repositories.GradeRepository;
import com.school.API.exceptions.GradeNotFoundException;
import com.school.API.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    public Grade findById(Long id) throws GradeNotFoundException {
        Optional<Grade> optGrade = gradeRepository.findById(id);
        if(optGrade.isPresent()){
            return optGrade.get();
        } else {
            throw new GradeNotFoundException(id);
        }
    }

    public List<Grade> findAllBySubject(String subject) {
        return gradeRepository.findAllBySubject(subject);
    }
}
