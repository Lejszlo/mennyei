package com.hajdu.sp.competition.query.repository;

import com.hajdu.sp.competition.query.entity.CompetitionDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionQueryMongoRepository extends MongoRepository<CompetitionDocument, String> {

}
