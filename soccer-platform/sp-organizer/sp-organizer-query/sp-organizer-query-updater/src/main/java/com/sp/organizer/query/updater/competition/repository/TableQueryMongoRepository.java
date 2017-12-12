package com.sp.organizer.query.updater.competition.repository;


import com.sp.organizer.query.updater.competition.entity.TableQuery;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TableQueryMongoRepository extends MongoRepository<TableQuery, String> {
}
