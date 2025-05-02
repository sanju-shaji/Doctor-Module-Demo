package com.example.demo.service;

import com.example.demo.dto.DoctorResponseDto;
import com.example.demo.model.DoctorEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface DoctorService {

     ResponseEntity<DoctorResponseDto> createDoctor(DoctorResponseDto doctorResponseDto);
     List<DoctorEntity> getDoctors();
     ResponseEntity<DoctorResponseDto> getDoctorsById(UUID id);
}
