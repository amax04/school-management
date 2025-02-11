package com.example.school_management.TeacherFeatures.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherOutputController {

    @GetMapping("/teacherPort")
    public String showTeachersPage() {
        return "teacher_frontend/teacher_index.html";
    }
}
