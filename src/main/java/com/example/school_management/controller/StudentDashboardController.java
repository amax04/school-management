package com.example.school_management.controller;

import com.example.school_management.entity.Student;
import com.example.school_management.entity.Subject;
import com.example.school_management.repository.StudentRepository;
import com.example.school_management.service.StudentService;
import com.example.school_management.service.SubjectService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@RequestMapping("/student")
@Controller
public class StudentDashboardController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectService subjectService;

//    @Autowired
//    private SubjectService subjectService;
//
//    @Autowired
//    private AttendanceService attendanceService;
//
//    @Autowired
//    private GradeService gradeService;


    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model, Principal principal) {
        String username = principal.getName();
        Long id=(Long)session.getAttribute("roleId");
        //Student student = studentRepository.findByUserUsername(username).orElse(null);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            session.setAttribute("studentId", student.getId());
            model.addAttribute("student", student);
        }
        return "student/student_dashboard";
    }

    @GetMapping("/profile")
    public String profile(HttpSession session,Model model, Principal principal) {
        String username = principal.getName();
        Long id=(Long)session.getAttribute("roleId");
        //Student student = studentRepository.findByUserUsername(username).orElse(null);
        Student student = studentRepository.findById(id).orElse(null);
        model.addAttribute("student", student);
        return "student/profile";
    }
    // Load student dashboard with student details
//    @GetMapping("/student/dashboard")
//    public String showStudentDashboard(@SessionAttribute("studentId") Long studentId, Model model) {
//        Optional<Student> student = Optional.ofNullable(studentService.getStudentById(studentId));
//        if (student.isPresent()) {
//            model.addAttribute("student", student.get());
//            return "student/student_dashboard"; // This refers to student_dashboard.jsp
//        }
//        return "redirect:/login"; // Redirect if student not found
//    }
//    @GetMapping("/student/dashboard")
//    public String showStudentDashboard(Model model) {
//        // Get the authenticated student username (assume email is the username)
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String studentEmail = authentication.getName(); // Get logged-in student's email
//
//        // Fetch student details
//        Student student = studentService.getStudentByEmail(studentEmail);
//        if (student == null) {
//            return "redirect:/login"; // If student not found, redirect to login
//        }
//
//        // Fetch subjects
//        List<Subject> subjects = subjectService.getSubjectsByStudentId(student.getId());
//
//        // Fetch attendance
//        Attendance attendance = attendanceService.getAttendanceByStudentId(student.getId());
//
//        // Fetch grades
//        List<Grade> grades = gradeService.getGradesByStudentId(student.getId());
//
//        // Add data to the model
//        model.addAttribute("student", student);
//        model.addAttribute("subjects", subjects);
//        model.addAttribute("attendance", attendance);
//        model.addAttribute("grades", grades);
//
//        return "student_dashboard"; // Return JSP page
//    }

    @GetMapping("/subjects")
    public String studentSubjects(Model model, HttpSession session) {
//        Long studentId = (Long) session.getAttribute("roleId");
//        Student student = studentRepository.findById(studentId).orElse(null);
//
//        List<Subject> subjects = subjectService.getSubjectsForStudent(student);
//        model.addAttribute("subjects", subjects);
//        return "student/subject_list";
        Long studentId = (Long) session.getAttribute("roleId");

        if (studentId == null) {
            return "redirect:/login"; // or handle session expiration
        }

        Student student = studentRepository.findById(studentId).orElse(null);

        if (student == null) {
            return "redirect:/login";
        }

        List<Subject> subjects = subjectService.getSubjectsForStudent(student);

        model.addAttribute("subjects", subjects);
        model.addAttribute("student", student); // ✅ Add this line

        return "student/subject_list";
    }

}
