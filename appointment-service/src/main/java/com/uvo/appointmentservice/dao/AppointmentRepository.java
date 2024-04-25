package com.uvo.appointmentservice.dao;

import com.uvo.appointmentservice.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorId(Long doctorId);

    List<Appointment> findByDoctorIdAndTimeSlotId(Long doctorId,Long timeSlotId);

    List<Appointment> findByPatientId(Long patientId);


}
