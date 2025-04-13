package com.example.school_management.TeacherFeatures.repository;

import com.example.school_management.TeacherFeatures.entity.AttendanceMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceMetaRepository extends JpaRepository<AttendanceMeta, Long> {
    boolean existsByDateAndGradeAndSectionAndTeacherId(LocalDate date, String grade, String section, Long teacherId);
    List<AttendanceMeta> findByTeacherId(Long teacherId);
}
