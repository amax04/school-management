package com.example.school_management.student_feature.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/demo")
    public String printdemo()
    {
        return "hello";
    }
}
