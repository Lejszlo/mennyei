package com.sp.organizer.query.updater.competition.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sp.organizer.query.updater.competition.entity.CompetitionQuery;

@Repository
public interface CompetitionQueryMongoRepository extends MongoRepository<CompetitionQuery, String> {

}
