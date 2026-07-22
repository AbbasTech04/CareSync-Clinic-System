package com.caresync.clinic.entity;

import jakarta.persistence.*;

/**
 * Represents a Doctor in the CareSync system.
 */
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String specialty;

    // Stores the path to the image (e.g., "/assets/img/doctors/doc1.jpg")
    @Column(name = "image_url", length = 255)
    private String imageUrl;

    // Default Constructor for JPA
    public Doctor() {
    }

    // Parameterized Constructor
    public Doctor(String name, String specialty, String imageUrl) {
        this.name = name;
        this.specialty = specialty;
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

// Add this under the specialty field
    @Column(columnDefinition = "TEXT")
    private String bio;

    // Update the constructor to include bio
    public Doctor(String name, String specialty, String bio, String imageUrl) {
        this.name = name;
        this.specialty = specialty;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

    // Add Getter and Setter for bio
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
}