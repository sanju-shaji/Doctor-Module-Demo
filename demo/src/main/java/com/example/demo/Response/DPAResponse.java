package com.example.demo.Response;

import com.example.demo.dto.DoctorDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DPAResponse{
    private String id;
    private String firstName;
    private String lastName;
    private List<DoctorDto> doctors;
 }
