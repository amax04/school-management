package com.example.school_management.TeacherFeatures.service;

import com.example.school_management.TeacherFeatures.entity.Teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Teacher addTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    Optional<Teacher> getTeacherById(Long id);

    Teacher updateTeacher(Long id, Teacher teacherDetails);

    void deleteTeacher(Long id);
}
