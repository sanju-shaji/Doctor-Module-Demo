package com.example.demo.repository;

import com.example.demo.model.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DoctorRepository extends MongoRepository<DoctorEntity, UUID> {
}
