package com.example.school_management.TeacherFeatures.service;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.TeacherFeatures.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        return teacherRepository.findById(id).map(existingTeacher -> {
            existingTeacher.setName(teacherDetails.getName());
            existingTeacher.setQualification(teacherDetails.getQualification());
            existingTeacher.setSpecialization(teacherDetails.getSpecialization());
            existingTeacher.setExperience(teacherDetails.getExperience());
            existingTeacher.setEmail(teacherDetails.getEmail());
            existingTeacher.setPhone(teacherDetails.getPhone());
            existingTeacher.setAddress(teacherDetails.getAddress());
            existingTeacher.setDoj(teacherDetails.getDoj());
            existingTeacher.setIsActive(teacherDetails.getIsActive());
            return teacherRepository.save(existingTeacher);
        }).orElse(null);
    }

    @Override
    public void deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
        }
    }
}
