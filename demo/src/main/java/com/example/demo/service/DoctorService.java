package com.example.demo.service;

import com.example.demo.dto.DoctorDto;
import com.example.demo.model.DoctorEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface DoctorService {

     DoctorDto createDoctor(DoctorDto doctorDto);
     List<DoctorEntity> getDoctors();
     DoctorEntity getDoctorsById(UUID id);
}
