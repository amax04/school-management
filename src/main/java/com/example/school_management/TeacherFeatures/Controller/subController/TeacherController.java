package com.example.school_management.TeacherFeatures.Controller.subController;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.TeacherFeatures.repository.TeacherRepository;
import com.example.school_management.TeacherFeatures.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers") // Version 1 API
public class TeacherController {

    private final TeacherRepository teacherRepository;

    private TeacherService teacherService;

    public TeacherController(TeacherRepository teacherRepository, TeacherService teacherService) {
        this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    // Create a new teacher
    @PostMapping
    public ResponseEntity<Teacher> addTeachers(@Valid @RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    // Get all teachers
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        //Hello
        List<Teacher> teachers = teacherRepository.findAll();
        return ResponseEntity.ok(teachers);
    }

    // Get a teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return teacherRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update a teacher
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @Valid @RequestBody Teacher teacherDetails) {
        // Find the teacher by ID or throw exception
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));

        // Update the teacher's details
        existingTeacher.setName(teacherDetails.getName());
        existingTeacher.setQualification(teacherDetails.getQualification());
        existingTeacher.setSpecialization(teacherDetails.getSpecialization());
        existingTeacher.setExperience(teacherDetails.getExperience());
        existingTeacher.setEmail(teacherDetails.getEmail());
        existingTeacher.setPhotoUrl(teacherDetails.getPhotoUrl());
        existingTeacher.setDob(teacherDetails.getDob());
        existingTeacher.setAadhaarNo(teacherDetails.getAadhaarNo());
        existingTeacher.setPhone(teacherDetails.getPhone());
        existingTeacher.setAltPhoneNo(teacherDetails.getAltPhoneNo());
        existingTeacher.setGender(teacherDetails.getGender());
        existingTeacher.setAddress(teacherDetails.getAddress());
        existingTeacher.setDoj(teacherDetails.getDoj());
        existingTeacher.setIsActive(teacherDetails.getIsActive());

        // Save the updated teacher and return
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return ResponseEntity.ok(updatedTeacher);
    }


    // Delete a teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return ResponseEntity.ok("Teacher removed with id " + id);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher not found with id " + id);
    }
}
