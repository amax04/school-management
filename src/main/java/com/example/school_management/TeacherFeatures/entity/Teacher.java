package com.example.school_management.TeacherFeatures.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@EqualsAndHashCode
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

    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^(https?|ftp)://.*$", message = "Invalid URL format for photo link")
    @NotBlank(message = "Photo link should not be empty")
    private String photoUrl;

    @NotNull(message = "Date of Birth is required")
    @PastOrPresent(message = "Date of Birth cannot be in the future")
    @Column(name = "dob") 
    private LocalDate dob;

    @Pattern(regexp = "\\d{12}", message = "Aadhaar must be 12 digits")
    private String aadhaarNo;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phone;

    private String altPhoneNo;

    private String gender;

    private String address;

    @NotNull(message = "Date of Joining is required")
    @PastOrPresent(message = "Date of Joining cannot be in the future")
    @Column(name = "doj") 
    private LocalDate doj;

    private Boolean isActive = true; // Default to true for new teachers
}
