package com.example.school_management.atRuntime;

import com.example.school_management.entity.AppUser;
import com.example.school_management.entity.Subject;
import com.example.school_management.entity.SubjectAssignment;
import com.example.school_management.repository.AppUserRepository;
import com.example.school_management.repository.SubjectAssignmentRepository;
import com.example.school_management.repository.SubjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
public class DataInitializer {
//
//    @Bean
//    CommandLineRunner subjectSeeder(SubjectRepository subjectRepo, SubjectAssignmentRepository assignmentRepo) {
//        return args -> {
//            // Create subjects
//            Subject math = subjectRepo.save(new Subject("Mathematics"));
//            Subject evs = subjectRepo.save(new Subject("EVS"));
//            Subject hindi = subjectRepo.save(new Subject("Hindi"));
//            Subject english = subjectRepo.save(new Subject("English"));
//            Subject science = subjectRepo.save(new Subject("Science"));
//            Subject sst = subjectRepo.save(new Subject("Social Studies"));
//            Subject physics = subjectRepo.save(new Subject("Physics"));
//            Subject chemistry = subjectRepo.save(new Subject("Chemistry"));
//            Subject biology = subjectRepo.save(new Subject("Biology"));
//            Subject comp = subjectRepo.save(new Subject("Computer Science"));
//            Subject economics = subjectRepo.save(new Subject("Economics"));
//            Subject business = subjectRepo.save(new Subject("Business Studies"));
//            Subject accounts = subjectRepo.save(new Subject("Accountancy"));
//            Subject physical = subjectRepo.save(new Subject("Physical Education"));
//
//            // Sections
//            List<String> sections = List.of("A", "B");
//
//            // Class-wise assignment
//            for (String section : sections) {
//                // Classes 1–5
//                for (int i = 1; i <= 5; i++) {
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, hindi));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, math));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, evs));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, english));
//                }
//
//                // Classes 6–8
//                for (int i = 6; i <= 8; i++) {
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, math));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, science));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, sst));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, hindi));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, english));
//                }
//
//                // Classes 9–10
//                for (int i = 9; i <= 10; i++) {
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, math));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, science));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, sst));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, hindi));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, english));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, comp));
//                }
//
//                // Class 11–12 (Science + Commerce stream — optional split in future)
//                for (int i = 11; i <= 12; i++) {
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, physics));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, chemistry));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, biology));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, comp));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, math));
//
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, economics));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, business));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, accounts));
//
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, english));
//                    assignmentRepo.save(new SubjectAssignment("" + i, section, physical));
//                }
//            }
//        };
//    }

    @Bean
    public CommandLineRunner loadData(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (appUserRepository.count() == 0) {
                AppUser admin = new AppUser();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("pass123"));
                admin.setRole("ADMIN");
                admin.setRoleId(1L); // Mocked roleId (can be 1, 101, etc.)

                AppUser teacher = new AppUser();
                teacher.setUsername("teacher");
                teacher.setPassword(passwordEncoder.encode("pass123"));
                teacher.setRole("TEACHER");
                teacher.setRoleId(1L);

                AppUser student = new AppUser();
                student.setUsername("saxenaam12@gmail.com");
                student.setPassword(passwordEncoder.encode("pass123"));
                student.setRole("STUDENT");
                student.setRoleId(74L);

                appUserRepository.save(admin);
                appUserRepository.save(teacher);
                appUserRepository.save(student);

                System.out.println("Default users inserted.");
            }
        };
    }
}
