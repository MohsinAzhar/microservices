package com.uvo.doctorsservice.dao;

import com.uvo.doctorsservice.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findBySpecializationIn(List<String> specializations);
}
