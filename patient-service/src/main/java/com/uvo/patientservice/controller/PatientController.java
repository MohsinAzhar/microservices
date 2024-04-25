package com.uvo.patientservice.controller;


import com.uvo.patientservice.dto.PatientRequest;
import com.uvo.patientservice.dto.PatientResponse;
import com.uvo.patientservice.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {


        @Autowired
        private PatientService patientService;

        @PostMapping
        public String registerPatient(@RequestBody PatientRequest objPatientRequest)
        {
            patientService.createPatient(objPatientRequest);
        return "Patient saved..";
        }

    @GetMapping("/{id}")
    public PatientResponse getPatient(@PathVariable Long id) {
            return patientService.findById(id);

    }

    @GetMapping("checkPatientRegistration/{id}")
    public boolean checkPatientRegistration(@PathVariable Long id) {
        return patientService.checkPatientRegistration(id);

    }



}
