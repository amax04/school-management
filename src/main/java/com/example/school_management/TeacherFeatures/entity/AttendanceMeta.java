package com.example.school_management.TeacherFeatures.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "attendance_meta")
public class AttendanceMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String grade;
    private String section;
    private Long teacherId;

    public AttendanceMeta() {}

    public AttendanceMeta(LocalDate date, String grade, String section, Long teacherId) {
        this.date = date;
        this.grade = grade;
        this.section = section;
        this.teacherId = teacherId;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getSection() { return section; }
    public void setSection(String section) { this.section = section; }

    public Long getTeacherId() { return teacherId; }
    public void setTeacherId(Long teacherId) { this.teacherId = teacherId; }
}
