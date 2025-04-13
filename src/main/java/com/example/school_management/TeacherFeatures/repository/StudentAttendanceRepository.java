package com.example.school_management.TeacherFeatures.repository;

import com.example.school_management.TeacherFeatures.entity.AttendanceMeta;
import com.example.school_management.TeacherFeatures.entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
    List<StudentAttendance> findByStudentId(Long studentId);
    List<StudentAttendance> findByDate(LocalDate date);
    List<StudentAttendance> findByAttendanceMeta(AttendanceMeta attendanceMeta);
}
