package com.example.demo.dao;

import com.example.demo.model.DoctorEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface DoctorRepo extends MongoRepository<DoctorEntity, UUID> {
}
