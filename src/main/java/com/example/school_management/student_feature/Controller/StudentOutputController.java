package com.example.school_management.student_feature.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentOutputController {

    @GetMapping("/studentPort")
    public String studentPortal()
    {
        return "/student_frontend/student_index.html";
    }
}
