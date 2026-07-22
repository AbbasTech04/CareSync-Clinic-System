package com.caresync.clinic.service;

import com.caresync.clinic.entity.Appointment;
import com.caresync.clinic.entity.Doctor;
import com.caresync.clinic.repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

/**
 * Service class handling Appointment booking and business rules.
 */
@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorService doctorService;

    // Constructor Injection
    public AppointmentService(AppointmentRepository appointmentRepository, DoctorService doctorService) {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
    }

    /**
     * Core method to process and book an appointment.
     * Includes all business logic validations.
     */
    @Transactional
    public Appointment bookAppointment(Long doctorId, Appointment appointment) {
        
        // 1. Validate Weekend Rule (Saturday & Sunday are off)
        DayOfWeek day = appointment.getAppointmentDate().getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            throw new IllegalStateException("Booking is not available on weekends (Saturday and Sunday).");
        }

        // 2. Fetch and assign the selected doctor
        Doctor doctor = doctorService.getDoctorById(doctorId);
        appointment.setDoctor(doctor);

        // 3. Conflict Prevention: Check if the time slot is already booked
        boolean isConflict = appointmentRepository.existsByDoctorAndAppointmentDateAndAppointmentTime(
                doctor, 
                appointment.getAppointmentDate(), 
                appointment.getAppointmentTime()
        );
        
        if (isConflict) {
            throw new IllegalStateException("This time slot is already booked. Please choose another time.");
        }

        // 4. Generate a unique Reference Number (e.g., REF-A1B2C3D4)
        // Using UUID and taking the first 8 characters for simplicity
        String uniqueRef = "REF-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        appointment.setReferenceNumber(uniqueRef);

        // 5. Save to the database
        return appointmentRepository.save(appointment);
    }

    /**
     * Retrieves an appointment by its reference number for the success page or PDF export.
     */
    public Appointment getAppointmentByReference(String referenceNumber) {
        return appointmentRepository.findByReferenceNumber(referenceNumber)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Reference Number"));
    }

    /**
     * Retrieves all appointments for the Reception/Admin dashboard.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAllByOrderByAppointmentDateDescAppointmentTimeDesc();
    }
}