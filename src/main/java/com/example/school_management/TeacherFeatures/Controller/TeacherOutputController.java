package com.example.school_management.TeacherFeatures.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherOutputController {

    @GetMapping("/teacherPort")
    public String studentPortal()
    {
        return "teacher_frontent/teacher.html";
    }
}
