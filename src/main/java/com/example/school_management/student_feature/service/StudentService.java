package com.example.school_management.student_feature.service;


import com.example.school_management.student_feature.entity.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Long id);
    Student updateStudent(Long id, Student student);
    void deleteStudent(Long id);
    public String savePhoto(MultipartFile photo);
    public String generatePlaceholder(String name);
    public String generateRandomColor();
}
