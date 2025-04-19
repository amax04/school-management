package com.example.school_management.TeacherFeatures.Controller;

import com.example.school_management.TeacherFeatures.dto.AttendanceRequest;
import com.example.school_management.TeacherFeatures.entity.AttendanceMeta;
import com.example.school_management.TeacherFeatures.entity.StudentAttendance;
import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.TeacherFeatures.repository.AttendanceMetaRepository;
import com.example.school_management.TeacherFeatures.repository.StudentAttendanceRepository;
import com.example.school_management.TeacherFeatures.repository.TeacherRepository;
import com.example.school_management.TeacherFeatures.service.StudentAttendanceService;
import com.example.school_management.entity.Student;
import com.example.school_management.service.StudentService;
import jakarta.servlet.http.HttpSession;
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
    @RequestMapping("/teacher/student-attendance")
    public class StudentAttendanceController {

        @Autowired
        private StudentAttendanceService attendanceService; // Use the interface here

        @Autowired
        private StudentService studentService;

        @Autowired
        private AttendanceMetaRepository attendanceMetaRepository;

        @Autowired
        private StudentAttendanceRepository studentAttendanceRepository;

        @Autowired
        private TeacherRepository teacherRepository;

        @GetMapping("/view")
        public String showAttendancePage(@ModelAttribute AttendanceRequest request, HttpSession session) {
            if (request.getTeacherId() == null) {
                request.setTeacherId((Long) session.getAttribute("teacherId"));
            }

            System.out.println("Teacher ID: " + request.getTeacherId());
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
        public boolean attendanceAlreadyExists(@RequestParam("date") LocalDate date,
                                               @RequestParam String grade,
                                               @RequestParam String section,
                                               HttpSession session) {
            Long teacherId = (Long  ) session.getAttribute("id");
            return attendanceService.existsByDateGradeSectionTeacher(date, grade, section, teacherId);
        }

        @PostMapping("/submit-attendance")
        public ResponseEntity<?> submitAttendance(@RequestBody AttendanceRequest request, HttpSession session) {
            Long teacherId = (Long) session.getAttribute("teacherId");
            String teacherName = (String) session.getAttribute("teacherName"); // ✅ Get from session

            request.setTeacherId(teacherId); // Set teacherId into request for downstream use

            // ✅ Prevent duplicate attendance entry
            if (attendanceService.existsByDateGradeSectionTeacher(
                    request.getDate(),
                    request.getGrade(),
                    request.getSection(),
                    teacherId)) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Attendance already taken");
            }

            // ✅ Create and save metadata
            AttendanceMeta attendanceMeta = new AttendanceMeta();
            attendanceMeta.setDate(request.getDate());
            attendanceMeta.setGrade(request.getGrade());
            attendanceMeta.setSection(request.getSection());
            attendanceMeta.setTeacherId(teacherId);
            attendanceMeta = attendanceMetaRepository.save(attendanceMeta);

            // ✅ Save student attendance records
            for (AttendanceRequest.AttendanceEntry entry : request.getAttendancelist()) {
                StudentAttendance studentAttendance = new StudentAttendance();
                studentAttendance.setStudentId(entry.getStudentId());
                studentAttendance.setStatus(entry.getStatus());
                studentAttendance.setDate(request.getDate());
                studentAttendance.setTeacherId(teacherId);
                studentAttendance.setGrade(request.getGrade());
                studentAttendance.setSection(request.getSection());
                studentAttendance.setAttendanceMeta(attendanceMeta);

                // ✅ Set recordedBy
                studentAttendance.setRecordedBy(teacherName);

                studentAttendanceRepository.save(studentAttendance);
            }

            return ResponseEntity.ok("Attendance submitted successfully");
        }


        @GetMapping("/check-exists")
        public ResponseEntity<Boolean> checkAttendanceExists(
                @RequestParam String grade,
                @RequestParam String section,
                @RequestParam("date") LocalDate date,
                HttpSession session) {

            Long teacherId = (Long) session.getAttribute("id");
            boolean exists = attendanceService.existsByDateGradeSectionTeacher(date, grade, section, teacherId);
            return ResponseEntity.ok(exists);
        }

        @GetMapping("/student-attendance-history")
        public String showAttendanceHistory(HttpSession session, Principal principal, Model model) {
            Long teacherId = (Long) session.getAttribute("roleId");

            // Optionally get from Principal if needed
            if (teacherId == null && principal != null) {
                String username = principal.getName();
                // Assuming you have a method to get teacher by username:
                Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
                if (teacher != null) {
                    teacherId = teacher.getId();
                    session.setAttribute("teacherId", teacherId);
                }
            }

            if (teacherId == null) {
                model.addAttribute("error", "Unable to identify teacher.");
                return "login/login"; // or your custom error page
            }

            List<AttendanceRequest> history = attendanceService.getAttendanceHistoryByTeacher(teacherId);
            model.addAttribute("history", history);
            return "teacher/student/studentAttendanceHistory";
        }
    }
