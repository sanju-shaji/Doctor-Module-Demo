//package com.example.demo.repository;
//
//import com.example.demo.Response.DPAResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.aggregation.*;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Repository
//public class DPAImpl {
//    @Autowired
//    DPARepository dpaRepository;
//
//    public List doctorPatientAssignmentService(String id){
//        DPAResponse  results = PatientRepository.doctorPatientAssignmentService(UUID.fromString(id) );
//        return new ArrayList<>();
//    }
//}
