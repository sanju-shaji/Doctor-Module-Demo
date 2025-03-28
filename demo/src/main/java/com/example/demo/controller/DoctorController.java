package com.example.demo.controller;

import com.example.demo.dto.DoctorDto;
import com.example.demo.model.DoctorEntity;
import com.example.demo.service.DoctorService;
import com.example.demo.service.DoctorServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class DoctorController {
    @Autowired
    DoctorService service;


    @PostMapping("/saveDoctor")
    public  DoctorDto saveDoctor(@RequestBody DoctorDto doctorDto){
         return  service.createDoctor(doctorDto);
    }
    @GetMapping("/getDoctor")
    public List<DoctorEntity> getDoctors(){
        return service.getDoctors();
    }

    @GetMapping("/GET/doctors/{id}")
    public DoctorEntity getDoctorByID(@PathVariable UUID id){
        return service.getDoctorsById(id);
    }





}
