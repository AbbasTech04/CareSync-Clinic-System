package com.caresync.clinic.controller;

import com.caresync.clinic.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller responsible for the Reception/Admin dashboard.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AppointmentService appointmentService;

    public AdminController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /**
     * Dashboard: Shows all appointments in a table.
     */
    @GetMapping
    public String showDashboard(Model model) {
        // Fetch all appointments and pass them to the view
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "dashboard"; // Renders dashboard.html
    }
}