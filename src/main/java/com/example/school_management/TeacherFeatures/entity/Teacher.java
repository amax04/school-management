package com.example.school_management.TeacherFeatures.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    private String qualification;
    private String specialization;
    private Integer experience;

    @Email // Validates email format
    private String email;

    @Size(max = 15) // Limits phone number to 15 characters
    private String phone;
    private String address;
    private LocalDate doj; // Date of Joining
    private Boolean isActive; // Indicates if the teacher is active
}
