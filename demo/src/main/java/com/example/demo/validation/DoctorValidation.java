package com.example.demo.validation;

import com.example.demo.model.DoctorEntity;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class DoctorValidation {

    public boolean isEmptyString(String string){
        return string.isEmpty();
    }

    public boolean containsOnlyAlbhabets(String string){
        String alphabetPattern="^[a-zA-Z\\s]+$";
        if(!string.matches(alphabetPattern)){
            return false;
        }
        return true;
    }

    public boolean isUUIDValid(UUID id){
        return id.toString().length()==36;
    }
    public List<String> isValidUUID(String id){
        List<String> errorMessage=new ArrayList<>();
         try {
            if(StringUtils.isBlank(id)){
                errorMessage.add("You are not providing an ID. Please provide a UUID");
                return errorMessage;
            }
            UUID.fromString(id);
            return errorMessage;
        }catch (IllegalArgumentException illegalArgumentException){
            errorMessage.add("Invalid Id. Please provide a valid UUID");
            return errorMessage;
        }
    }

////   public List <String> validateUUID(String id){
////        List<String>errorMessage=new ArrayList<>();
////        if(id.isEmpty()){
////            errorMessage.add("Please provide UUID for retrieving data");
////        }
////        errorMessage.add("Invalid Id. Please provide a valid UUID");
////        return errorMessage;
//   }

    public boolean isValidDepartmentName(String string){
        String departmentNamePattern="^[a-zA-Z\\s\\-'.,]+$";
        if(!string.matches(departmentNamePattern)){
            return false;
        }
        return true;
    }

    public List<String> validatePostDoctor(DoctorEntity doctor){

        List<String> errorMessage=new ArrayList<>();
       if(isEmptyString(doctor.getFirstName())){
           errorMessage.add("First name should not be an empty string");
        }
       if(!containsOnlyAlbhabets(doctor.getFirstName())){
           errorMessage.add("First name should only contain alphabets");
       }
        if(isEmptyString(doctor.getLastName())){
            errorMessage.add("Last name should not be an empty string");
        }
        if(!containsOnlyAlbhabets(doctor.getLastName())){
            errorMessage.add("Last name should only contain alphabets");
        }
        if(isEmptyString(doctor.getDepartment())){
            errorMessage.add("Department should not be empty");
        }
        if(!isValidDepartmentName(doctor.getDepartment())){
            errorMessage.add("Invalid Department Name");
        }


        return errorMessage;
    }
}
