package com.example.school_management.serviceImpl;

import com.example.school_management.TeacherFeatures.entity.Teacher;
import com.example.school_management.TeacherFeatures.repository.TeacherRepository;
import com.example.school_management.entity.Admin;
import com.example.school_management.entity.AppUser;
import com.example.school_management.entity.Student;
import com.example.school_management.entity.UserCreationRequestDTO;
import com.example.school_management.repository.AdminRepository;
import com.example.school_management.repository.AppUserRepository;
import com.example.school_management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UserRegistrationService {

    @Autowired
    private StudentRepository studentRepo;

    @Autowired
    private TeacherRepository teacherRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private AppUserRepository logUserRepo;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;



    public void createUser(UserCreationRequestDTO dto) {
        String generatedPassword = generateRandomPassword(8);
        String encodedPassword = passwordEncoder.encode(generatedPassword);

        String generatedUsername = dto.getEmail();

        Long userProfileId = null;

        if (dto.getRole().equalsIgnoreCase("STUDENT")) {
            Student student = new Student();
            student.setName(dto.getName());
            student.setEmail(dto.getEmail());
            student.setGrade(dto.getGrade());
            student.setSection(dto.getSection());
            studentRepo.save(student);
            userProfileId = student.getId();
        } else if (dto.getRole().equalsIgnoreCase("TEACHER")) {
            Teacher teacher = new Teacher();
            teacher.setName(dto.getName());
            teacher.setEmail(dto.getEmail());
            teacherRepo.save(teacher);
            userProfileId = teacher.getId();
        } else {
            Admin admin = new Admin();
            admin.setFullName(dto.getName());
            admin.setEmail(dto.getEmail());
            adminRepo.save(admin);
            userProfileId = admin.getId();
        }

        AppUser user = new AppUser();
        user.setUsername(generatedUsername);
        user.setPassword(encodedPassword);
//        user.setRole("ROLE_" + dto.getRole().toUpperCase());
        user.setRole( dto.getRole().toUpperCase());
        user.setRoleId(userProfileId);
        logUserRepo.save(user);

        sendEmail(dto.getEmail(), generatedUsername, generatedPassword);
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    private void sendEmail(String toEmail, String username, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("School Management System - Your Login Details");
        message.setText("Dear user,\n\nYour login details are:\nUsername: " + username + "\nPassword: " + password +
                "\n\nLogin here: https://yourdomain.com/login\n\nRegards,\nAdmin Team");
        mailSender.send(message);
    }
}
