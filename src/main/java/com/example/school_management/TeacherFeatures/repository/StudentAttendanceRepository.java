package com.example.school_management.TeacherFeatures.repository;

import com.example.school_management.TeacherFeatures.entity.AttendanceMeta;
import com.example.school_management.TeacherFeatures.entity.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {
    List<StudentAttendance> findByStudentId(Long studentId);
    List<StudentAttendance> findByDate(LocalDate date);
    @Query("SELECT sa FROM StudentAttendance sa JOIN FETCH sa.student WHERE sa.attendanceMeta = :meta")
    List<StudentAttendance> findByAttendanceMetaWithStudent(@Param("meta") AttendanceMeta attendanceMeta);


    boolean existsByAttendanceMetaDateAndAttendanceMetaGradeAndAttendanceMetaSection(LocalDate date, String grade, String section);


    List<StudentAttendance> findByAttendanceMeta(AttendanceMeta attendanceMeta);

}
