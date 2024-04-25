package com.uvo.appointmentservice.controller;


import com.uvo.appointmentservice.dto.AppointmentRequest;
import com.uvo.appointmentservice.dto.AppointmentResponse;
import com.uvo.appointmentservice.dto.DoctorResponse;
import com.uvo.appointmentservice.model.Appointment;
import com.uvo.appointmentservice.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Appointment")
public class AppointmentController {


        @Autowired
        private AppointmentService objAppointmentService;

    @PostMapping
    public String setAppointment(@RequestBody AppointmentRequest appointment) {
        // Add logic to check if patient, doctor, and time slot exist
        // You can use Feign or RestTemplate to communicate with other services
        // For simplicity, let's assume patientId, doctorId, and timeSlotId are valid
            //objAppointmentService.save(appointment);
            return objAppointmentService.save(appointment);

    }

    @GetMapping("/getAppointment{id}")
    public AppointmentResponse getAppointment(@PathVariable Long id) {
        return objAppointmentService.findById(id);

    }

    @GetMapping("/getAppointmentsByDoctor/{id}")
    public List<Appointment> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return objAppointmentService.getAppointmentsByDoctor(doctorId);

    }

    @GetMapping("/getAppointmentsByPatient/{id}")
    public List<Appointment> getAppointmentsByPatient(@PathVariable Long patientId) {
        return objAppointmentService.getAppointmentsByPatient(patientId);

    }

    @DeleteMapping("/{id}")
    public void cancelAppointment(@PathVariable Long id) {
        objAppointmentService.deleteAppointment(id);

    }

    @GetMapping("/getAppointmentsBySpecialization")
    public List<DoctorResponse> getAppointmentsBySpecialization(@RequestParam List<String> specializations) {
        return objAppointmentService.getAppointmentsBySpecialization(specializations);

    }


}
