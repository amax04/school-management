package com.example.school_management.TeacherFeatures.Controller;

import com.example.school_management.TeacherFeatures.dto.AttendanceRequest;
import com.example.school_management.TeacherFeatures.service.StudentAttendanceService;
import com.example.school_management.student_feature.entity.Student;
import com.example.school_management.student_feature.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

    @Controller
    @RequestMapping("/teacher/student-attendance")
    public class StudentAttendanceController {

        @Autowired
        private StudentAttendanceService attendanceService;

        @Autowired
        private StudentService studentService;

        @GetMapping("/view")
        public String showAttendancePage() {
            return "teacher/student-Attendance/studentMarkAttendance"; // This will load studentMarkAttendance.jsp
        }

        // Get all students for the selected grade and section
        @GetMapping("/students-attendance")
        public ResponseEntity<List<Student>> getStudentsByGradeAndSection(@RequestParam String grade, @RequestParam String section) {
            List<Student> students = studentService.getStudentsByGradeAndSection(grade, section);
            return ResponseEntity.ok(students);
        }

        // Get attendance records for a specific date
        @GetMapping("/get-attendance")
        public ResponseEntity<?> getAttendance(@RequestParam String grade, @RequestParam String section, @RequestParam String date) {
            LocalDate attendanceDate = LocalDate.parse(date);
            List<Student> students = studentService.getStudentsByGradeAndSection(grade, section);
            return ResponseEntity.ok(students);
        }

        // Fetch all grades dynamically
        @GetMapping("/grades")
        public ResponseEntity<List<String>> getAllGrades() {
            List<String> grades = studentService.getAllGrades();
            return ResponseEntity.ok(grades);
        }

        // Fetch all sections dynamically
        @GetMapping("/sections")
        public ResponseEntity<List<String>> getAllSections() {
            List<String> sections = studentService.getAllSections();
            return ResponseEntity.ok(sections);
        }

        @GetMapping("/attendance-exists")
        @ResponseBody
        public boolean attendanceAlreadyExists(@RequestParam LocalDate  date,
                                               @RequestParam String grade,
                                               @RequestParam String section,
                                               HttpSession session) {
            Long teacherId = (Long  ) session.getAttribute("id");
            return attendanceService.existsByDateGradeSectionTeacher(date, grade, section, teacherId);
        }

        @PostMapping("/submit-attendance")
        public ResponseEntity<?> submitAttendance(@RequestBody AttendanceRequest request, HttpSession session) {
            Long teacherId = (Long) session.getAttribute("id");
            request.setTeacherId(teacherId); // set it into request for downstream use

            if (attendanceService.existsByDateGradeSectionTeacher(
                    request.getDate(),
                    request.getGrade(),
                    request.getSection(),
                    request.getTeacherId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Attendance already taken");
            }

            attendanceService.saveAttendance(request, teacherId);
            return ResponseEntity.ok("Attendance submitted successfully");
        }

        @GetMapping("/check-exists")
        public ResponseEntity<Boolean> checkAttendanceExists(
                @RequestParam String grade,
                @RequestParam String section,
                @RequestParam LocalDate date,
                HttpSession session) {

            Long teacherId = (Long) session.getAttribute("id");
            boolean exists = attendanceService.existsByDateGradeSectionTeacher(date, grade, section, teacherId);
            return ResponseEntity.ok(exists);
        }

        @GetMapping("/attendance-history")
        public String getAttendanceHistory(HttpSession session, Model model) {
            Long teacherId = (Long) session.getAttribute("id");
            List<AttendanceRequest> history = attendanceService.getAttendanceHistoryByTeacher(teacherId);
            model.addAttribute("history", history);
            System.out.println("Teacher ID: " + teacherId);
            System.out.println("Attendance history: " + history);
            return "teacher/student-Attendance/studentAttendanceHistory";
        }

    }
