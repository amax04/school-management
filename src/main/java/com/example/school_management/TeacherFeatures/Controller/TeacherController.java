package com.example.school_management.TeacherFeatures.Controller;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.TeacherFeatures.repository.TeacherRepository;
import com.example.school_management.TeacherFeatures.service.TeacherService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // ✅ 1. Show all teachers (for listing)
    @GetMapping("")
    public String getAllTeachers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Teacher> teachers;

        if (keyword != null && !keyword.trim().isEmpty()) {
            teachers = teacherService.searchTeachers(keyword);
        } else {
            teachers = teacherService.getAllTeachers();
        }

        model.addAttribute("teachers", teachers);
        model.addAttribute("keyword", keyword);  // Preserve search input in UI
        return "teacher/teacher-index";
    }

    @GetMapping("/api")   // for backend
    @ResponseBody
    public ResponseEntity<List<Teacher>> getAllTeachersApi() {
        List<Teacher> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);  // Returns JSON response
    }

    // ✅ 2. Show Add/Update Form
    @GetMapping("/form")
    public String showTeacherForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Teacher teacher = teacherService.getTeacherById(id);
            model.addAttribute("teacher", teacher);
        } else {
            model.addAttribute("teacher", new Teacher());
        }
        return "teacher/teacher-form"; // This maps to teacher-form.jsp
    }

    // ✅ 3. Save (Add or Update) Teacher

    @PostMapping("/save")
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher teacher,
                              BindingResult result,
                              @RequestParam("file") MultipartFile file,
                              RedirectAttributes redirectAttributes, Model model) {

        // ✅ Step 1: Check for validation errors and return to form
        if (result.hasErrors()) {
            model.addAttribute("teacher", teacher); // Retain form data
            return "teacher/teacher-form"; // Redirects back to form with errors
        }

        try {
            if (!file.isEmpty()) {
                // ✅ Generate a unique file name using UUID
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

                // ✅ Define the storage path
                Path uploadDir = Paths.get("src/main/webapp/images");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }

                // ✅ Save the file
                Path filePath = uploadDir.resolve(fileName);
                Files.copy(file.getInputStream(), filePath);

                // ✅ Save the file path in the database
                teacher.setPhotoUrl("/images/" + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ✅ Step 2: Save teacher
        teacherService.saveTeacher(teacher);
        redirectAttributes.addFlashAttribute("successMessage", "Teacher saved successfully!");

        return "redirect:/teachers";
    }

    // ✅ 4. Delete a Teacher
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editTeacher(@PathVariable Long id, Model model) {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher == null) {
            return "redirect:/teachers?error=notfound";
        }
        model.addAttribute("teacher", teacher);
        return "teacher/teacher-form"; // This should match your JSP file name
    }

    @GetMapping("/dashboard")
    public String teacherDashboard(HttpSession session, Principal principal) {
        System.out.println("Dashboard method-------------");
        if (principal == null) {
            return "redirect:/login";
        }
        System.out.println("Principal pass method-------------");

        Long id= (Long) session.getAttribute("roleId");
        String username = principal.getName(); // e.g., teacher's username
        Teacher teacher = teacherRepository.findById(id).orElse(null);

        if (teacher != null) {
            //session.setAttribute("roleId", teacher.getId());
            session.setAttribute("teacherId", teacher.getId());
            session.setAttribute("teacherName", teacher.getName());
            session.setAttribute("teacherPhoto", teacher.getPhotoUrl());
        }
        System.out.println("this is the teacher Id:"+id);
        return "teacher/teacherDashboard";
    }


//    @GetMapping("/dashboard")
//    public String showDashboard() {
//        return "teacher/teacherDashboard"; // This maps to /WEB-INF/views/teacher/teacherDashboard.jsp
//    }

}
