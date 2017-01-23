package com.mennyei.publicweb.match.infrastructure;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mennyei.publicweb.match.dto.MatchQuery;

@Repository
public interface MatchMongoRepository extends MongoRepository<MatchQuery, String> {
	List<MatchQuery> findByHomeClubIdAndCompetitionAndStageName(String clubId, String competitionId, String stageName);

}
