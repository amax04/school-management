package com.example.school_management.student_feature.Controller;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.student_feature.entity.Student;
import com.example.school_management.student_feature.repository.StudentRepository;
import com.example.school_management.student_feature.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/admin")
@Controller
public class StudentOutputController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/studentPort")
    public String studentPortal()
    {
        return "/student_frontend/student_index.html";
    }
    @GetMapping("/studentshow")
    public String studentshow(Model model)
    {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("teachers", students);
        return "student/student_index";
    }
    @GetMapping("/addoreditstudent")
    public String saveOrEditStudent()
    {
        return "/student/addOrEditStudent";
    }

}
