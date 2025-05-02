package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

/**
 * PatientModel class represents patient entity in Doctor patient management system
 */
@Document(collection = "tp_patients")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientModel {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
}
