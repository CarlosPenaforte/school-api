package com.school.API.models;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double gradeValue;

    @Autowired
    @OneToOne
    private Student student;

    public Grade() {
    }

    public Grade(Long id, Double gradeValue, Student student) {
        this.gradeValue = gradeValue;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrade() {
        return gradeValue;
    }

    public void setGrade(Double gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grade grade = (Grade) o;
        return id.equals(grade.id) && gradeValue.equals(grade.gradeValue) && student.equals(grade.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gradeValue, student);
    }

    @Override
    public String toString() {
        return "Grades{" +
                "id=" + id +
                ", gradeValue=" + gradeValue +
                ", student=" + student +
                '}';
    }
}
