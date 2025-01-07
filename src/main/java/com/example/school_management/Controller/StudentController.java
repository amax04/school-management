package com.example.school_management.Controller;

import com.example.school_management.entity.Student;
import com.example.school_management.repository.StudentRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/students") // Version 1 API
public class StudentController {
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student studentDetails) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(studentDetails.getName());
            student.setEmail(studentDetails.getEmail());
            student.setGrade(studentDetails.getGrade());
            student.setAddress(studentDetails.getAddress());
            return studentRepository.save(student);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return "Student removed with id " + id;
    }
}
