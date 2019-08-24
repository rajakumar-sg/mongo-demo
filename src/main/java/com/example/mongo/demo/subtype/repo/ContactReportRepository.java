package com.example.mongo.demo.subtype.repo;

import com.example.mongo.demo.subtype.model.ContactReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactReportRepository extends MongoRepository<ContactReport, String> {
}
