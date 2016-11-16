package com.mennyei.publicweb.competition.infrastructure;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mennyei.publicweb.competition.dto.CompetitionClubListQuery;

@Repository
public interface CompetitionMongoRepository extends MongoRepository<CompetitionClubListQuery, Long> {

}
