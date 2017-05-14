package com.sp.organizer.backend.competition.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sp.organizer.backend.competition.dto.table.TableQuery;

public interface TableMongoRepository extends MongoRepository<TableQuery, String> {
}
