package com.uvo.doctorsservice.controller;



import com.uvo.doctorsservice.dto.DoctorRequest;
import com.uvo.doctorsservice.dto.DoctorResponse;
import com.uvo.doctorsservice.model.Doctor;
import com.uvo.doctorsservice.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {


        @Autowired
        private DoctorService doctorService;

        @PostMapping
        public String registerDoctor(@RequestBody DoctorRequest objDoctorRequest)
        {
            doctorService.registerDoctor(objDoctorRequest);
        //repository.save(objPatient);
        return "Doctor Registered";
        }

        @GetMapping("/{id}")
        public DoctorResponse getDoctor(@PathVariable long id) {
            return doctorService.getDoctor(id);
    }

    @GetMapping("/getDoctorsBySpecialization")
    public List<DoctorResponse> getDoctorsBySpecialization(@RequestParam List<String> ids) {
        return doctorService.getDoctorsBySpecialization(ids);
    }


}
