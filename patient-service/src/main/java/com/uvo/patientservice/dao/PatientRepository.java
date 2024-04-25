package com.uvo.patientservice.dao;

import com.uvo.patientservice.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository   extends JpaRepository<Patient, Long> {

}
