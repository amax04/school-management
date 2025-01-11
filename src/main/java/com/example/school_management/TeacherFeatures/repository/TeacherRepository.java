package com.example.school_management.TeacherFeatures.repository;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
