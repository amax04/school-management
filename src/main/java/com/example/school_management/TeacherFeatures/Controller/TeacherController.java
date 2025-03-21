package com.example.school_management.TeacherFeatures.Controller;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.TeacherFeatures.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // ✅ 1. Show all teachers (for listing)
    @GetMapping("")
    public String getAllTeachers(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "teacher/teacher-index";  // This maps to index.jsp
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
    public String saveTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult result) {
        if (result.hasErrors()) {
            return "teacher/teacher-form";  // Return to form if errors exist
        }
        teacherService.saveTeacher(teacher);
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

}
