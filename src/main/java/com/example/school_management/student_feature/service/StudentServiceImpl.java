package com.example.school_management.student_feature.service;

import com.example.school_management.student_feature.entity.Student;
import com.example.school_management.student_feature.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existingStudent = getStudentById(id);
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setGrade(student.getGrade());
        existingStudent.setAddress(student.getAddress());
        existingStudent.setPhoneNo(student.getPhoneNo());
        existingStudent.setRollNo(student.getRollNo());
        existingStudent.setFatherName(student.getFatherName());
        existingStudent.setMotherName(student.getMotherName());
        existingStudent.setAadhaarNo(student.getAadhaarNo());
        existingStudent.setAltPhoneNo(student.getAltPhoneNo());
        existingStudent.setGender(student.getGender());
        existingStudent.setSection(student.getSection());
        existingStudent.setSessionId(student.getSessionId());
        return studentRepository.save(existingStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
