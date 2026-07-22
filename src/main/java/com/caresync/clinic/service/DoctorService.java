package com.caresync.clinic.service;

import com.caresync.clinic.entity.Doctor;
import com.caresync.clinic.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class handling Doctor related business logic.
 */
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // Constructor Injection (Best Practice in Spring Boot)
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Retrieves all doctors to display them on the landing page.
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    /**
     * Finds a doctor by ID, throws an exception if not found.
     */
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found with id: " + id));
    }
}