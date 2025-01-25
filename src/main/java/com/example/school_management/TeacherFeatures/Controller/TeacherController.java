package com.example.school_management.TeacherFeatures.Controller;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.TeacherFeatures.repository.TeacherRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/teachers") // Version 1 API
public class TeacherController {

    private final TeacherRepository teacherRepository;

    public TeacherController(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Create a new teacher
    @PostMapping
    public ResponseEntity<Teacher> addTeachers(@RequestBody Teacher teacher) {
        Teacher savedTeacher = teacherRepository.save(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTeacher);
    }

    // Get all teachers
    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
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
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody Teacher teacherDetails) {
        // Find the teacher by ID
        Teacher existingTeacher = teacherRepository.findById(id).orElse(null);

        // Check if the teacher exists
        if (existingTeacher != null) {
            // Update the teacher's details
            existingTeacher.setName(teacherDetails.getName());
            existingTeacher.setQualification(teacherDetails.getQualification());
            existingTeacher.setSpecialization(teacherDetails.getSpecialization());
            existingTeacher.setExperience(teacherDetails.getExperience());
            existingTeacher.setEmail(teacherDetails.getEmail());
            existingTeacher.setPhone(teacherDetails.getPhotoUrl());
            existingTeacher.setDoj(teacherDetails.getDob());
            existingTeacher.setPhone(teacherDetails.getPhone());
            existingTeacher.setAddress(teacherDetails.getAddress());
            existingTeacher.setDoj(teacherDetails.getDoj());
            existingTeacher.setIsActive(teacherDetails.getIsActive());

            // Save the updated teacher back to the database
            Teacher updatedTeacher = teacherRepository.save(existingTeacher);

            // Return the updated teacher in the response
            return ResponseEntity.ok(updatedTeacher);
        }

        // If the teacher doesn't exist, return a "Not Found" response
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
