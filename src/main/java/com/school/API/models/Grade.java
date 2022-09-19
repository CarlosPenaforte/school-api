package com.school.API.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double gradeValue;

    private String studentName;

    @Column(nullable = false)
    private String examDate;

    @Column(nullable = false)
    private String subject;

    public Grade() {
    }

    public Grade(Double gradeValue, String examDate, String subject) {
        this.gradeValue = gradeValue;
        this.examDate = examDate;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getExamDate() {
        return examDate;
    }

    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Double gradeValue) {
        this.gradeValue = gradeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return gradeValue.equals(grade.gradeValue) && studentName.equals(grade.studentName) && examDate.equals(grade.examDate) && subject.equals(grade.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gradeValue, studentName, examDate, subject);
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", gradeValue=" + gradeValue +
                ", studentName='" + studentName + '\'' +
                ", examDate='" + examDate + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
