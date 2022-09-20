package com.school.API.services;

import com.school.API.exceptions.StudentAlreadyRegisteredException;
import com.school.API.exceptions.StudentNotFoundException;
import com.school.API.models.Grade;
import com.school.API.models.Student;
import com.school.API.repositories.GradeRepository;
import com.school.API.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private GradeRepository gradeRepository;

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long id) throws StudentNotFoundException {
        Optional<Student> optStudent = studentRepository.findById(id);
        if (optStudent.isEmpty()) {
            throw new StudentNotFoundException(id);
        }
        return optStudent.get();
    }

    public void insert(Student student) throws StudentAlreadyRegisteredException {
        String name = student.getName();
        Optional<Student> newUser = studentRepository.findByName(name);

        if (newUser.isEmpty()) {
            for (Grade grade : student.getGrades()){
                grade.setStudentName(student.getName());
                gradeRepository.save(grade);
            }
            studentRepository.save(student);
        } else {
            throw new StudentAlreadyRegisteredException(name);
        }
    }

    public void update(Long id, Student student) throws StudentNotFoundException {
        Optional<Student> userDb = studentRepository.findById(id);
        if (userDb.isPresent()) {
            student.setId(id);
            List<Grade> gradesDb = userDb.get().getGrades();

            for (Grade grade : student.getGrades()){
                grade.setStudentName(student.getName());
                if (!gradesDb.contains(grade)) {
                    gradeRepository.save(grade);
                } else {
                    long gradeId = gradesDb.stream()
                                    .filter(gradeDb -> {
                                        return gradeDb.equals(grade);
                                    }).findFirst().get().getId();
                    grade.setId(gradeId);
                }
            }

            studentRepository.save(student);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public void delete(Long id) throws StudentNotFoundException {
        Optional<Student> userDb = studentRepository.findById(id);
        if (userDb.isPresent()) {
            List<Grade> grades = userDb.get().getGrades();
            studentRepository.deleteById(id);

            for (Grade grade : grades){
                gradeRepository.delete(grade);
            }
        } else {
            throw new StudentNotFoundException(id);
        }
    }
}
