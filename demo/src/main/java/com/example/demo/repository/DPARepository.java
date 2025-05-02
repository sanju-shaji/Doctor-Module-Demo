package com.example.demo.repository;

import com.example.demo.Response.DPAResponse;
import com.example.demo.model.DPAEntity;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface DPARepository extends MongoRepository<DPAResponse, UUID>{

}
