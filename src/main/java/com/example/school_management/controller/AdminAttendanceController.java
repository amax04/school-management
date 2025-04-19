package com.example.school_management.controller;

import com.example.school_management.TeacherFeatures.dto.AttendanceRequest;
import com.example.school_management.TeacherFeatures.entity.AttendanceMeta;
import com.example.school_management.TeacherFeatures.entity.StudentAttendance;
import com.example.school_management.TeacherFeatures.repository.AttendanceMetaRepository;
import com.example.school_management.TeacherFeatures.repository.StudentAttendanceRepository;
import com.example.school_management.TeacherFeatures.service.StudentAttendanceService;
import com.example.school_management.entity.Student;
import com.example.school_management.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/student-attendance")
public class AdminAttendanceController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private AttendanceMetaRepository attendanceMetaRepository;

    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    @Autowired
    private StudentAttendanceService studentAttendanceService;

    // Show attendance page
    @GetMapping("/view")
    public String showAttendancePage(@ModelAttribute AttendanceRequest request) {
        return "admin/student-Attendance/adminMarkAttendance"; // Your JSP page
    }

    // Fetch students for grade + section
    @GetMapping("/students-attendance")
    public ResponseEntity<List<Student>> getStudentsByGradeAndSection(@RequestParam String grade, @RequestParam String section) {
        List<Student> students = studentService.getStudentsByGradeAndSection(grade, section);
        return ResponseEntity.ok(students);
    }

    // Get grades
    @GetMapping("/grades")
    public ResponseEntity<List<String>> getAllGrades() {
        List<String> grades = studentService.getAllGrades();
        return ResponseEntity.ok(grades);
    }

    // Get sections
    @GetMapping("/sections")
    public ResponseEntity<List<String>> getAllSections() {
        List<String> sections = studentService.getAllSections();
        return ResponseEntity.ok(sections);
    }

    // Check if attendance already exists for grade, section, date
    @GetMapping("/attendance-exists")
    @ResponseBody
    public boolean attendanceAlreadyExists(@RequestParam("date") LocalDate date,
                                           @RequestParam String grade,
                                           @RequestParam String section) {
        return studentAttendanceService.existsByDateGradeSection(date, grade, section);
    }

    // Submit attendance
    @PostMapping("/submit-attendance")
    public ResponseEntity<?> submitAttendance(@RequestBody AttendanceRequest request, Principal principal) {
        String adminName = principal.getName(); // Admin's username

        // Prevent duplicate
        if (studentAttendanceService.existsByDateGradeSection(
                request.getDate(),
                request.getGrade(),
                request.getSection())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Attendance already taken");
        }

        // Save AttendanceMeta
        AttendanceMeta meta = new AttendanceMeta();
        meta.setDate(request.getDate());
        meta.setGrade(request.getGrade());
        meta.setSection(request.getSection());
        attendanceMetaRepository.save(meta);

        // Save StudentAttendance entries
        for (AttendanceRequest.AttendanceEntry entry : request.getAttendancelist()) {
            StudentAttendance attendance = new StudentAttendance();
            attendance.setStudentId(entry.getStudentId());
            attendance.setStatus(entry.getStatus());
            attendance.setDate(request.getDate());
            attendance.setGrade(request.getGrade());
            attendance.setSection(request.getSection());
            attendance.setRecordedBy(adminName);
            attendance.setAttendanceMeta(meta);

            studentAttendanceRepository.save(attendance);
        }

        return ResponseEntity.ok("Attendance submitted successfully");
    }

    // Attendance History (All Records)
    @GetMapping("/attendance-history")
    public String showAttendanceHistory(Model model) {
        List<AttendanceRequest> history = studentAttendanceService.getAllAttendanceHistory();
        model.addAttribute("history", history);
        return "admin/student-Attendance/adminAttendanceHistory"; // JSP to show full attendance records
    }
}
