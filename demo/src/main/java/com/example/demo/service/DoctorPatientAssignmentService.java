package com.example.demo.service;

import com.example.demo.Response.DPAResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Repository
public class DoctorPatientAssignmentService {
    @Autowired
    MongoTemplate mongoTemplate;

    public ResponseEntity<DPAResponse> doctorPatientAssignmentService(String id) {
        UUID patientId = UUID.fromString(id);
        MatchOperation matchPatient = Aggregation.match(Criteria.where("_id").is(patientId));
        LookupOperation lookupAssignments = LookupOperation.newLookup().from("tp_doctor_patient_assignment")
                .localField("_id").foreignField("patient_id").as("assignments");
        UnwindOperation unwindAssignments = Aggregation.unwind("assignments", true);
        LookupOperation lookUpDoctors = LookupOperation.newLookup().from("tp_doctors")
                .localField("assignments.doctor_id").foreignField("_id")
                .as("assignments.doctor");
        UnwindOperation unwindDoctor = Aggregation.unwind("assignments.doctor", true);
        GroupOperation group = Aggregation.group("_id", "firstName", "lastName").
                push("assignments.doctor").as("doctors");
        ProjectionOperation project = Aggregation.project().and("$_id._id").as("id")
                .and("_id.firstName").as("firstName")
                .and("_id.lastName").as("lastName")
                .and("_id.doctors").as("doctors");

        Aggregation aggregation = Aggregation.newAggregation(matchPatient,
                lookupAssignments, unwindAssignments, lookUpDoctors, unwindDoctor, group, project);

        AggregationResults<DPAResponse> result = mongoTemplate.aggregate(aggregation, "tp_patients", DPAResponse.class);
        System.out.println(result.getRawResults());
        return ResponseEntity.ok().body(result.getUniqueMappedResult());
    }

}
