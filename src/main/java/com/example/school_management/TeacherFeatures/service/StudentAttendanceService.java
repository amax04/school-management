package com.example.school_management.TeacherFeatures.service;

import com.example.school_management.TeacherFeatures.dto.AttendanceRequest;
import java.time.LocalDate;
import java.util.List;

public interface StudentAttendanceService {

    void saveAttendance(AttendanceRequest request, Long teacherId);

    boolean existsByDateGradeSectionTeacher(LocalDate date, String grade, String section, Long teacherId);

    List<AttendanceRequest> getAttendanceHistoryByTeacher(Long teacherId);
}
