package com.caresync.clinic.repository;

import com.caresync.clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Doctor entity.
 * By extending JpaRepository, Spring Boot automatically provides 
 * basic CRUD operations (findAll, findById, save, delete, etc.).
 */
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // No custom methods needed for now. 
    // The built-in findAll() will be used to display doctors on the landing page.
}