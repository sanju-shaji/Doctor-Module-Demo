package com.example.demo.service;

import com.example.demo.Response.DPAResponse;
import com.example.demo.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class DPAService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public DPAResponse getPatientWithDoctors(UUID patientId) {
        Aggregation aggregation = newAggregation(
                match(org.springframework.data.mongodb.core.query.Criteria.where("_id").is(patientId)),
                lookup("tp_doctor_patient_assignment", "_id", "patient_id", "assignments"),
                unwind("assignments", true),
                lookup("tp_doctors", "assignments.doctor_id", "_id", "assignments.doctor"),
                unwind("assignments.doctor", true),
                group("_id").first("firstName").as("firstName")
                        .first("lastName").as("lastName")
                        .push("assignments.doctor").as("doctors")

        );
        AggregationResults<DPAResponse> results = mongoTemplate.aggregate(aggregation, "tp_patients", DPAResponse.class);
        System.out.println(results.getRawResults());
        return results.getUniqueMappedResult();
    }

}
