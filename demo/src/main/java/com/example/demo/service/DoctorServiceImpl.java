package com.example.demo.service;

import com.example.demo.dto.DoctorDto;
import com.example.demo.model.DoctorEntity;
import com.example.demo.dao.DoctorRepo;
import com.example.demo.validation.DoctorValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepo docRepo;


    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        DoctorEntity doctorEntity=new DoctorEntity(doctorDto);
        DoctorEntity saveDoctor;
        DoctorValidation doctorValidation=new DoctorValidation();
       List<String> validateInput= doctorValidation.validatePostDoctor(doctorEntity);
       if(!validateInput.isEmpty()){
           DoctorDto errorResponseDto=new DoctorDto();
           errorResponseDto.setSuccess(false);
           errorResponseDto.setError(validateInput);
            return errorResponseDto;
       }
       saveDoctor=docRepo.save(doctorEntity);
        DoctorDto responseDto=new DoctorDto(saveDoctor);
        responseDto.setSuccess(true);
        responseDto.setError(null);
        return responseDto;
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
    public DoctorEntity getDoctorsById(UUID id) {
        Optional<DoctorEntity> response =docRepo.findById(id);
        return response.get();
    }
}
