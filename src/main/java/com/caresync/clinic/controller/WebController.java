package com.caresync.clinic.controller;

import com.caresync.clinic.entity.Appointment;
import com.caresync.clinic.entity.Doctor;
import com.caresync.clinic.service.AppointmentService;
import com.caresync.clinic.service.DoctorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller responsible for the public-facing patient pages.
 */
@Controller
public class WebController {

    private final DoctorService doctorService;
    private final AppointmentService appointmentService;

    // Constructor Injection
    public WebController(DoctorService doctorService, AppointmentService appointmentService) {
        this.doctorService = doctorService;
        this.appointmentService = appointmentService;
    }

    /**
     * 1. Landing Page: Shows all doctors.
     */
    @GetMapping("/")
    public String showLandingPage(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "index"; // Renders index.html
    }

    /**
     * 2. Booking Page: Shows the booking form for a specific doctor.
     */
    @GetMapping("/book/{doctorId}")
    public String showBookingForm(@PathVariable Long doctorId, Model model) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        model.addAttribute("doctor", doctor);
        model.addAttribute("appointment", new Appointment()); // Empty object for the form
        return "booking"; // Renders booking.html
    }

    /**
     * 3. Process Booking: Handles the form submission.
     */
    @PostMapping("/book/{doctorId}")
    public String processBooking(@PathVariable Long doctorId, 
                                 @ModelAttribute Appointment appointment, 
                                 RedirectAttributes redirectAttributes) {
        try {
            // Attempt to book the appointment
            Appointment savedAppointment = appointmentService.bookAppointment(doctorId, appointment);
            
            // If successful, redirect to success page with the generated Reference Number
            return "redirect:/success?ref=" + savedAppointment.getReferenceNumber();
            
        } catch (IllegalStateException e) {
            // If conflict or weekend error occurs, catch the exception from the Service
            // and send the error message back to the booking page
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/book/" + doctorId;
        }
    }

    /**
     * 4. Success Page: Shows the booking details and reference number.
     */
    @GetMapping("/success")
    public String showSuccessPage(@RequestParam("ref") String referenceNumber, Model model) {
        Appointment appointment = appointmentService.getAppointmentByReference(referenceNumber);
        model.addAttribute("appointment", appointment);
        return "success"; // Renders success.html
    }
}