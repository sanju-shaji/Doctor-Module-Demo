package com.example.demo.validation;

import com.example.demo.model.DoctorEntity;

import java.util.ArrayList;
import java.util.List;

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
