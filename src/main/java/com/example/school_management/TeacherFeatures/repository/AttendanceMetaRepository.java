package com.example.school_management.TeacherFeatures.repository;

import com.example.school_management.TeacherFeatures.entity.AttendanceMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceMetaRepository extends JpaRepository<AttendanceMeta, Long> {
    boolean existsByDateAndGradeAndSectionAndTeacherId(LocalDate date, String grade, String section, Long teacherId);
    List<AttendanceMeta> findByTeacherId(Long teacherId);

    @Query("SELECT a FROM AttendanceMeta a WHERE a.teacherId = :teacherId " +
            "AND (:grade IS NULL OR a.grade = :grade) " +
            "AND (:section IS NULL OR a.section = :section) " +
            "AND (:date IS NULL OR a.date = :date) " +
            "ORDER BY a.date DESC")
    List<AttendanceMeta> findByFilters(@Param("teacherId") Long teacherId,
                                       @Param("grade") String grade,
                                       @Param("section") String section,
                                       @Param("date") LocalDate date);

    @Query(value = "SELECT COUNT(DISTINCT am.grade || '-' || am.section) FROM attendance_meta am WHERE am.teacher_id = :teacherId", nativeQuery = true)
    int countDistinctClassesByTeacherId(@Param("teacherId") Long teacherId);

}
