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
    private Double value;

    @Autowired
    @ManyToOne
    private Student student;

    public Grade() {
    }

    public Grade(Long id, Double value, Student student) {
        this.value = value;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getGrade() {
        return value;
    }

    public void setGrade(Double value) {
        this.value = value;
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
        return id.equals(grade.id) && value.equals(grade.value) && student.equals(grade.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, student);
    }

    @Override
    public String toString() {
        return "Grades{" +
                "id=" + id +
                ", value=" + value +
                ", student=" + student +
                '}';
    }
}
