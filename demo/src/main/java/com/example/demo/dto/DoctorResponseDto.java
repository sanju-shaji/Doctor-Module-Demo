package com.example.demo.dto;

import com.example.demo.model.DoctorEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class DoctorResponseDto extends ResponseDto {


   private UUID id;
   private String firstName;
   private String lastName;
   private String department;

   public DoctorResponseDto(DoctorEntity doctorEntity){

      setId(doctorEntity.getId());
      setFirstName(doctorEntity.getFirstName());
      setLastName(doctorEntity.getLastName());
      setDepartment(doctorEntity.getDepartment());

   }
}
