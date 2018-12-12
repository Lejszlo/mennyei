package com.sp.competition.query.updater.repository;

import com.sp.competition.query.updater.entity.CompetitionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionQueryMongoRepository extends MongoRepository<CompetitionDocument, String> {

}
