package com.example.demo.repository;

import com.example.demo.Response.DPAResponse;
import com.example.demo.model.PatientModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Repository layer interface for patient entity
 */
@Repository
public interface PatientRepository extends MongoRepository<PatientModel, UUID> {
    List<PatientModel> findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(String firstName, String lastName);

    List<PatientModel> findByFirstNameStartingWithIgnoreCaseAndLastNameStartingWithIgnoreCase(String firstName, String lastName);
    @org.springframework.data.mongodb.repository.Aggregation(pipeline = {"""
            {
                $match: {
                  _id: ?0}
              },
              {
                $lookup: {
                  from: "tp_doctor_patient_assignment",
                  localField: "_id",
                  foreignField: "patient_id",
                  as: "assignments"      }
              },
                {
                $unwind: {
                  path: "$assignments",
                  preserveNullAndEmptyArrays: true
                }
              },
               {
                $lookup: {
                  from: "tp_doctors",
                  localField: "assignments.doctor_id",
                  foreignField: "_id",
                  as: "assignments.doctor"
                }
              },
                        
              {
                $unwind: {
                  path: "$assignments.doctor",
                  preserveNullAndEmptyArrays: true
                }
              },
              {
                $group: {
                  _id: {
                    _id: "$_id",
                    firstName: "$firstName",
                    lastName: "$lastName"
                  },
                  doctors: {
                    $push: "$assignments.doctor"
                  }
                }
              },
              {
                $project: {
                  _id: "$_id._id",
                  firstName: "$_id.firstName",
                  lastName: "$_id.lastName",
                  doctors: 1
                }
              }"""})
   List<DPAResponse>  doctorPatientAssignmentService(UUID id);
}
