package com.uvo.patientservice.service;

import com.uvo.patientservice.dao.PatientRepository;
import com.uvo.patientservice.dto.PatientRequest;
import com.uvo.patientservice.dto.PatientResponse;
import com.uvo.patientservice.model.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    @Autowired
    private PatientRepository objPatientRepository;

    public void createPatient(PatientRequest patientRequest)
    {
        Patient objPatient = Patient.builder()
                .id(patientRequest.getId())
                .name(patientRequest.getName())
                .build();

        objPatientRepository.save(objPatient);
        log.info("Patient Saved");

    }

    public PatientResponse findById(Long id)
    {
        Patient  objPatient = objPatientRepository.findById(id).orElse(new Patient());
        return PatientResponse.builder()
                .id(objPatient.getId())
                .name(objPatient.getName())
                .contactNumber(objPatient.getContactNumber())
                .build();
    }

    public boolean checkPatientRegistration(Long id)
    {
        Patient  objPatient = objPatientRepository.findById(id).orElse(null);
        return objPatient == null ? false:true;
    }

}
