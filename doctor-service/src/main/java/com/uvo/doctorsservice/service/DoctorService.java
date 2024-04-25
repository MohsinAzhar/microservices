package com.uvo.doctorsservice.service;

import com.uvo.doctorsservice.dao.DoctorRepository;
import com.uvo.doctorsservice.dto.DoctorRequest;
import com.uvo.doctorsservice.dto.DoctorResponse;
import com.uvo.doctorsservice.model.Doctor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    @Autowired
    private DoctorRepository objDoctorRepository;

    public void registerDoctor(DoctorRequest doctorRequest)
    {
        Doctor objDoctor= Doctor.builder()
                .id(doctorRequest.getId())
                .name(doctorRequest.getName())
                .specialization(doctorRequest.getSpecialization())
                .build();

        objDoctorRepository.save(objDoctor);
        log.info("Doctor Saved");

    }


    public DoctorResponse getDoctor(Long id)
    {
        Doctor objDoctor = objDoctorRepository.findById(id).orElse(new Doctor());
        return DoctorResponse.builder()
            .id(objDoctor.getId())
            .name(objDoctor.getName())
            .specialization(objDoctor.getSpecialization())
            .build();
    }

    public List<DoctorResponse> getDoctorsBySpecialization(List<String> ids)
    {
        return objDoctorRepository.findBySpecializationIn(ids).stream()
                .map(doc -> DoctorResponse.builder()
                        .id(doc.getId())
                        .name(doc.getName())
                        .specialization(doc.getSpecialization())
                        .build()).collect(Collectors.toList());
    }


}
