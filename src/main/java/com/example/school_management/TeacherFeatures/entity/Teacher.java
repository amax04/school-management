package com.example.school_management.TeacherFeatures.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    private String qualification;
    private String specialization;

    @Min(value = 0, message = "Experience cannot be negative")
    private Integer experience;

    @Email(message = "Email should be valid")   // Validates email format
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Photo link should not be empty")
    private String photoUrl;

    @NotNull(message = "Date of Birth is required")
    @PastOrPresent(message = "Date of Birth cannot be in the future")   // ensure that the date is not in the future.
    private LocalDate dob;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phone;
    private String address;

    @NotNull(message = "Date of Joining is required")
    @PastOrPresent(message = "Date of Joining cannot be in the future")   // ensure that the date is not in the future.
    private LocalDate doj;
    private Boolean isActive; // Indicates if the teacher is active
}
