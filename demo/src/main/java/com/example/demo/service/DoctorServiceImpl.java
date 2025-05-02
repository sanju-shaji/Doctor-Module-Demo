package com.example.demo.service;

import com.example.demo.dto.DoctorResponseDto;
import com.example.demo.model.DoctorEntity;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.validation.DoctorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository docRepo;


    @Override
    public ResponseEntity<DoctorResponseDto> createDoctor(DoctorResponseDto doctorResponseDto) {
        DoctorEntity doctorEntity=new DoctorEntity();
       doctorEntity.setId(UUID.randomUUID());
       doctorEntity.setFirstName(doctorResponseDto.getFirstName().trim());
       doctorEntity.setLastName(doctorResponseDto.getLastName().trim());
       doctorEntity.setDepartment(doctorResponseDto.getDepartment());
        DoctorEntity saveDoctor;
        DoctorValidation doctorValidation=new DoctorValidation();
       List<String> validateInput= doctorValidation.validatePostDoctor(doctorEntity);
       if(!validateInput.isEmpty()){
           DoctorResponseDto errorResponseDto=new DoctorResponseDto();
           errorResponseDto.setSuccess(false);
           errorResponseDto.setError(validateInput);
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(errorResponseDto) ;
       }
       saveDoctor=docRepo.save(doctorEntity);
        DoctorResponseDto responseDto=new DoctorResponseDto(saveDoctor);
        responseDto.setSuccess(true);
        responseDto.setError(null);
        return ResponseEntity.ok(responseDto) ;
    }

    @Override
    public List<DoctorEntity> getDoctors() {
        List<DoctorEntity> docs =docRepo.findAll();
        if (!docs.isEmpty()) {
            for(DoctorEntity doctorEntity:docs){
//                doctorEntity.setSuccess("true");
            }
        }
        return docs;
    }

    @Override
    public ResponseEntity<DoctorResponseDto> getDoctorsById(UUID id) {
        DoctorResponseDto responseDto=new DoctorResponseDto();
        Optional<DoctorEntity> doctorEntity =docRepo.findById(id);
        if(doctorEntity.isPresent()){
        responseDto.setSuccess(true);
        responseDto.setId(doctorEntity.get().getId());
        responseDto.setFirstName(doctorEntity.get().getFirstName());
        responseDto.setLastName(doctorEntity.get().getLastName());
        responseDto.setDepartment(doctorEntity.get().getDepartment());
        responseDto.setError(null);
        return ResponseEntity.ok().body(responseDto);
        }
        List<String> invalidUUID=new ArrayList<>();
        invalidUUID.add("User Not Found In Database");
        responseDto.setSuccess(false);
        responseDto.setError(invalidUUID);
        return ResponseEntity.status(400).body(responseDto);
    }
}
