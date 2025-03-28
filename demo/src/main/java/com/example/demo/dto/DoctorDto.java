package com.example.demo.dto;

import com.example.demo.model.DoctorEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;

import java.util.List;
import java.util.UUID;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class DoctorDto extends BaseDto {


   private UUID id;
   private String firstName;
   private String lastName;
   private String department;

   public DoctorDto(DoctorEntity doctorEntity){

      setId(doctorEntity.getId());
      setFirstName(doctorEntity.getFirstName());
      setLastName(doctorEntity.getLastName());
      setDepartment(doctorEntity.getDepartment());

   }
}
