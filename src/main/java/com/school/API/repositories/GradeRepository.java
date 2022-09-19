package com.school.API.repositories;

import com.school.API.models.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findAllByStudentName(String studentName);

    List<Grade> findAllBySubject(String subject);
}
