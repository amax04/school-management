package com.example.school_management.TeacherFeatures.service.implement;

import com.example.school_management.TeacherFeatures.dto.AttendanceRequest;
import com.example.school_management.TeacherFeatures.entity.AttendanceMeta;
import com.example.school_management.TeacherFeatures.entity.StudentAttendance;
import com.example.school_management.TeacherFeatures.repository.StudentAttendanceRepository;
import com.example.school_management.TeacherFeatures.repository.AttendanceMetaRepository;
import com.example.school_management.TeacherFeatures.repository.TeacherRepository;
import com.example.school_management.TeacherFeatures.service.StudentAttendanceService;
import com.example.school_management.TeacherFeatures.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentAttendanceServiceImpl implements StudentAttendanceService {

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    @Autowired
    private AttendanceMetaRepository attendanceMetaRepository;

    @Override
    public void saveAttendance(AttendanceRequest request, Long teacherId, String recordedBy) {
        // ✅ First, save attendance metadata
        AttendanceMeta meta = new AttendanceMeta(
                request.getDate(),
                request.getGrade(),
                request.getSection(),
                teacherId
        );
        attendanceMetaRepository.save(meta); // Save and get the persisted entity

        List<AttendanceRequest.AttendanceEntry> entries = request.getAttendancelist();

        // ✅ Avoid NullPointerException
        if (entries != null && !entries.isEmpty()) {
            for (AttendanceRequest.AttendanceEntry entry : entries) {
                StudentAttendance studentAttendance = new StudentAttendance();
                studentAttendance.setStudentId(entry.getStudentId());
                studentAttendance.setStatus(entry.getStatus());
                studentAttendance.setDate(request.getDate());
                studentAttendance.setGrade(request.getGrade());
                studentAttendance.setSection(request.getSection());
                studentAttendance.setTeacherId(teacherId);
                studentAttendance.setRecordedBy(recordedBy);
                studentAttendance.setAttendanceMeta(meta); // ✅ Now meta is available
                studentAttendanceRepository.save(studentAttendance);
            }
        } else {
            System.out.println("No student attendance entries found. Skipping studentAttendance save.");
        }
    }

    @Override
    public boolean existsByDateGradeSectionTeacher(LocalDate date, String grade, String section, Long teacherId) {
        return attendanceMetaRepository.existsByDateAndGradeAndSectionAndTeacherId(date, grade, section, teacherId);
    }

    @Override
    public List<AttendanceRequest> getAttendanceHistoryByTeacher(Long teacherId) {
        List<AttendanceMeta> metaList = attendanceMetaRepository.findByTeacherId(teacherId);

        return metaList.stream().map(meta -> {
            AttendanceRequest request = new AttendanceRequest();
            request.setDate(meta.getDate());
            request.setGrade(meta.getGrade());
            request.setSection(meta.getSection());
            request.setTeacherId(meta.getTeacherId());

            List<StudentAttendance> studentAttendanceList = studentAttendanceRepository.findByAttendanceMetaWithStudent(meta);

            // ✅ Convert to AttendanceEntry DTOs
            List<AttendanceRequest.AttendanceEntry> entryList = studentAttendanceList.stream().map(att -> {
                AttendanceRequest.AttendanceEntry entry = new AttendanceRequest.AttendanceEntry();
                entry.setStudentId(att.getStudentId()); // ✅ Use this
                entry.setStatus(att.getStatus());
                return entry;
            }).toList();

            request.setAttendancelist(entryList); // ✅ Fixed method name

            return request;
        }).toList();
    }

    @Override
    public List<AttendanceMeta> getFilteredHistory(Long teacherId, String grade, String section, LocalDate date) {
        List<AttendanceMeta> allRecords = attendanceMetaRepository.findByTeacherId(teacherId);

        return allRecords.stream()
                .filter(meta -> grade == null || meta.getGrade().equalsIgnoreCase(grade))
                .filter(meta -> section == null || meta.getSection().equalsIgnoreCase(section))
                .filter(meta -> date == null || meta.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @Override
    public double getTodaysAttendancePercentage(Long teacherId) {
        LocalDate today = LocalDate.now();
        int presentCount = studentAttendanceRepository.countByTeacherIdAndDateAndStatus(teacherId, today, "Present");
        int totalMarked = studentAttendanceRepository.countByTeacherIdAndDate(teacherId, today);

        if (totalMarked == 0) return 0.0;
        return (presentCount * 100.0) / totalMarked;
    }

    @Override
    public int getTotalStudentsAssignedToTeacher(Long teacherId) {
        return studentAttendanceRepository.countDistinctStudentsByTeacherId(teacherId);
    }

    @Override
    public int getTotalClassesAssignedToTeacher(Long teacherId) {
        return attendanceMetaRepository.countDistinctClassesByTeacherId(teacherId);
    }

    @Override
    public double getOverallAttendanceRate(Long teacherId) {
        int totalPresent = studentAttendanceRepository.countByTeacherIdAndStatus(teacherId, "Present");
        int totalMarked = studentAttendanceRepository.countByTeacherId(teacherId);

        if (totalMarked == 0) return 0.0;
        return (totalPresent * 100.0) / totalMarked;
    }

}
