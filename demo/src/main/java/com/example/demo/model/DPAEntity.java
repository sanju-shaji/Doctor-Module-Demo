package com.example.demo.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

@Document(collection = "tp_doctor_patient_assignment")
@Data
public class DPAEntity {
private UUID id;
private UUID doctor_id;
private UUID patient_id;
private Date dateOfAdmission;
}
