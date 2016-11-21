package com.mennyei.publicweb.competition.infrastructure;

import com.mennyei.publicweb.competition.dto.CompetitionQuery;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionMongoRepository extends MongoRepository<CompetitionQuery, String> {

}
