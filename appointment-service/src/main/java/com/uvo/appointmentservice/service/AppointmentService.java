package com.uvo.appointmentservice.service;

import com.uvo.appointmentservice.dao.AppointmentRepository;
import com.uvo.appointmentservice.dto.AppointmentRequest;
import com.uvo.appointmentservice.dto.AppointmentResponse;
import com.uvo.appointmentservice.dto.DoctorResponse;
import com.uvo.appointmentservice.model.Appointment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    @Autowired
    private AppointmentRepository objAppointmentRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public String save(AppointmentRequest objAppointmentRequest)
    {
        if(!getAvaillableTimeSlotsByDoctor(objAppointmentRequest.getPatientId()))
        {
            return "Patient Not Registered";
        }

        if(objAppointmentRepository.findByDoctorIdAndTimeSlotId(objAppointmentRequest.getDoctorId(),objAppointmentRequest.getTimeSlotId()).size()>0)
        {
            return "Appointment not Available";
        }else
        {
            Appointment objAppointment = Appointment.builder()
                    .id(objAppointmentRequest.getId())
                    .doctorId(objAppointmentRequest.getDoctorId())
                    .patientId(objAppointmentRequest.getPatientId())
                    .timeSlotId(objAppointmentRequest.getTimeSlotId())
                    .build();

            objAppointmentRepository.save(objAppointment);
            return "Appointment Saved";
        }

    }

     public AppointmentResponse findById(Long id)
    {
        Appointment  objAppointment = objAppointmentRepository.findById(id).orElse(new Appointment());
        return AppointmentResponse.builder()
                .id(objAppointment.getId())
                .patientId(objAppointment.getPatientId())
                .doctorId(objAppointment.getDoctorId())
                .timeSlotId(objAppointment.getTimeSlotId())
                .build();
    }

    private boolean getAvaillableTimeSlotsByDoctor(Long patientId)
    {
        Boolean result = Boolean.FALSE;
       try {
           result = webClientBuilder.build().get().
                   uri("http://Patient-Service/patients/checkPatientRegistration/2")
                   .retrieve()
                   .bodyToMono(Boolean.class)
                   .block();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
        return result.booleanValue();
    }
    public List<Appointment> getAppointmentsByDoctor(Long doctorId)
    {
        return objAppointmentRepository.findByDoctorId(doctorId);

    }

    public List<Appointment> getAppointmentsByPatient(Long patientId)
    {
        return objAppointmentRepository.findByPatientId(patientId);

    }

    public List<DoctorResponse> getAppointmentsBySpecialization(List<String> specializations)
    {
        DoctorResponse[] response = null;
        try {
            response = webClientBuilder.build().get().
                    uri("http://Doctor-Service/doctors/getDoctorsBySpecialization",uriBuilder -> uriBuilder.queryParam("ids",specializations).build())
                    .retrieve()
                    .bodyToMono(DoctorResponse[].class)
                    .block();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return Arrays.asList(response);

    }

    public void deleteAppointment(Long id)
    {
        objAppointmentRepository.deleteById(id);
    }



}
