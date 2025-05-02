package com.example.demo.controller;

import com.example.demo.Response.DPAResponse;
import com.example.demo.dto.DoctorResponseDto;
import com.example.demo.model.DoctorEntity;
//import com.example.demo.repository.DPAImpl;
import com.example.demo.service.DPAService;
import com.example.demo.service.DoctorPatientAssignmentService;
import com.example.demo.service.DoctorService;
import com.example.demo.validation.DoctorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class DoctorController {
    @Autowired
    DoctorService service;
    @Autowired
    DPAService DPAservice;
    @Autowired
    DoctorValidation doctorValidation;


    @PostMapping("/saveDoctor")
    public ResponseEntity<DoctorResponseDto> saveDoctor(@RequestBody DoctorResponseDto doctorResponseDto) {
        return service.createDoctor(doctorResponseDto);
    }

    @GetMapping("/getDoctor")
    public List<DoctorEntity> getDoctors() {
        return service.getDoctors();
    }

    @GetMapping("/GET/doctors/{id}")
    public ResponseEntity<DoctorResponseDto> getDoctorByID(@PathVariable String id) {
        List<String> errorMessage = doctorValidation.isValidUUID(id);
        if (!errorMessage.isEmpty()) {
            DoctorResponseDto responseDto = new DoctorResponseDto();
            responseDto.setSuccess(false);
            responseDto.setError(errorMessage);
            return ResponseEntity.status(400).body(responseDto);
        }
        UUID uuid = UUID.fromString(id);
        return service.getDoctorsById(uuid);
    }

    @GetMapping("/AssignedDoctorsByPatientId")
    public ResponseEntity<DPAResponse> getdoctorlist(@RequestParam String patientId){
        DPAResponse responses = DPAservice.getPatientWithDoctors(UUID.fromString(patientId) );
        return ResponseEntity.ok().body(responses);
    }

}
