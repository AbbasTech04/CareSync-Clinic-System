package com.caresync.clinic.repository;

import com.caresync.clinic.entity.Appointment;
import com.caresync.clinic.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Appointment entity.
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    /**
     * Checks if a specific time slot is already booked for a specific doctor.
     * This is crucial for conflict prevention during the booking process.
     */
    boolean existsByDoctorAndAppointmentDateAndAppointmentTime(Doctor doctor, LocalDate appointmentDate, LocalTime appointmentTime);

    /**
     * Finds an appointment using its unique reference number.
     * Useful for displaying booking details and exporting the PDF.
     */
    Optional<Appointment> findByReferenceNumber(String referenceNumber);

    /**
     * Retrieves all appointments for a specific date, ordered by time.
     * This will be used in the Reception/Admin page to view daily schedules.
     */
    List<Appointment> findByAppointmentDateOrderByAppointmentTimeAsc(LocalDate appointmentDate);
    
    /**
     * Retrieves all appointments ordered by date and time (most recent first).
     * Useful for the main Reception dashboard.
     */
    List<Appointment> findAllByOrderByAppointmentDateDescAppointmentTimeDesc();
}