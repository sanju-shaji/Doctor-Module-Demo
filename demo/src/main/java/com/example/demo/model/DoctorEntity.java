package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "Doctor")
@Getter
@Setter
@ToString
public class DoctorEntity {
    @Id
    private UUID id ;
    private String firstName;
    private String lastName;
    private String department;
}
