package com.example.demo.model;

import com.example.demo.dto.DoctorDto;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Doctor")
@Getter
@Setter
@ToString
public class DoctorEntity {
    @Id
    private UUID id ;
    private String firstName;
    private String lastName;
    private String department;


  public DoctorEntity(DoctorDto doctorDto){
        setId(UUID.randomUUID());
        setFirstName(doctorDto.getFirstName().trim());
        setLastName(doctorDto.getLastName().trim());
        setDepartment(doctorDto.getDepartment());


    }


}
