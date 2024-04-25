package com.uvo.doctorsservice.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {

    private Long id;

    private String name;
    private String specialization;


}
